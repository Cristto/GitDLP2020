/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	TipoFuncion:Tipo -> tipoRetorno:Tipo  defParametros:Definicion*

public class TipoFuncion extends AbstractTipo {

	public TipoFuncion(Tipo tipoRetorno, List<Definicion> defParametros) {
		this.tipoRetorno = tipoRetorno;
		this.defParametros = defParametros;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(tipoRetorno, defParametros);
	}

	@SuppressWarnings("unchecked")
	public TipoFuncion(Object tipoRetorno, Object defParametros) {
		this.tipoRetorno = (Tipo) ((tipoRetorno instanceof ParserRuleContext) ? getAST(tipoRetorno) : tipoRetorno);
		this.defParametros = (List<Definicion>) defParametros;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(tipoRetorno, defParametros);
	}

	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}
	public void setTipoRetorno(Tipo tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public List<Definicion> getDefParametros() {
		return defParametros;
	}
	public void setDefParametros(List<Definicion> defParametros) {
		this.defParametros = defParametros;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Tipo tipoRetorno;
	private List<Definicion> defParametros;

	public String toString() {
       return "{tipoRetorno:" + getTipoRetorno() + ", defParametros:" + getDefParametros() + "}";
   }
}
