/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package visitor;

import ast.Programa;
import ast.definiciones.DefCampoStruct;
import ast.definiciones.DefFuncion;
import ast.definiciones.DefStruct;
import ast.definiciones.DefVariable;
import ast.expresiones.AccesoArray;
import ast.expresiones.AccesoStruct;
import ast.expresiones.Asignacion;
import ast.expresiones.CTE_Char;
import ast.expresiones.CTE_Entera;
import ast.expresiones.CTE_Real;
import ast.expresiones.ExprAritmetica;
import ast.expresiones.ExprComparacion;
import ast.expresiones.ExprLogica;
import ast.expresiones.ExprMenosUnario;
import ast.expresiones.ExprNot;
import ast.expresiones.ExpresionCast;
import ast.expresiones.ExpresionInvocacion;
import ast.expresiones.Variable;
import ast.sentencias.If;
import ast.sentencias.IfElse;
import ast.sentencias.InvocacionProcedimiento;
import ast.sentencias.Print;
import ast.sentencias.PrintLn;
import ast.sentencias.PrintSp;
import ast.sentencias.Read;
import ast.sentencias.Return;
import ast.sentencias.While;
import ast.tipos.TipoArray;
import ast.tipos.TipoChar;
import ast.tipos.TipoFloat;
import ast.tipos.TipoFuncion;
import ast.tipos.TipoInt;
import ast.tipos.TipoStruct;
import ast.tipos.TipoVoid;

public interface Visitor {
	public Object visit(Programa node, Object param);
	public Object visit(DefVariable node, Object param);
	public Object visit(DefFuncion node, Object param);
	public Object visit(DefStruct node, Object param);
	public Object visit(DefCampoStruct node, Object param);
	public Object visit(Print node, Object param);
	public Object visit(PrintLn node, Object param);
	public Object visit(PrintSp node, Object param);
	public Object visit(Asignacion node, Object param);
	public Object visit(If node, Object param);
	public Object visit(IfElse node, Object param);
	public Object visit(While node, Object param);
	public Object visit(InvocacionProcedimiento node, Object param);
	public Object visit(Return node, Object param);
	public Object visit(Read node, Object param);
	public Object visit(ExprAritmetica node, Object param);
	public Object visit(ExprComparacion node, Object param);
	public Object visit(ExprLogica node, Object param);
	public Object visit(ExprMenosUnario node, Object param);
	public Object visit(ExprNot node, Object param);
	public Object visit(AccesoArray node, Object param);
	public Object visit(ExpresionCast node, Object param);
	public Object visit(AccesoStruct node, Object param);
	public Object visit(ExpresionInvocacion node, Object param);
	public Object visit(CTE_Real node, Object param);
	public Object visit(CTE_Entera node, Object param);
	public Object visit(CTE_Char node, Object param);
	public Object visit(Variable node, Object param);
	public Object visit(TipoInt node, Object param);
	public Object visit(TipoStruct node, Object param);
	public Object visit(TipoFloat node, Object param);
	public Object visit(TipoChar node, Object param);
	public Object visit(TipoArray node, Object param);
	public Object visit(TipoVoid node, Object param);
	public Object visit(TipoFuncion node, Object param);
}
