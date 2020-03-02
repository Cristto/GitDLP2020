/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	ExprLogica:Expresion -> expIzq:Expresion  operator:String  expDrcha:Expresion

public class ExprLogica extends AbstractExpresion {

	public ExprLogica(Expresion expIzq, String operator, Expresion expDrcha) {
		this.expIzq = expIzq;
		this.operator = operator;
		this.expDrcha = expDrcha;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expIzq, expDrcha);
	}

	public ExprLogica(Object expIzq, Object operator, Object expDrcha) {
		this.expIzq = (Expresion) ((expIzq instanceof ParserRuleContext) ? getAST(expIzq) : expIzq);
		this.operator = (operator instanceof Token) ? ((Token)operator).getText() : (String) operator;
		this.expDrcha = (Expresion) ((expDrcha instanceof ParserRuleContext) ? getAST(expDrcha) : expDrcha);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expIzq, operator, expDrcha);
	}

	public Expresion getExpIzq() {
		return expIzq;
	}
	public void setExpIzq(Expresion expIzq) {
		this.expIzq = expIzq;
	}

	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Expresion getExpDrcha() {
		return expDrcha;
	}
	public void setExpDrcha(Expresion expDrcha) {
		this.expDrcha = expDrcha;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion expIzq;
	private String operator;
	private Expresion expDrcha;

	public String toString() {
       return "{expIzq:" + getExpIzq() + ", operator:" + getOperator() + ", expDrcha:" + getExpDrcha() + "}";
   }
}
