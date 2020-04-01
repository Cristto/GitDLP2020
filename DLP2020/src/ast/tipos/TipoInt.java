/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.tipos;

import visitor.Visitor;

//	TipoInt:Tipo -> 

public class TipoInt extends AbstractTipo {

	private static TipoInt instance = new TipoInt();

    private TipoInt() {

    }

    public static TipoInt getInstance() {

	return instance;

    }

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}
	
	@Override
    public String getNombreMapl() {
	return "int";
    }


	public String toString() {
       return "{TipoInt}";
   }
}
