/**
 * Tutorial de Diseño de Lenguajes de Programación
 * @author Raúl Izquierdo
 */

package codegeneration;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.AST;
import ast.Position;
import ast.Programa;
import ast.definiciones.DefFuncion;
import ast.definiciones.DefStruct;
import ast.definiciones.DefVariable;
import ast.definiciones.Definicion;
import ast.expresiones.AccesoArray;
import ast.expresiones.AccesoStruct;
import ast.expresiones.Asignacion;
import ast.expresiones.CTE_Char;
import ast.expresiones.CTE_Entera;
import ast.expresiones.CTE_Real;
import ast.expresiones.ExprAritmetica;
import ast.expresiones.ExprComparacion;
import ast.expresiones.ExprLogica;
import ast.expresiones.ExprMenosUnario;
import ast.expresiones.ExprNot;
import ast.expresiones.ExpresionCast;
import ast.expresiones.ExpresionInvocacion;
import ast.expresiones.Variable;
import ast.sentencias.If;
import ast.sentencias.IfElse;
import ast.sentencias.InvocacionProcedimiento;
import ast.sentencias.Print;
import ast.sentencias.PrintLn;
import ast.sentencias.PrintSp;
import ast.sentencias.Read;
import ast.sentencias.Return;
import ast.sentencias.While;
import ast.tipos.Tipo;
import ast.tipos.TipoArray;
import ast.tipos.TipoChar;
import ast.tipos.TipoFloat;
import ast.tipos.TipoFuncion;
import ast.tipos.TipoInt;
import ast.tipos.TipoStruct;
import ast.tipos.TipoVoid;
import codegeneration.tipo.Funcion;
import visitor.DefaultVisitor;

public class CodeSelection extends DefaultVisitor {

	private Map<String, String> instruccion;

	private List<Definicion> locales;
	private Map<String, DefVariable> params;
	
	private int numWhile;
	private int numIfElse;
	private int numIf;

	public CodeSelection(Writer writer, String sourceFile) {
		this.writer = new PrintWriter(writer);
		this.sourceFile = sourceFile;

		this.instruccion = new HashMap<>();
		this.locales = new ArrayList<>();
		this.params = new HashMap<>();
		
		this.numIfElse = 0;
    	this.numWhile = 0;

		instruccion.put("&&", "and");
		instruccion.put("||", "or");
		instruccion.put("!", "not");

		instruccion.put("+", "add");
		instruccion.put("-", "sub");
		instruccion.put("/", "div");
		instruccion.put("*", "mul");

		instruccion.put(">", "gt");
		instruccion.put("<", "lt");
		instruccion.put("!=", "ne");
		instruccion.put("==", "eq");
		instruccion.put(">=", "ge");
		instruccion.put("<=", "le");
	}

	@Override
	public Object visit(Programa node, Object param) {
		out("#source \"" + sourceFile + "\"");
		for (Definicion def : node.getDefiniciones()) {
			if (def.getClass() == DefFuncion.class && def.getNombre().equals("main")) {
				out("call main");
				break;
			}
		}

		out("halt");
		super.visit(node, Funcion.SUFIJO);

		return null;
	}

	@Override
	public Object visit(DefVariable node, Object param) {
		out("#VAR " + node.getNombre() + ":" + node.getTipo().getNombreMapl());
		return null;
	}

	@Override
	public Object visit(DefFuncion node, Object param) {
		out(node.getNombre() + ":");
		out("enter " + totalLocales(node.getDefVariableLocal(), param));
		locales.addAll(node.getDefVariableLocal());
		for (Definicion defVar : ((TipoFuncion) node.getTipo()).getDefParametros()) {
			locales.add(defVar);
		}
		visitChildren(node.getSentencias(), node);
		if (((TipoFuncion) node.getTipo()).getTipoRetorno() instanceof TipoVoid)
			//ret retorno + locales + parametros
			out("RET 0," + totalLocales(node.getDefVariableLocal(), param) + ","
					+ totalParams(((TipoFuncion) node.getTipo()).getDefParametros(), param));
		return null;
	}
	
	@Override
	public Object visit(Return node, Object param) {
		DefFuncion funcion = (DefFuncion) param;	

		if (node.getExpresion() != null) {
			node.getExpresion().accept(this, Funcion.VALOR);
			out("RET " + node.getExpresion().getTipo().accept(new MemoryAllocation(), Funcion.TAM) + ","
					+ totalLocales(funcion.getDefVariableLocal(), param) + ","
					+ totalParams(((TipoFuncion) funcion.getTipo()).getDefParametros(), param));
		} else {
			out("RET 0, " + totalLocales(funcion.getDefVariableLocal(), param) + ","
					+ totalParams(((TipoFuncion) funcion.getTipo()).getDefParametros(), param));
		}
		return null;
	}

	@Override
	public Object visit(If node, Object param) {
		int local = numIf++;
		out("\n#line " + node.getEnd().getLine());

		if (node.getCondicion() != null)
			node.getCondicion().accept(this, Funcion.VALOR);

		out("jz fin" + local);

		visitChildren(node.getSentenciasIf(), param);
		out("fin" + local + ":");
		return null;
	}

