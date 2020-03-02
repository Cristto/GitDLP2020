/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package visitor;

import ast.*;

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
	public Object visit(TipoDouble node, Object param);
	public Object visit(TipoChar node, Object param);
	public Object visit(TipoArray node, Object param);
	public Object visit(TipoVoid node, Object param);
	public Object visit(TipoFuncion node, Object param);
}
