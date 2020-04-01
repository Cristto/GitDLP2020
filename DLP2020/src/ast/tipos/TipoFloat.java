/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.tipos;

import visitor.Visitor;

//	TipoFloat:Tipo -> 

public class TipoFloat extends AbstractTipo {

	private static TipoFloat instance = new TipoFloat();

    private TipoFloat() {

    }

    public static TipoFloat getInstance() {

	return instance;

    }

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}
	
	@Override
    public String getNombreMapl() {
	return "float";
    }
}
