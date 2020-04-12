/**
 * Tutorial de Diseño de Lenguajes de Programación
 * @author Raúl Izquierdo
 */

package codegeneration;

import java.util.List;

import ast.Programa;
import ast.definiciones.DefCampoStruct;
import ast.definiciones.DefFuncion;
import ast.definiciones.DefStruct;
import ast.definiciones.DefVariable;
import ast.definiciones.Definicion;
import ast.tipos.TipoArray;
import ast.tipos.TipoChar;
import ast.tipos.TipoFloat;
import ast.tipos.TipoFuncion;
import ast.tipos.TipoInt;
import ast.tipos.TipoStruct;
import ast.tipos.TipoVoid;
import codegeneration.tipo.Funcion;
import visitor.DefaultVisitor;

/**
 * Clase encargada de asignar direcciones a las variables.
 */
public class MemoryAllocation extends DefaultVisitor {

	private int dir;

	@Override
	public Object visit(Programa node, Object param) {
		this.dir = 0;
		return super.visit(node, param);
	}

	@Override
	public Object visit(DefVariable node, Object param) {
		node.setDireccion(dir);
		dir += (int) node.getTipo().accept(this, Funcion.TAM);
		return null;
	}

	@Override
	public Object visit(DefStruct node, Object param) {
		node.getTipo().accept(this, Funcion.TAM);
		return null;
	}

	@Override
	public Object visit(DefCampoStruct node, Object param) {
		node.accept(this, Funcion.TAM);
		return null;
	}

	@Override
	public Object visit(DefFuncion node, Object param) {
		int dirLocal = 0;
		int dirParam = 4;
		Definicion defParam;
		List<Definicion> parametros = ((TipoFuncion) node.getTipo()).getDefParametros();
		for (int i = parametros.size() - 1; i >= 0; i--) {
			defParam = parametros.get(i);
			defParam.setDireccion(dirParam);
			dirParam += (int) defParam.getTipo().accept(this, Funcion.TAM);
		}

		for (Definicion child : node.getDefVariableLocal()) {
			dirLocal -= (int) child.getTipo().accept(this, Funcion.TAM);
			child.setDireccion(dirLocal);
		}
		return null;
	}

	@Override
	public Object visit(TipoInt node, Object param) {
		assert (param == Funcion.TAM);
		return 2;
	}

	@Override
	public Object visit(TipoFloat node, Object param) {
		assert (param == Funcion.TAM);
		return 4;
	}

	@Override
	public Object visit(TipoChar node, Object param) {
		assert (param == Funcion.TAM);
		return 1;
	}

	@Override
	public Object visit(TipoArray node, Object param) {
		assert (param == Funcion.TAM);
		return (int) node.getTipoDe().accept(this, Funcion.TAM) * node.getSize();
	}

	@Override
	public Object visit(TipoFuncion node, Object param) {
		assert (param == Funcion.TAM);
		return node.getTipoRetorno().accept(this, Funcion.TAM);
	}

	@Override
	public Object visit(TipoVoid node, Object param) {
		assert (param == Funcion.TAM);
		return 0;
	}

	@Override
	public Object visit(TipoStruct node, Object param) {
		assert (param == Funcion.TAM);
		int sum = 0;
		for (Definicion c : ((DefStruct) node.getDefinicion()).getCampos()) {
			c.setDireccion(sum);
			sum += (int) c.getTipo().accept(this, Funcion.TAM);
		}

		return sum;
	}

}
