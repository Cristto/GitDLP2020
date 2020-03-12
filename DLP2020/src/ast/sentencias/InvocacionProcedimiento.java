/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.sentencias;

import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.expresiones.Expresion;
import visitor.Visitor;

//	InvocacionProcedimiento:Sentencia -> nombre:String  argumentos:Expresion*

public class InvocacionProcedimiento extends AbstractSentencia {

	public InvocacionProcedimiento(String nombre, List<Expresion> argumentos) {
		this.nombre = nombre;
		this.argumentos = argumentos;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(argumentos);
	}

	@SuppressWarnings("unchecked")
	public InvocacionProcedimiento(Object nombre, Object argumentos) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getText() : (String) nombre;
		this.argumentos = (List<Expresion>) argumentos;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(nombre, argumentos);
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Expresion> getArgumentos() {
		return argumentos;
	}
	public void setArgumentos(List<Expresion> argumentos) {
		this.argumentos = argumentos;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String nombre;
	private List<Expresion> argumentos;

	public String toString() {
       return "{nombre:" + getNombre() + ", argumentos:" + getArgumentos() + "}";
   }
}
