/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import org.antlr.v4.runtime.Token;

import visitor.Visitor;

//	CTE_Real:Expresion -> valor:String

public class CTE_Real extends AbstractExpresion {

	public CTE_Real(String valor) {
		this.valor = valor;
	}

	public CTE_Real(Object valor) {
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
