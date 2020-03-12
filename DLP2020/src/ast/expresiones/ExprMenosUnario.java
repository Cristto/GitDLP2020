/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import org.antlr.v4.runtime.ParserRuleContext;

import visitor.Visitor;

//	ExprMenosUnario:Expresion -> expr:Expresion

public class ExprMenosUnario extends AbstractExpresion {

	public ExprMenosUnario(Expresion expr) {
		this.expr = expr;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expr);
	}

	public ExprMenosUnario(Object expr) {
		this.expr = (Expresion) ((expr instanceof ParserRuleContext) ? getAST(expr) : expr);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expr);
	}

	public Expresion getExpr() {
		return expr;
	}
	public void setExpr(Expresion expr) {
		this.expr = expr;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion expr;

	public String toString() {
       return "{expr:" + getExpr() + "}";
   }
}
