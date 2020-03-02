/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	If:Sentencia -> condicion:Expresion  sentenciasIf:Sentencia*

public class If extends AbstractSentencia {

	public If(Expresion condicion, List<Sentencia> sentenciasIf) {
		this.condicion = condicion;
		this.sentenciasIf = sentenciasIf;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(condicion, sentenciasIf);
	}

	@SuppressWarnings("unchecked")
	public If(Object condicion, Object sentenciasIf) {
		this.condicion = (Expresion) ((condicion instanceof ParserRuleContext) ? getAST(condicion) : condicion);
		this.sentenciasIf = (List<Sentencia>) sentenciasIf;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(condicion, sentenciasIf);
	}

	public Expresion getCondicion() {
		return condicion;
	}
	public void setCondicion(Expresion condicion) {
		this.condicion = condicion;
	}

	public List<Sentencia> getSentenciasIf() {
		return sentenciasIf;
	}
	public void setSentenciasIf(List<Sentencia> sentenciasIf) {
		this.sentenciasIf = sentenciasIf;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion condicion;
	private List<Sentencia> sentenciasIf;

	public String toString() {
       return "{condicion:" + getCondicion() + ", sentenciasIf:" + getSentenciasIf() + "}";
   }
}
