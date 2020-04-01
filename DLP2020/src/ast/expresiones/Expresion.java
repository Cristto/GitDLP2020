/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.expresiones;

import ast.AST;
import ast.tipos.Tipo;

public interface Expresion extends AST {
	
	boolean isLvalue();

    void setLvalue(boolean lvalue);

    Tipo getTipo();

    void setTipo(Tipo tipo);

}
