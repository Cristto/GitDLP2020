/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	Variable:Expresion -> lexema:String

public class Variable extends AbstractExpresion {

	public Variable(String lexema) {
		this.lexema = lexema;
	}

	public Variable(Object lexema) {
		this.lexema = (lexema instanceof Token) ? ((Token)lexema).getText() : (String) lexema;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(lexema);
	}

	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String lexema;

	public String toString() {
       return "{lexema:" + getLexema() + "}";
   }
}
