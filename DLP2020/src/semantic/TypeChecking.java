/**
 * Tutorial de Dise帽o de Lenguajes de Programaci贸n
 * @author Ra煤l Izquierdo
 */

package semantic;

import java.util.List;

import ast.AST;
import ast.Position;
import ast.definiciones.DefCampoStruct;
import ast.definiciones.DefFuncion;
import ast.definiciones.DefStruct;
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
import ast.expresiones.Expresion;
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
import main.ErrorManager;
import visitor.DefaultVisitor;

public class TypeChecking extends DefaultVisitor {

    public TypeChecking(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

	// class DefFuncion { String nombre; Tipo tipo; List<Definicion>
	// defVariableLocal; List<Sentencia> sentencias; }
	public Object visit(DefFuncion node, Object param) {

		// Si no es tipoSimple el tipo del retorno ni tipoVoid
		if (!esTipoSimple(((TipoFuncion) node.getTipo()).getTipoRetorno())
				&& !(((TipoFuncion) node.getTipo()).getTipoRetorno() instanceof TipoVoid))
			predicado(false, "El tipo de retorno de la funcion no es simple", node.getStart());

		// recorres los parametros que tb so parte del tipoFuncion y comprueba que sean
		// de tipoSImple
		for (Definicion parametro : ((TipoFuncion) node.getTipo()).getDefParametros()) {
			if (!esTipoSimple(parametro.getTipo()))
				predicado(false, "El parametro no es de tipo simple", parametro.getStart());
		}
		visitChildren(node.getDefVariableLocal(), param); // visito las variables locales
		visitChildren(node.getSentencias(), node.getTipo()); // visito las sentencias
		return null;
	}

	// class DefStruct { String nombre; List<Definicion> campos; Tipo tipo; }
	public Object visit(DefStruct node, Object param) {

		// super.visit(node, param);

		if (node.getCampos() != null)
			for (Definicion child : node.getCampos())
				child.accept(this, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}

	// class DefCampoStruct { String nombre; Tipo tipo; }
	public Object visit(DefCampoStruct node, Object param) {

		// super.visit(node, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}

	// class Print { Expresion expresion; }
	public Object visit(Print node, Object param) {

		super.visit(node, param);

		if (!esTipoSimple(node.getExpresion().getTipo()))
			predicado(false, "Expresion no imprimible", node.getStart());

		return null;
	}

	// class PrintLn { Expresion expresion; }
	public Object visit(PrintLn node, Object param) {

		super.visit(node, param);

		if (!esTipoSimple(node.getExpresion().getTipo()))
			predicado(false, "Expresion no imprimible", node.getStart());

		return null;
	}

	// class PrintSp { Expresion expresion; }
	public Object visit(PrintSp node, Object param) {

		super.visit(node, param);

		if (!esTipoSimple(node.getExpresion().getTipo()))
			predicado(false, "Expresion no imprimible", node.getStart());

		return null;
	}

	// class Asignacion { Expresion expIzq; Expresion expDrcha; }
	public Object visit(Asignacion node, Object param) {

		super.visit(node, param); // comprueba qe las expresiones no sea null

		// la expresion izq tiene que ser del mismo tipo que la der
		if (!esMismoTipo(node.getExpIzq().getTipo(), node.getExpDrcha().getTipo()))
			predicado(false, "La expresion de la izquierda y la derecha tienen distinto tipo", node.getStart());

		// la expresion de la izq tiene qe ser de tipo simple
		if (!esTipoSimple(node.getExpIzq().getTipo()))
			predicado(false, "La parte izquierda de la expresion no es de tipo simple", node.getStart());
		
		if (!node.getExpIzq().isLvalue())
			predicado(false, "La expresion de la izquierda no es lvalue", node.getStart());
		return null;
	}

	// class If { Expresion condicion; List<Sentencia> sentenciasIf; }
	public Object visit(If node, Object param) {

		super.visit(node, param); // comprueba que la condicion no sea null

		comprobarCondicion(node.getCondicion()); // que la condicion sea de tipoInt
		return null;
	}

	// class IfElse { Expresion condicion; List<Sentencia> sentenciasIf;
	// List<Sentencia> sentenciasElse; }
	public Object visit(IfElse node, Object param) {

		super.visit(node, param); // comprueba que la condicion no sea null

		comprobarCondicion(node.getCondicion()); // que la condicion sea de tipoInt
		return null;
	}

	// class While { Expresion condicion; List<Sentencia> sentencias; }
	public Object visit(While node, Object param) {

		super.visit(node, param); // comprueba que la condicion no sea null

		comprobarCondicion(node.getCondicion()); // que la condicion sea de tipoInt
		return null;
	}

	// class InvocacionProcedimiento { String nombre; List<Expresion> argumentos; }
	public Object visit(InvocacionProcedimiento node, Object param) {

		super.visit(node, param); // visita los argumentos

		// se comprueba los parametros de la definicion de funcion con los argumentos,
		// tama駉 y tipo
		if (!esArgumentosValido(((TipoFuncion) node.getDefinicion().getTipo()).getDefParametros(),
				node.getArgumentos()))
			predicado(false, "Los argumentos no son validos", node.getStart());
		return null;
	}

	// class Return { Expresion expresion; }
	public Object visit(Return node, Object param) {

		super.visit(node, param); // primero compruebo que el return no este vacio
		
		// comprueba que la expresion del return sea igual al objeto que retorna
		if (node.getExpresion() != null && !esMismoTipo(node.getExpresion().getTipo(), ((TipoFuncion) param).getTipoRetorno()))
			predicado(false, "Tipo de retorno no coincide", node.getStart());

		// si es null compruebo si el tipoFuncion es igual al tipoVoid
		if (node.getExpresion() == null && !esMismoTipo(((TipoFuncion) param).getTipoRetorno(), TipoVoid.getInstance()))
			predicado(false, "El tipo de retorno no coincide", node.getStart());
		
		// la expresion de retorno tiene qe ser de tipo simple
		if (node.getExpresion() != null && !esTipoSimple(node.getExpresion().getTipo()))
			predicado(false, "La expresion de retorno no es de tipo simple", node.getStart());
		
		return null;
	}

	// class Read { Expresion expresion; }
	public Object visit(Read node, Object param) {

		super.visit(node, param);

		if (!esTipoSimple(node.getExpresion().getTipo()))
			predicado(false, "La expresion debe de ser de tipo simple", node.getStart());
		
		if (!node.getExpresion().isLvalue())
			predicado(false, "La expresion no es lvalue", node.getStart());

		return null;
	}

	// class ExprAritmetica { Expresion expIzq; String operator; Expresion expDrcha;
	// }
	public Object visit(ExprAritmetica node, Object param) {

		super.visit(node, param); // comprueba las dos expresiones

		if (!esMismoTipo(node.getExpIzq().getTipo(), // las expr tienen que ser de mismo tipo
				node.getExpDrcha().getTipo()))
			predicado(false, "La expresiones deben de ser del mismo tipo", node.getStart());

		// le a馻do el tipo resultante a la expresion
		node.setTipo(node.getExpIzq().getTipo());
		
		node.setLvalue(false);
		return null;
	}

	// class ExprComparacion { Expresion expIzq; String operator; Expresion
	// expDrcha; }
	public Object visit(ExprComparacion node, Object param) {

		super.visit(node, param); // compruebo las expresiones

		// igual que en el aritmetico
		if (!esMismoTipo(node.getExpIzq().getTipo(), node.getExpDrcha().getTipo()))
			predicado(false, "La expresiones deben de ser del mismo tipo", node.getStart());
		// en comparaciones solo tipos int y real
		if (!esTipoComparacion(node.getExpIzq().getTipo()))
			predicado(false, "Las expresiones deben de ser de tipo entero o real ", node.getStart());
		node.setTipo(TipoInt.getInstance());
		
		node.setLvalue(false);
		return null;
	}

	// class ExprLogica { Expresion expIzq; String operator; Expresion expDrcha; }
	public Object visit(ExprLogica node, Object param) {

		super.visit(node, param);

		if (!esMismoTipo(node.getExpIzq().getTipo(), node.getExpDrcha().getTipo()))
			predicado(false, "La expresiones deben de ser del mismo tipo", node.getStart());
		// tipoLogico que es tipoInt
		if (!esTipoLogico(node.getExpIzq().getTipo()))
			predicado(false, "La expresion debe de ser de tipo entero", node.getStart());
		node.setTipo(TipoInt.getInstance());
		
		node.setLvalue(false);
		return null;
	}

	// class ExprMenosUnario { Expresion expr; }
	public Object visit(ExprMenosUnario node, Object param) {

		// igual que la expresion aritmetica
		super.visit(node, param); // compruebo la expr

		if (!esTipoAritmetico(node.getExpr().getTipo()))
			predicado(false, "La expresion debe de ser de tipo entero", node.getStart());

		node.setTipo(node.getExpr().getTipo());
		
		node.setLvalue(false);
		return null;
	}

	// class ExprNot { Expresion expr; }
	public Object visit(ExprNot node, Object param) {

		super.visit(node, param);

		if (!esTipoLogico(node.getExpr().getTipo())) {
			predicado(false, "Debe de ser de tipo entero", node.getStart());
		}
		node.setTipo(TipoInt.getInstance());
		
		node.setLvalue(false);
		return null;
	}

	// class AccesoArray { Expresion identificador; Expresion pos; }
	public Object visit(AccesoArray node, Object param) {

		super.visit(node, param); // compruebas el ident y la posicion

		Tipo tipo = node.getIdentificador().getTipo(); // tipo del ident

		if (tipo instanceof TipoArray) { // que es de tipoArray??
			node.setTipo(((TipoArray) tipo).getTipoDe()); // la expresion es ahora de tipoArray
		} else {
			predicado(false, "Debe de ser de tipo array", node.getStart());
		}
		if (!(node.getPosicion().getTipo() instanceof TipoInt)) { // lo que va entre corchetes de tipoInt (indice)
			predicado(false, "El indice debe de ser de tipo entero", node.getStart());
		}
		
		node.setLvalue(true);
		
		return null;
	}

	// class ExpresionCast { Tipo tipoCast; Expresion expresion; }
	public Object visit(ExpresionCast node, Object param) {

		super.visit(node, param); // el cast y la expr

		if (!esTipoSimple(node.getTipoCast())) // el cast debe ser tipoSimple
			predicado(false, "Debe de castearse con un tipo simple", node.getStart());
		if (!esTipoSimple(node.getExpresion().getTipo())) // el tipo de la expresion debe ser tipo simple
			predicado(false, "Las expresiones a castear deben de ser de tipo simple", node.getStart());

		// los tipos de cast y la expresion tienen que ser distintos para poder hacer el
		// cast
		if (esMismoTipo(node.getExpresion().getTipo(), node.getTipoCast()))
			predicado(false, "las expresiones deben de ser de distinto tipo", node.getStart());

		// establezco el tipo de la expresion
		node.setTipo(node.getTipoCast());
		
		node.setLvalue(false);
		return null;
	}

	// class AccesoStruct { Expresion expresion; String nombre; }
	public Object visit(AccesoStruct node, Object param) {

		super.visit(node, param); // check la expr

		Tipo tipo = node.getExpresion().getTipo(); // saco el tipo de la expr

		if (tipo instanceof TipoStruct) { // tipo es tipoStruct??

			tipo = getTipoCampo((TipoStruct) tipo, node.getIdentificador());
			if (tipo == null) {
				predicado(true, "No existe un campo con tal nombre", node.getStart());
			} else {
				node.setTipo(tipo);
			}
		} else {
			predicado(true, "La expresion de la izquierda no es de tipo struct", node.getStart());
		}
		
		node.setLvalue(true);

		return null;
	}

	// class ExpresionInvocacion { String nombre; List<Expresion> argumentos; }
	public Object visit(ExpresionInvocacion node, Object param) {

		super.visit(node, param); // compruebo los argumentos

		// compruebo tama駉 y tipo de los parametros con los argumentos
		if (!esArgumentosValido(((TipoFuncion) node.getDefinicion().getTipo()).getDefParametros(),
				node.getArgumentos()))
			predicado(false, "Los argumentos no son validos", node.getStart());

		// estableces el tipo de retorno del tipoFuncion
		node.setTipo(((TipoFuncion) node.getDefinicion().getTipo()).getTipoRetorno());
		
		node.setLvalue(false);
		return null;
	}

	// class LiteralReal { String valor; }
	public Object visit(CTE_Real node, Object param) {
		node.setTipo(TipoFloat.getInstance());
		node.setLvalue(false);
		return null;
	}

	// class LiteralEntero { String valor; }
	public Object visit(CTE_Entera node, Object param) {
		node.setTipo(TipoInt.getInstance());
		node.setLvalue(false);
		return null;
	}

	// class LiteralChar { String lexema; }
	public Object visit(CTE_Char node, Object param) {
		node.setTipo(TipoChar.getInstance());
		node.setLvalue(false);
		return null;
	}

	// class Variable { String lexema; }
	public Object visit(Variable node, Object param) {
		// le estableces el tipo a la expresion
		node.setTipo(node.getDefinicion().getTipo());
		node.setLvalue(true);		
		return null;
	}

	////////////////////////////////////// Metodos
	////////////////////////////////////// auxiliares////////////////////////////////

	private boolean esTipoSimple(Tipo tipo) {
		return (tipo instanceof TipoInt) || (tipo instanceof TipoFloat) || (tipo instanceof TipoChar);
	}

	private boolean esTipoLogico(Tipo tipo) {
		return (tipo instanceof TipoInt);
	}

	private boolean esTipoAritmetico(Tipo tipo) {
		return (tipo instanceof TipoInt) || (tipo instanceof TipoFloat);
	}

	private boolean esTipoComparacion(Tipo tipo) {
		return (tipo instanceof TipoInt) || (tipo instanceof TipoFloat) || (tipo instanceof TipoChar);
	}

	private boolean esMismoTipo(Tipo a, Tipo b) {
		return (a.getClass() == b.getClass());
	}

	// cualquier sentencia que lleve una condicion, los if, el while
	private void comprobarCondicion(Expresion condicion) {
		if (!esTipoLogico(condicion.getTipo()))
			predicado(false, "La condici髇 debe ser de tipo entero", condicion.getStart());
	}

	// InvocacionProcedimiento tanto de sentencia como de expresion
	public boolean esArgumentosValido(List<Definicion> parametros, List<Expresion> argumentos) {
		if (parametros.size() != argumentos.size()) // parametros de la funcion definida y los argumentos sean iguales
			return false;
		else {// que el tipo de los parametros y de los argumentos sean los mismos
			for (int i = 0; i < parametros.size(); i++) {
				if (!esMismoTipo(argumentos.get(i).getTipo(), parametros.get(i).getTipo())) {
					return false;
				}
			}
			return true;
		}
	}

	private Tipo getTipoCampo(TipoStruct tipo, String nombre) {
		for (Definicion campo : ((DefStruct) tipo.getDefinicion()).getCampos()) {
			if (campo.getNombre().equals(nombre)) {
				return campo.getTipo();
			}
		}
		return null;
	}

    // # ----------------------------------------------------------
    // M茅todos auxiliares recomendados (opcionales) -------------

    /**
     * predicado. M茅todo auxiliar para implementar los predicados. Borrar si no se quiere usar.
     *
     * Ejemplos de uso (suponiendo que existe un m茅todo "esPrimitivo(expr)"):
     *
     *      1. predicado(esPrimitivo(expr.tipo), "La expresi贸n debe ser de un tipo primitivo", expr.getStart());
     *      2. predicado(esPrimitivo(expr.tipo), "La expresi贸n debe ser de un tipo primitivo", expr); // Se asume getStart()
     *      3. predicado(esPrimitivo(expr.tipo), "La expresi贸n debe ser de un tipo primitivo");
     *
     * NOTA: El m茅todo getStart() (ejemplo 1) indica la linea/columna del fichero fuente donde estaba el nodo
     * (y as铆 poder dar informaci贸n m谩s detallada de la posici贸n del error). Si se usa VGen, dicho m茅todo
     * habr谩 sido generado en todos los nodos del AST.
     * No es obligatorio llamar a getStart() (ejemplo 2), ya que si se pasa el nodo, se usar谩 por defecto dicha
     * posici贸n.
     * Si no se quiere imprimir la posici贸n del fichero, se puede omitir el tercer argumento (ejemplo 3).
     *
     * @param condition     Debe cumplirse para que no se produzca un error
     * @param errorMessage  Se imprime si no se cumple la condici贸n
     * @param posicionError Fila y columna del fichero donde se ha producido el error.
     */

    private void predicado(boolean condition, String errorMessage, Position position) {
        if (!condition)
            errorManager.notify("Type Checking", errorMessage, position);
    }

    private void predicado(boolean condition, String errorMessage, AST node) {
        predicado(condition, errorMessage, node.getStart());
    }

    private void predicado(boolean condition, String errorMessage) {
        predicado(condition, errorMessage, (Position) null);
    }

    private ErrorManager errorManager;
}
