/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	TipoArray:Tipo -> size:Integer  tipoDe:Tipo

public class TipoArray extends AbstractTipo {

	public TipoArray(Integer size, Tipo tipoDe) {
		this.size = size;
		this.tipoDe = tipoDe;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(tipoDe);
	}

	public TipoArray(Object size, Object tipoDe) {
		this.size = Integer.parseInt(((Token) size).getText()); //CAMBIO
		//this.size = (Integer) ((size instanceof ParserRuleContext) ? getAST(size) : size);
		this.tipoDe = (Tipo) ((tipoDe instanceof ParserRuleContext) ? getAST(tipoDe) : tipoDe);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(size, tipoDe);
	}

	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

	public Tipo getTipoDe() {
		return tipoDe;
	}
	public void setTipoDe(Tipo tipoDe) {
		this.tipoDe = tipoDe;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Integer size;
	private Tipo tipoDe;

	public String toString() {
       return "{size:" + getSize() + ", tipoDe:" + getTipoDe() + "}";
   }
}
