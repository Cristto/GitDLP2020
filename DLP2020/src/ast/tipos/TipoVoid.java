/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.tipos;

import visitor.Visitor;

//	TipoVoid:Tipo -> 

public class TipoVoid extends AbstractTipo {

	private static TipoVoid instance = new TipoVoid();

    private TipoVoid() {

    }

    public static TipoVoid getInstance() {

	return instance;

    }
	
	
	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}
	
	@Override
    public String getNombreMapl() {
	return "void";
    }


	public String toString() {
       return "{TipoVoid}";
   }
}