	@Override
	public Object visit(IfElse node, Object param) {
		int local = numIfElse++;
		out("\n#line " + node.getEnd().getLine());

		if (node.getCondicion() != null)
			node.getCondicion().accept(this, Funcion.VALOR);

		out("jz else" + local);
		visitChildren(node.getSentenciasIf(), param);
		if (node.getSentenciasElse() != null)
			out("jmp fin" + local);
		out("else" + local + ":");

		visitChildren(node.getSentenciasElse(), param);
		if (node.getSentenciasElse() != null)
			out("fin" + local + ":");
		return null;
	}

	@Override
	public Object visit(While node, Object param) {
		int local = numWhile++;
		out("bucle" + local + ":");

		if (node.getCondicion() != null)
			node.getCondicion().accept(this, Funcion.VALOR);

		out("JZ finBucle" + local);
		visitChildren(node.getSentencias(), param);
		out("JMP bucle" + local);
		out("finBucle" + local + ":");

		return null;
	}

	@Override
	public Object visit(Read node, Object param) {
		out("\n#line " + node.getEnd().getLine());
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, Funcion.DIRECCION);
		out("in");
		out("store", node.getExpresion().getTipo());
		return null;
	}

	@Override
	public Object visit(Print node, Object param) {
		out("\n#line " + node.getEnd().getLine());
		node.getExpresion().accept(this, Funcion.VALOR);
		out("out", node.getExpresion().getTipo());
		return null;
	}

	@Override
	public Object visit(PrintLn node, Object param) {
		out("\n#line " + node.getEnd().getLine());
		node.getExpresion().accept(this, Funcion.VALOR);
		out("out", node.getExpresion().getTipo());
		out("pushb 10");
		out("outb");

		return null;
	}

	@Override
	public Object visit(PrintSp node, Object param) {
		out("\n#line " + node.getEnd().getLine());
		node.getExpresion().accept(this, Funcion.VALOR);
		out("out", node.getExpresion().getTipo());
		out("pushb 32");
		out("outb");

		return null;
	}

	@Override
	public Object visit(Asignacion node, Object param) {
		out("\n#line " + node.getEnd().getLine());
		node.getExpIzq().accept(this, Funcion.DIRECCION);
		node.getExpDrcha().accept(this, Funcion.VALOR);
		out("store", node.getExpIzq().getTipo());
		return null;
	}

	@Override
	public Object visit(InvocacionProcedimiento node, Object param) {
		out("#line " + node.getEnd().getLine());
		visitChildren(node.getArgumentos(), Funcion.VALOR);
		out("call " + node.getNombre());
		TipoFuncion tipoFuncion = ((TipoFuncion) node.getDefinicion().getTipo());
		if(!(tipoFuncion.getTipoRetorno() instanceof TipoVoid)) {
			out("pop", tipoFuncion.getTipoRetorno());
		}

		return null;
	}

	@Override
	public Object visit(ExprAritmetica node, Object param) {
		assert (param == Funcion.VALOR);
		node.getExpIzq().accept(this, Funcion.VALOR);
		node.getExpDrcha().accept(this, Funcion.VALOR);
		out(instruccion.get(node.getOperator()), node.getTipo());
		return null;
	}

	@Override
	public Object visit(ExprComparacion node, Object param) {
		assert (param == Funcion.VALOR);
		node.getExpIzq().accept(this, Funcion.VALOR);
		node.getExpDrcha().accept(this, Funcion.VALOR);
		out(instruccion.get(node.getOperator()), node.getExpIzq().getTipo());
		return null;
	}

	@Override
	public Object visit(ExprLogica node, Object param) {
		assert (param == Funcion.VALOR);
		node.getExpIzq().accept(this, Funcion.VALOR);
		node.getExpDrcha().accept(this, Funcion.VALOR);
		out(instruccion.get(node.getOperator()));
		return null;
	}

	@Override
	public Object visit(AccesoStruct node, Object param) {
		if (param == Funcion.DIRECCION) {
			if (node.getExpresion() != null)
				node.getExpresion().accept(this, Funcion.DIRECCION);
			Definicion s = ((TipoStruct) node.getExpresion().getTipo()).getDefinicion();
			int desplazamiento = 0;
			for (Definicion c : ((DefStruct) s).getCampos()) {
				if (!c.getNombre().equals(node.getIdentificador()))
					desplazamiento += (int) c.getTipo().accept(new MemoryAllocation(), Funcion.TAM);
				else
					break;
			}
			out("push " + desplazamiento);
			out("add");
		} else {
			visit(node, Funcion.DIRECCION);
			out("load", node.getTipo());
		}
		return null;
	}

	@Override
	public Object visit(AccesoArray node, Object param) {
		if (param == Funcion.DIRECCION) {
			node.getIdentificador().accept(this, Funcion.DIRECCION);
			node.getPosicion().accept(this, Funcion.VALOR);
			out("push " + node.getTipo().accept(new MemoryAllocation(), Funcion.TAM));
			out(instruccion.get("*"));
			out(instruccion.get("+"));
		} else {
			visit(node, Funcion.DIRECCION);
			out("load", node.getTipo());
		}
		return null;
	}

	@Override
	public Object visit(ExprNot node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, Funcion.VALOR);
		out(instruccion.get("!"));
		return null;
	}

	@Override
	public Object visit(ExprMenosUnario node, Object param) {
		
		if(node.getTipo() instanceof TipoInt)
			out("pushi 0");
		else
			out("pushf 0.0");		
		node.getExpr().accept(this, Funcion.VALOR);
		out("sub",node.getExpr().getTipo());
		
		return null;
	}

	@Override
	public Object visit(ExpresionCast node, Object param) {
		
		node.getExpresion().accept(this, Funcion.VALOR);
		if(node.getTipoCast() instanceof TipoChar 
				&& node.getExpresion().getTipo() instanceof TipoFloat) {
			out("f2i");
			out("i2b");			
		}
		else if(node.getTipoCast() instanceof TipoFloat 
				&& node.getExpresion().getTipo() instanceof TipoChar) {
			out("b2i");
			out("i2f");
		}
		else {
			out(node.getExpresion().getTipo().accept(this, Funcion.SUFIJO) + "2"
					+ node.getTipo().accept(this, Funcion.SUFIJO));
		}
		
		
		return null;
	}

	@Override
	public Object visit(ExpresionInvocacion node, Object param) {
		visitChildren(node.getArgumentos(), Funcion.VALOR);
		out("call " + node.getNombre());

		return null;
	}

	@Override
	public Object visit(Variable node, Object param) {
		if (param == Funcion.VALOR) { //aqui es por valor
			visit(node, Funcion.DIRECCION); //entra de nuevo por el y va al else y luego termina el if
			out("load", node.getTipo());
		} else { // aqui por direccion
			if (locales.contains(node.getDefinicion())) {
				out("push bp");
				out("push " + node.getDefinicion().getDireccion());
				out("add");
			} else if (params.containsKey(node.getNombre())) {
				out("push bp");
				out("push " + params.get(node.getNombre()).getDireccion());
				out("add");
			} else
				out("pusha " + node.getDefinicion().getDireccion());

		}
		return null;
	}

	@Override
	public Object visit(CTE_Real node, Object param) {
		assert (param == Funcion.VALOR);
		out("pushf " + node.getValor());
		return null;
	}

	@Override
	public Object visit(CTE_Entera node, Object param) {
		assert (param == Funcion.VALOR);
		out("pushi " + node.getValor());
		return null;
	}

	@Override
	public Object visit(CTE_Char node, Object param) {
		assert (param == Funcion.VALOR);
		// out("pushb " + Integer.parseInt(node.getLexema()));
		out("pushb " + (int) node.getLexema().toCharArray()[1]);
		return null;
	}

	// Sufijos------------

	@Override
	public Object visit(TipoInt node, Object param) {
		assert (param == Funcion.SUFIJO);
		return 'i';
	}

	@Override
	public Object visit(TipoFloat node, Object param) {
		assert (param == Funcion.SUFIJO);
		return 'f';
	}

	@Override
	public Object visit(TipoChar node, Object param) {
		assert (param == Funcion.SUFIJO);
		return 'b';
	}

	@Override
	public Object visit(TipoVoid node, Object param) {
		assert (param == Funcion.SUFIJO);
		return ' ';
	}

	@Override
	public Object visit(TipoFuncion node, Object param) {
		assert (param == Funcion.SUFIJO);
		return node.getTipoRetorno().accept(this, Funcion.SUFIJO);
	}

	@Override
	public Object visit(TipoStruct node, Object param) {
		assert (param == Funcion.SUFIJO);
		return ' ';
	}

	@Override
	public Object visit(TipoArray node, Object param) {
		assert (param == Funcion.SUFIJO);
		return node.getTipoDe().accept(this, Funcion.SUFIJO);
	}
	
	// Tama�os totales
    private int totalLocales(List<Definicion> vars, Object param) {
	int total = 0;
	for (Definicion def : vars) {
	    total += (int) def.getTipo().accept(new MemoryAllocation(),
		    Funcion.TAM);
	}
	return total;
    }

    private int totalParams(List<Definicion> params, Object param) {
	int total = 0;
	for (Definicion def : params) {
	    total += (int) def.getTipo().accept(new MemoryAllocation(),
		    Funcion.TAM);
	}
	return total;
    }

	// Métodos auxiliares recomendados (opcionales) -------------

	private void out(String instruccion, Tipo tipo) {
		writer.println(instruccion + tipo.accept(this, Funcion.SUFIJO));
	}

	// Imprime una línea en el fichero de salida
	private void out(String instruction) {
		writer.println(instruction);
	}

	// Imprime la directiva `#line` junto con la línea del fichero de entrada donde
	// estaba el nodo
	// indicado
	private void line(AST node) {
		line(node.getEnd());
	}

	private void line(Position pos) {
		out("\n#line " + pos.getLine());
	}

	private PrintWriter writer;
	private String sourceFile;
}
