/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.tipos;

import visitor.Visitor;

//	TipoChar:Tipo -> 

public class TipoChar extends AbstractTipo {
	
	private static TipoChar instance = new TipoChar();

    private TipoChar() {

    }

    public static TipoChar getInstance() {

	return instance;

    }

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}
	
	@Override
    public String getNombreMapl() {
	return "byte";
    }


	public String toString() {
       return "{TipoChar}";
   }
}
