/**
 * Tutorial de Diseño de Lenguajes de Programación
 * @author Raúl Izquierdo
 */

package semantic;

import java.util.HashMap;
import java.util.Map;

import ast.AST;
import ast.Position;
import ast.definiciones.DefCampoStruct;
import ast.definiciones.DefFuncion;
import ast.definiciones.DefStruct;
import ast.definiciones.DefVariable;
import ast.definiciones.Definicion;
import ast.expresiones.Expresion;
import ast.expresiones.ExpresionInvocacion;
import ast.expresiones.Variable;
import ast.sentencias.InvocacionProcedimiento;
import ast.tipos.TipoStruct;
import main.ErrorManager;
import visitor.DefaultVisitor;

public class Identification extends DefaultVisitor {

	ContextMap<String, DefVariable> variables = new ContextMap<String, DefVariable>();
	private Map<String, DefStruct> estructuras = new HashMap<String, DefStruct>();
	private Map<String, DefFuncion> funciones = new HashMap<String, DefFuncion>();
	private Map<String, DefCampoStruct> campos = new HashMap<String, DefCampoStruct>();

	public Identification(ErrorManager errorManager) {
		this.errorManager = errorManager;
	}

//	class DefVariable { String nombre;  Tipo tipo; }
	public Object visit(DefVariable node, Object param) {

		// super.visit(node, param);

		Definicion defVariable = variables.getFromTop(node.getNombre());
		if (defVariable == null)
			variables.put(node.getNombre(), node);
		else
			predicado(false, "La variable " + node.getNombre() + " ya esta definida.", node.getStart());

		super.visit(node, param);

		return null;
	}

//	class Variable { String nombre; }
	public Object visit(Variable node, Object param) {

		DefVariable defVariable = variables.getFromAny(node.getNombre());
		if (defVariable != null)
			node.setDefinicion(defVariable); // enlazo referencia con definicion
		else
			predicado(false, "la variable " + node.getNombre() + " no esta definicida.", node.getStart());

		return null;
	}

	// class DefFuncion { String nombre; Tipo tipo; List<Definicion>
	// defVariableLocal; List<Sentencia> sentencias; }
	public Object visit(DefFuncion node, Object param) {
		
		//aqui buscas por key y metes valor al map
		DefFuncion defFuncion = funciones.get(node.getNombre());
		if(defFuncion == null)
			funciones.put(node.getNombre(), node);
		else
			predicado(false, "la funcion "+node.getNombre()+" ya esta definida");
		
		//aqui lo de antes lo insertas en el contexto
		variables.set(); //inserto el hashmap en el contexto
		super.visit(node, param); //sigo recorriendo las varlocales y sentencias
		variables.reset();  //saco de la pila

		return null;
	}

//	class InvocacionProcedimiento { String nombre;  List<Expresion> argumentos; }
	public Object visit(InvocacionProcedimiento node, Object param) {

		DefFuncion defFuncion = funciones.get(node.getNombre());

		if (defFuncion != null) {
			
		    node.setDefinicion(defFuncion); // Enlazar referencia con definici�n
		} else {
		    predicado(false, "Procedimiento '" + node.getNombre() + "' no definido", node.getStart());
		}
		super.visit(node, param);  //recorre los argumentos

		return null;
	}
	
//	class ExpresionInvocacion { String nombre;  List<Expresion> argumentos; }
	public Object visit(ExpresionInvocacion node, Object param) {

		DefFuncion defFuncion = funciones.get(node.getNombre());

		if (defFuncion != null) {
		    node.setDefinicion(defFuncion); // Enlazar referencia con definici�n
		} else {
		    predicado(false, "Funci�n '" + node.getNombre() + "' no definida", node.getStart());
		}
		super.visit(node, param);

		return null;
	}
	
//	class DefStruct { String nombre;  List<Definicion> campos;  Tipo tipo; }
	public Object visit(DefStruct node, Object param) {
		
		//creo el objeto 
		DefStruct structs = estructuras.get(node.getNombre());

		if (structs == null) { //busco la estructura por el nombre
			estructuras.put(node.getNombre(), node);
		} else {
		    predicado(false, "La estructura '" + node.getNombre() + "' ya est� definida", node.getStart());
		}

		variables.set(); //inserto la estructura en el contextMap
		super.visit(node, param);  //recorre los campos
		variables.reset();

		return null;
	}

	//	class DefCampoStruct { String nombre;  Tipo tipo; }
	public Object visit(DefCampoStruct node, Object param) {

		DefCampoStruct campo = campos.get(node.getNombre());
		
		if (campo == null) {
		    campos.put(node.getNombre(), node);
		} else {
		    predicado(false, "El campo del struct '" + node.getNombre() + "' ya est� definido", node.getStart());
		}

		super.visit(node, param);

		return null;
	}
	
//	class TipoStruct { String identificador; }
	public Object visit(TipoStruct node, Object param) {
		
		DefStruct defEstructura = estructuras.get(node.getIdentificador()); //el tipo es el nombre

		if (defEstructura != null) {
		    node.setDefinicion(defEstructura); // Enlazar referencia con definici�n
		} else {
		    predicado(false, "Estructura '" + node.getIdentificador() + "' no definida", node.getStart());
		}
		super.visit(node, param);
		
		return null;
	}

	// Métodos auxiliares recomendados (opcionales) -------------

	/**
	 * predicado. Método auxiliar para implementar los predicados. Borrar si no se
	 * quiere usar.
	 *
	 * Ejemplos de uso (suponiendo que existe un método "esPrimitivo(expr)"):
	 *
	 * 1. predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo
	 * primitivo", expr.getStart()); 2. predicado(esPrimitivo(expr.tipo), "La
	 * expresión debe ser de un tipo primitivo", expr); // Se asume getStart() 3.
	 * predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo
	 * primitivo");
	 *
	 * NOTA: El método getStart() (ejemplo 1) indica la linea/columna del fichero
	 * fuente donde estaba el nodo (y así poder dar información más detallada de
	 * la posición del error). Si se usa VGen, dicho método habrá sido generado
	 * en todos los nodos del AST. No es obligatorio llamar a getStart() (ejemplo
	 * 2), ya que si se pasa el nodo, se usará por defecto dicha posición. Si no
	 * se quiere imprimir la posición del fichero, se puede omitir el tercer
	 * argumento (ejemplo 3).
	 *
	 * @param condition     Debe cumplirse para que no se produzca un error
	 * @param errorMessage  Se imprime si no se cumple la condición
	 * @param posicionError Fila y columna del fichero donde se ha producido el
	 *                      error.
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
