/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import visitor.Visitor;

//	AccesoStruct:Expresion -> expresion:Expresion  identificador:String

public class AccesoStruct extends AbstractExpresion {

	public AccesoStruct(Expresion expresion, String identificador) {
		this.expresion = expresion;
		this.identificador = identificador;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expresion);
	}

	public AccesoStruct(Object expresion, Object identificador) {
		this.expresion = (Expresion) ((expresion instanceof ParserRuleContext) ? getAST(expresion) : expresion);
		this.identificador = (identificador instanceof Token) ? ((Token)identificador).getText() : (String) identificador;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expresion, identificador);
	}

	public Expresion getExpresion() {
		return expresion;
	}
	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion expresion;
	private String identificador;

	public String toString() {
       return "{expresion:" + getExpresion() + ", identificador:" + getIdentificador() + "}";
   }
}
