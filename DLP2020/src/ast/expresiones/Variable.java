/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import org.antlr.v4.runtime.Token;

import ast.definiciones.Definicion;
import visitor.Visitor;

//	Variable:Expresion -> nombre:String

public class Variable extends AbstractExpresion {

	public Variable(String nombre) {
		this.nombre = nombre;
	}

	public Variable(Object nombre) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getText() : (String) nombre;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(nombre);
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}
	
	public Definicion getDefinicion() {
		return definicion;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String nombre;
	private Definicion definicion;

	public String toString() {
       return "{nombre:" + getNombre() + "}";
   }
}
