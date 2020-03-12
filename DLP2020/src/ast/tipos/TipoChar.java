/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.tipos;

import visitor.Visitor;

//	TipoChar:Tipo -> 

public class TipoChar extends AbstractTipo {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}


	public String toString() {
       return "{TipoChar}";
   }
}
