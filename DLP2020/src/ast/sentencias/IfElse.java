/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.sentencias;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import ast.expresiones.Expresion;
import visitor.Visitor;

//	IfElse:Sentencia -> condicion:Expresion  sentenciasIf:Sentencia*  sentenciasElse:Sentencia*

public class IfElse extends AbstractSentencia {

	public IfElse(Expresion condicion, List<Sentencia> sentenciasIf, List<Sentencia> sentenciasElse) {
		this.condicion = condicion;
		this.sentenciasIf = sentenciasIf;
		this.sentenciasElse = sentenciasElse;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(condicion, sentenciasIf, sentenciasElse);
	}

	@SuppressWarnings("unchecked")
	public IfElse(Object condicion, Object sentenciasIf, Object sentenciasElse) {
		this.condicion = (Expresion) ((condicion instanceof ParserRuleContext) ? getAST(condicion) : condicion);
		this.sentenciasIf = (List<Sentencia>) sentenciasIf;
		this.sentenciasElse = (List<Sentencia>) sentenciasElse;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(condicion, sentenciasIf, sentenciasElse);
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

	public List<Sentencia> getSentenciasElse() {
		return sentenciasElse;
	}
	public void setSentenciasElse(List<Sentencia> sentenciasElse) {
		this.sentenciasElse = sentenciasElse;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion condicion;
	private List<Sentencia> sentenciasIf;
	private List<Sentencia> sentenciasElse;

	public String toString() {
       return "{condicion:" + getCondicion() + ", sentenciasIf:" + getSentenciasIf() + ", sentenciasElse:" + getSentenciasElse() + "}";
   }
}
