/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.definiciones;

import ast.AbstractAST;

public abstract class AbstractDefinicion extends AbstractAST implements Definicion {
	
	protected int direccion;
	
	 @Override
	    public int getDireccion() {
		return direccion;
	    }

	    @Override
	    public void setDireccion(int direccion) {
		this.direccion = direccion;

	    }

}
