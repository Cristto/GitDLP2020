/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	DefFuncion:Definicion -> nombre:String  tipo:Tipo  defVariableLocal:Definicion*  sentencias:Sentencia*

public class DefFuncion extends AbstractDefinicion {

	public DefFuncion(String nombre, Tipo tipo, List<Definicion> defVariableLocal, List<Sentencia> sentencias) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.defVariableLocal = defVariableLocal;
		this.sentencias = sentencias;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(tipo, defVariableLocal, sentencias);
	}

	@SuppressWarnings("unchecked")
	public DefFuncion(Object nombre, Object tipo, Object defVariableLocal, Object sentencias) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getText() : (String) nombre;
		this.tipo = (Tipo) ((tipo instanceof ParserRuleContext) ? getAST(tipo) : tipo);
		this.defVariableLocal = (List<Definicion>) defVariableLocal;
		this.sentencias = (List<Sentencia>) sentencias;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(nombre, tipo, defVariableLocal, sentencias);
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Definicion> getDefVariableLocal() {
		return defVariableLocal;
	}
	public void setDefVariableLocal(List<Definicion> defVariableLocal) {
		this.defVariableLocal = defVariableLocal;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}
	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String nombre;
	private Tipo tipo;
	private List<Definicion> defVariableLocal;
	private List<Sentencia> sentencias;

	public String toString() {
       return "{nombre:" + getNombre() + ", tipo:" + getTipo() + ", defVariableLocal:" + getDefVariableLocal() + ", sentencias:" + getSentencias() + "}";
   }
}
