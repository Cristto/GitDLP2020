/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.tipos;

import org.antlr.v4.runtime.Token;

import ast.definiciones.Definicion;
import ast.definiciones.DefStruct;
import visitor.Visitor;

//	TipoStruct:Tipo -> identificador:String

public class TipoStruct extends AbstractTipo {

	public TipoStruct(String identificador) {
		this.identificador = identificador;
	}

	public TipoStruct(Object identificador) {
		this.identificador = (identificador instanceof Token) ? ((Token)identificador).getText() : (String) identificador;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(identificador);
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
	
	public Definicion getDefinicion() {
		return definicion;
	}
	
	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}

	private String identificador;
	private Definicion definicion;
	
	 @Override
	    public String getNombreMapl() {
		String res = "{";
		for (Definicion c : ((DefStruct) definicion).getCampos()) {
		    res += c.getNombre() + ":" + c.getTipo().getNombreMapl() + " ";
		}
		res += "}";
		return res;
	    }

	public String toString() {
       return "{identificador:" + getIdentificador() + "}";
   }
}
