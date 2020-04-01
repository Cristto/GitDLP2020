/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast.definiciones;

import ast.AST;
import ast.tipos.Tipo;

public interface Definicion extends AST {
	
	String getNombre();

    void setNombre(String nombre);

    Tipo getTipo();

    void setTipo(Tipo tipo);
    
    int getDireccion();
    
    void setDireccion(int direccion);

}
