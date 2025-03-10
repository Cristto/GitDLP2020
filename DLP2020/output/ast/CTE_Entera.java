/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	CTE_Entera:Expresion -> valor:String

public class CTE_Entera extends AbstractExpresion {

	public CTE_Entera(String valor) {
		this.valor = valor;
	}

	public CTE_Entera(Object valor) {
		this.valor = (valor instanceof Token) ? ((Token)valor).getText() : (String) valor;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(valor);
	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String valor;

	public String toString() {
       return "{valor:" + getValor() + "}";
   }
}
