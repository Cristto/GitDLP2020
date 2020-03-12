/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import org.antlr.v4.runtime.ParserRuleContext;

import ast.tipos.Tipo;
import visitor.Visitor;

//	ExpresionCast:Expresion -> tipoCast:Tipo  expresion:Expresion

public class ExpresionCast extends AbstractExpresion {

	public ExpresionCast(Tipo tipoCast, Expresion expresion) {
		this.tipoCast = tipoCast;
		this.expresion = expresion;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(tipoCast, expresion);
	}

	public ExpresionCast(Object tipoCast, Object expresion) {
		this.tipoCast = (Tipo) ((tipoCast instanceof ParserRuleContext) ? getAST(tipoCast) : tipoCast);
		this.expresion = (Expresion) ((expresion instanceof ParserRuleContext) ? getAST(expresion) : expresion);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(tipoCast, expresion);
	}

	public Tipo getTipoCast() {
		return tipoCast;
	}
	public void setTipoCast(Tipo tipoCast) {
		this.tipoCast = tipoCast;
	}

	public Expresion getExpresion() {
		return expresion;
	}
	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Tipo tipoCast;
	private Expresion expresion;

	public String toString() {
       return "{tipoCast:" + getTipoCast() + ", expresion:" + getExpresion() + "}";
   }
}
