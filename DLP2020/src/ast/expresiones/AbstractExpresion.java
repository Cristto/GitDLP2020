/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import ast.AbstractAST;
import ast.tipos.Tipo;

public abstract class AbstractExpresion extends AbstractAST implements Expresion {
	
	private boolean lvalue;
    private Tipo tipo;

    @Override
    public void setLvalue(boolean lvalue) {
	this.lvalue = lvalue;

    }

    @Override
    public boolean isLvalue() {
	return lvalue;
    }

    @Override
    public Tipo getTipo() {
	return tipo;
    }

    @Override
    public void setTipo(Tipo tipo) {
	this.tipo = tipo;

}

}
