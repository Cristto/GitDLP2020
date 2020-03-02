/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	DefStruct:Definicion -> nombre:String  campos:Definicion*  tipo:Tipo

public class DefStruct extends AbstractDefinicion {

	public DefStruct(String nombre, List<Definicion> campos, Tipo tipo) {
		this.nombre = nombre;
		this.campos = campos;
		this.tipo = tipo;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(campos, tipo);
	}

	@SuppressWarnings("unchecked")
	public DefStruct(Object nombre, Object campos, Object tipo) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getText() : (String) nombre;
		this.campos = (List<Definicion>) campos;
		this.tipo = (Tipo) ((tipo instanceof ParserRuleContext) ? getAST(tipo) : tipo);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(nombre, campos, tipo);
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Definicion> getCampos() {
		return campos;
	}
	public void setCampos(List<Definicion> campos) {
		this.campos = campos;
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String nombre;
	private List<Definicion> campos;
	private Tipo tipo;

	public String toString() {
       return "{nombre:" + getNombre() + ", campos:" + getCampos() + ", tipo:" + getTipo() + "}";
   }
}
