/**
 * Tutorial de Diseño de Lenguajes de Programación
 * @author Raúl Izquierdo
 */

package semantic;

import ast.AST;
import ast.Position;
import ast.definiciones.DefVariable;
import ast.definiciones.Definicion;
import ast.expresiones.Variable;
import main.ErrorManager;
import visitor.DefaultVisitor;

public class Identification extends DefaultVisitor {
	
	ContextMap<String, DefVariable> variables = new ContextMap<String, DefVariable>();

    public Identification(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    
//	class DefVariable { String nombre;  Tipo tipo; }
	public Object visit(DefVariable node, Object param) {

		// super.visit(node, param);

		Definicion defVariable = variables.getFromTop(node.getNombre());
		
		

		return null;
	}
	
//	class Variable { String lexema; }
	public Object visit(Variable node, Object param) {
		return null;
	}
    

    // Métodos auxiliares recomendados (opcionales) -------------

    /**
     * predicado. Método auxiliar para implementar los predicados. Borrar si no se quiere usar.
     *
     * Ejemplos de uso (suponiendo que existe un método "esPrimitivo(expr)"):
     *
     *      1. predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo", expr.getStart());
     *      2. predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo", expr); // Se asume getStart()
     *      3. predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo");
     *
     * NOTA: El método getStart() (ejemplo 1) indica la linea/columna del fichero fuente donde estaba el nodo
     * (y así poder dar información más detallada de la posición del error). Si se usa VGen, dicho método
     * habrá sido generado en todos los nodos del AST.
     * No es obligatorio llamar a getStart() (ejemplo 2), ya que si se pasa el nodo, se usará por defecto dicha
     * posición.
     * Si no se quiere imprimir la posición del fichero, se puede omitir el tercer argumento (ejemplo 3).
     *
     * @param condition     Debe cumplirse para que no se produzca un error
     * @param errorMessage  Se imprime si no se cumple la condición
     * @param posicionError Fila y columna del fichero donde se ha producido el error.
     */

    private void predicado(boolean condition, String errorMessage, Position position) {
        if (!condition)
            errorManager.notify("Identification", errorMessage, position);
    }

    private void predicado(boolean condition, String errorMessage, AST node) {
        predicado(condition, errorMessage, node.getStart());
    }

    private void predicado(boolean condition, String errorMessage) {
        predicado(condition, errorMessage, (Position) null);
    }

    private ErrorManager errorManager;
}
