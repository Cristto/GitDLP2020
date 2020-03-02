/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	AccesoArray:Expresion -> identificador:Expresion  posicion:Expresion

public class AccesoArray extends AbstractExpresion {

	public AccesoArray(Expresion identificador, Expresion posicion) {
		this.identificador = identificador;
		this.posicion = posicion;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(identificador, posicion);
	}

	public AccesoArray(Object identificador, Object posicion) {
		this.identificador = (Expresion) ((identificador instanceof ParserRuleContext) ? getAST(identificador) : identificador);
		this.posicion = (Expresion) ((posicion instanceof ParserRuleContext) ? getAST(posicion) : posicion);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(identificador, posicion);
	}

	public Expresion getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Expresion identificador) {
		this.identificador = identificador;
	}

	public Expresion getPosicion() {
		return posicion;
	}
	public void setPosicion(Expresion posicion) {
		this.posicion = posicion;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion identificador;
	private Expresion posicion;

	public String toString() {
       return "{identificador:" + getIdentificador() + ", posicion:" + getPosicion() + "}";
   }
}
