/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.tipos;

import visitor.Visitor;

//	TipoInt:Tipo -> 

public class TipoInt extends AbstractTipo {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}


	public String toString() {
       return "{TipoInt}";
   }
}
