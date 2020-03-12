/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import org.antlr.v4.runtime.ParserRuleContext;

import ast.sentencias.AbstractSentencia;
import visitor.Visitor;

//	Asignacion:Sentencia -> expIzq:Expresion  expDrcha:Expresion

public class Asignacion extends AbstractSentencia {

	public Asignacion(Expresion expIzq, Expresion expDrcha) {
		this.expIzq = expIzq;
		this.expDrcha = expDrcha;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expIzq, expDrcha);
	}

	public Asignacion(Object expIzq, Object expDrcha) {
		this.expIzq = (Expresion) ((expIzq instanceof ParserRuleContext) ? getAST(expIzq) : expIzq);
		this.expDrcha = (Expresion) ((expDrcha instanceof ParserRuleContext) ? getAST(expDrcha) : expDrcha);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expIzq, expDrcha);
	}

	public Expresion getExpIzq() {
		return expIzq;
	}
	public void setExpIzq(Expresion expIzq) {
		this.expIzq = expIzq;
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
	private Expresion expDrcha;

	public String toString() {
       return "{expIzq:" + getExpIzq() + ", expDrcha:" + getExpDrcha() + "}";
   }
}
