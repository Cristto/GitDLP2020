/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.definiciones.Definicion;
import visitor.Visitor;

//	ExpresionInvocacion:Expresion -> nombre:String  argumentos:Expresion*

public class ExpresionInvocacion extends AbstractExpresion {

	public ExpresionInvocacion(String nombre, List<Expresion> argumentos) {
		this.nombre = nombre;
		this.argumentos = argumentos;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(argumentos);
	}

	@SuppressWarnings("unchecked")
	public ExpresionInvocacion(Object nombre, Object argumentos) {
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
	
	public Definicion getDefinicion() {
		return definicion;
	}
	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}

	private String nombre;
	private List<Expresion> argumentos;
	private Definicion definicion;

	public String toString() {
       return "{nombre:" + getNombre() + ", argumentos:" + getArgumentos() + "}";
   }
}
