/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.tipos;

import visitor.Visitor;

//	TipoVoid:Tipo -> 

public class TipoVoid extends AbstractTipo {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}


	public String toString() {
       return "{TipoVoid}";
   }
}
