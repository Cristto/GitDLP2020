/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package visitor;

import ast.*;
import ast.definiciones.*;
import ast.expresiones.*;
import ast.sentencias.*;
import ast.tipos.*;
import java.util.*;

/*
DefaultVisitor. Implementación base del visitor para ser derivada por nuevos visitor.
	No modificar esta clase. Para crear nuevos visitor usar el fichero "_PlantillaParaVisitors.txt".
	DefaultVisitor ofrece una implementación por defecto de cada nodo que se limita a visitar los nodos hijos.
*/
public class DefaultVisitor implements Visitor {

	//	class Programa { List<Definicion> definiciones; }
	public Object visit(Programa node, Object param) {
		visitChildren(node.getDefiniciones(), param);
		return null;
	}

	//	class DefVariable { String nombre;  Tipo tipo; }
	public Object visit(DefVariable node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class DefFuncion { String nombre;  Tipo tipo;  List<Definicion> defVariableLocal;  List<Sentencia> sentencias; }
	public Object visit(DefFuncion node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		visitChildren(node.getDefVariableLocal(), param);
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class DefStruct { String nombre;  List<Definicion> campos;  Tipo tipo; }
	public Object visit(DefStruct node, Object param) {
		visitChildren(node.getCampos(), param);
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class DefCampoStruct { String nombre;  Tipo tipo; }
	public Object visit(DefCampoStruct node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class Print { Expresion expresion; }
	public Object visit(Print node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class PrintLn { Expresion expresion; }
	public Object visit(PrintLn node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class PrintSp { Expresion expresion; }
	public Object visit(PrintSp node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Asignacion { Expresion expIzq;  Expresion expDrcha; }
	public Object visit(Asignacion node, Object param) {
		if (node.getExpIzq() != null)
			node.getExpIzq().accept(this, param);
		if (node.getExpDrcha() != null)
			node.getExpDrcha().accept(this, param);
		return null;
	}

	//	class If { Expresion condicion;  List<Sentencia> sentenciasIf; }
	public Object visit(If node, Object param) {
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		visitChildren(node.getSentenciasIf(), param);
		return null;
	}

	//	class IfElse { Expresion condicion;  List<Sentencia> sentenciasIf;  List<Sentencia> sentenciasElse; }
	public Object visit(IfElse node, Object param) {
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		visitChildren(node.getSentenciasIf(), param);
		visitChildren(node.getSentenciasElse(), param);
		return null;
	}

	//	class While { Expresion condicion;  List<Sentencia> sentencias; }
	public Object visit(While node, Object param) {
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class InvocacionProcedimiento { String nombre;  List<Expresion> argumentos; }
	public Object visit(InvocacionProcedimiento node, Object param) {
		visitChildren(node.getArgumentos(), param);
		return null;
	}

	//	class Return { Expresion expresion; }
	public Object visit(Return node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Read { Expresion expresion; }
	public Object visit(Read node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class ExprAritmetica { Expresion expIzq;  String operator;  Expresion expDrcha; }
	public Object visit(ExprAritmetica node, Object param) {
		if (node.getExpIzq() != null)
			node.getExpIzq().accept(this, param);
		if (node.getExpDrcha() != null)
			node.getExpDrcha().accept(this, param);
		return null;
	}

	//	class ExprComparacion { Expresion expIzq;  String operator;  Expresion expDrcha; }
	public Object visit(ExprComparacion node, Object param) {
		if (node.getExpIzq() != null)
			node.getExpIzq().accept(this, param);
		if (node.getExpDrcha() != null)
			node.getExpDrcha().accept(this, param);
		return null;
	}

	//	class ExprLogica { Expresion expIzq;  String operator;  Expresion expDrcha; }
	public Object visit(ExprLogica node, Object param) {
		if (node.getExpIzq() != null)
			node.getExpIzq().accept(this, param);
		if (node.getExpDrcha() != null)
			node.getExpDrcha().accept(this, param);
		return null;
	}

	//	class ExprMenosUnario { Expresion expr; }
	public Object visit(ExprMenosUnario node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class ExprNot { Expresion expr; }
	public Object visit(ExprNot node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class AccesoArray { Expresion identificador;  Expresion posicion; }
	public Object visit(AccesoArray node, Object param) {
		if (node.getIdentificador() != null)
			node.getIdentificador().accept(this, param);
		if (node.getPosicion() != null)
			node.getPosicion().accept(this, param);
		return null;
	}

	//	class ExpresionCast { Tipo tipoCast;  Expresion expresion; }
	public Object visit(ExpresionCast node, Object param) {
		if (node.getTipoCast() != null)
			node.getTipoCast().accept(this, param);
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class AccesoStruct { Expresion expresion;  String identificador; }
	public Object visit(AccesoStruct node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class ExpresionInvocacion { String nombre;  List<Expresion> argumentos; }
	public Object visit(ExpresionInvocacion node, Object param) {
		visitChildren(node.getArgumentos(), param);
		return null;
	}

	//	class CTE_Real { String valor; }
	public Object visit(CTE_Real node, Object param) {
		return null;
	}

	//	class CTE_Entera { String valor; }
	public Object visit(CTE_Entera node, Object param) {
		return null;
	}

	//	class CTE_Char { String lexema; }
	public Object visit(CTE_Char node, Object param) {
		return null;
	}

	//	class Variable { String nombre; }
	public Object visit(Variable node, Object param) {
		return null;
	}

	//	class TipoInt {  }
	public Object visit(TipoInt node, Object param) {
		return null;
	}

	//	class TipoStruct { String identificador; }
	public Object visit(TipoStruct node, Object param) {
		return null;
	}

	//	class TipoFloat {  }
	public Object visit(TipoFloat node, Object param) {
		return null;
	}

	//	class TipoChar {  }
	public Object visit(TipoChar node, Object param) {
		return null;
	}

	//	class TipoArray { Integer size;  Tipo tipoDe; }
	public Object visit(TipoArray node, Object param) {
		if (node.getTipoDe() != null)
			node.getTipoDe().accept(this, param);
		return null;
	}

	//	class TipoVoid {  }
	public Object visit(TipoVoid node, Object param) {
		return null;
	}

	//	class TipoFuncion { Tipo tipoRetorno;  List<Definicion> defParametros; }
	public Object visit(TipoFuncion node, Object param) {
		if (node.getTipoRetorno() != null)
			node.getTipoRetorno().accept(this, param);
		visitChildren(node.getDefParametros(), param);
		return null;
	}

    // Método auxiliar -----------------------------
    protected void visitChildren(List<? extends AST> children, Object param) {
        if (children != null)
            for (AST child : children)
                child.accept(this, param);
    }
}
