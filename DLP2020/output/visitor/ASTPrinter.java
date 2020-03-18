/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package visitor;

import java.io.*;

import ast.*;
import java.util.*;

/**
 * ASTPrinter. Utilidad que ayuda a validar un arbol AST:
 * - Muestra la estructura del árbol en HTML.
 * - Destaca los hijos/propiedades a null.
 * - Muestra a qué texto apuntan las posiciones de cada nodo (linea/columna)
 *      ayudando a decidir cual de ellas usar en los errores y generación de código.
 *
 * Esta clase se genera con VGen. El uso de esta clase es opcional (puede eliminarse del proyecto).
 *
 */
public class ASTPrinter extends DefaultVisitor {

    /**
     * toHtml. Muestra la estructura del AST indicando qué hay en las posiciones
     * (línea y columna) de cada nodo.
     *
     * @param sourceFile El fichero del cual se ha obtenido el AST
     * @param raiz       El AST creado a partir de sourceFile
     * @param filename   Nombre del fichero HMTL a crear con la traza del AST
     */

    public static void toHtml(String sourceFile, AST raiz, String filename) {
        toHtml(sourceFile, raiz, filename, 4);
    }

    public static void toHtml(AST raiz, String filename) {
        toHtml(null, raiz, filename);
    }

    // tabWidth deberían ser los espacios correspondientes a un tabulador en eclipse.
    // Normalmente no sería necesario especificarlo. Usar mejor los dos métodos anteriores.

    public static void toHtml(String sourceFile, AST raiz, String filename, int tabWidth) {
        try {
            PrintWriter writer = new PrintWriter(
                    new FileWriter(filename.endsWith(".html") ? filename : filename + ".html"));
            generateHeader(writer);
            writer.println("[ASTPrinter] -------------------------------- line:col  line:col");
            if (raiz != null) {
                ASTPrinter tracer = new ASTPrinter(writer, loadLines(sourceFile, tabWidth));
                raiz.accept(tracer, new Integer(0));
            } else
                writer.println("raiz == null");
            writer.println(ls + ls + "[ASTPrinter] --------------------------------");
            generateFooter(writer);
            writer.close();
            System.out.println(ls + "ASTPrinter: Fichero '" + filename
                    + ".html' generado. Abra dicho fichero para validar el AST generado.");
        } catch (IOException e) {
            System.out.println(ls + "ASTPrinter: No se ha podido crear el fichero " + filename);
            e.printStackTrace();
        }
    }

    private static void generateHeader(PrintWriter writer) {
        writer.println("<html>\r\n"
            + "<head>\r\n"
            + "<meta charset=\"utf-8\" />\r\n"
            + "<style type=\"text/css\">\r\n"
            + ".value { font-weight: bold; }\r\n"
            + ".dots { color: #bebdbd; }\r\n"
            + ".type { color: #BBBBBB; }\r\n"
            + ".pos { color: #CCCCCC; }\r\n"
            + ".sourceText { color: #BBBBBB; }\r\n"
            + ".posText {\r\n" + "	color: #BBBBBB;\r\n"
            + "	text-decoration: underline; font-weight: bold;\r\n"
            + "}\r\n"
            + ".null {\r\n"
            + "	color: #FF0000;\r\n"
            + "	font-weight: bold;\r\n"
            + "	font-style: italic;\r\n" + "}\r\n"
            + "</style>\r\n" + "</head>\r\n" + "\r\n"
            + "<body><pre>");
    }

    private static void generateFooter(PrintWriter writer) {
        writer.println("</pre>\r\n" + "</body>\r\n" + "</html>");
    }

    private ASTPrinter(PrintWriter writer, List<String> sourceLines) {
        this.writer = writer;
        this.sourceLines = sourceLines;
    }

    // ----------------------------------------------
	//	class Programa { List<Definicion> definiciones; }
	public Object visit(Programa node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Programa", node, false);

		visit(indent + 1, "definiciones", "List<Definicion>",node.getDefiniciones());
		return null;
	}

	//	class DefVariable { String nombre;  Tipo tipo; }
	public Object visit(DefVariable node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefVariable", node, false);

		print(indent + 1, "nombre", "String", node.getNombre());
		visit(indent + 1, "tipo", "Tipo",node.getTipo());
		return null;
	}

	//	class DefFuncion { String nombre;  Tipo tipo;  List<Definicion> defVariableLocal;  List<Sentencia> sentencias; }
	public Object visit(DefFuncion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefFuncion", node, false);

		print(indent + 1, "nombre", "String", node.getNombre());
		visit(indent + 1, "tipo", "Tipo",node.getTipo());
		visit(indent + 1, "defVariableLocal", "List<Definicion>",node.getDefVariableLocal());
		visit(indent + 1, "sentencias", "List<Sentencia>",node.getSentencias());
		return null;
	}

	//	class DefStruct { String nombre;  List<Definicion> campos;  Tipo tipo; }
	public Object visit(DefStruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefStruct", node, false);

		print(indent + 1, "nombre", "String", node.getNombre());
		visit(indent + 1, "campos", "List<Definicion>",node.getCampos());
		visit(indent + 1, "tipo", "Tipo",node.getTipo());
		return null;
	}

	//	class DefCampoStruct { String nombre;  Tipo tipo; }
	public Object visit(DefCampoStruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefCampoStruct", node, false);

		print(indent + 1, "nombre", "String", node.getNombre());
		visit(indent + 1, "tipo", "Tipo",node.getTipo());
		return null;
	}

	//	class Print { Expresion expresion; }
	public Object visit(Print node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Print", node, false);

		visit(indent + 1, "expresion", "Expresion",node.getExpresion());
		return null;
	}

	//	class PrintLn { Expresion expresion; }
	public Object visit(PrintLn node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "PrintLn", node, false);

		visit(indent + 1, "expresion", "Expresion",node.getExpresion());
		return null;
	}

	//	class PrintSp { Expresion expresion; }
	public Object visit(PrintSp node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "PrintSp", node, false);

		visit(indent + 1, "expresion", "Expresion",node.getExpresion());
		return null;
	}

	//	class Asignacion { Expresion expIzq;  Expresion expDrcha; }
	public Object visit(Asignacion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Asignacion", node, false);

		visit(indent + 1, "expIzq", "Expresion",node.getExpIzq());
		visit(indent + 1, "expDrcha", "Expresion",node.getExpDrcha());
		return null;
	}

	//	class If { Expresion condicion;  List<Sentencia> sentenciasIf; }
	public Object visit(If node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "If", node, false);

		visit(indent + 1, "condicion", "Expresion",node.getCondicion());
		visit(indent + 1, "sentenciasIf", "List<Sentencia>",node.getSentenciasIf());
		return null;
	}

	//	class IfElse { Expresion condicion;  List<Sentencia> sentenciasIf;  List<Sentencia> sentenciasElse; }
	public Object visit(IfElse node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "IfElse", node, false);

		visit(indent + 1, "condicion", "Expresion",node.getCondicion());
		visit(indent + 1, "sentenciasIf", "List<Sentencia>",node.getSentenciasIf());
		visit(indent + 1, "sentenciasElse", "List<Sentencia>",node.getSentenciasElse());
		return null;
	}

	//	class While { Expresion condicion;  List<Sentencia> sentencias; }
	public Object visit(While node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "While", node, false);

		visit(indent + 1, "condicion", "Expresion",node.getCondicion());
		visit(indent + 1, "sentencias", "List<Sentencia>",node.getSentencias());
		return null;
	}

	//	class InvocacionProcedimiento { String nombre;  List<Expresion> argumentos; }
	public Object visit(InvocacionProcedimiento node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "InvocacionProcedimiento", node, false);

		print(indent + 1, "nombre", "String", node.getNombre());
		visit(indent + 1, "argumentos", "List<Expresion>",node.getArgumentos());
		return null;
	}

	//	class Return { Expresion expresion; }
	public Object visit(Return node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Return", node, false);

		visit(indent + 1, "expresion", "Expresion",node.getExpresion());
		return null;
	}

	//	class Read { Expresion expresion; }
	public Object visit(Read node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Read", node, false);

		visit(indent + 1, "expresion", "Expresion",node.getExpresion());
		return null;
	}

	//	class ExprAritmetica { Expresion expIzq;  String operator;  Expresion expDrcha; }
	public Object visit(ExprAritmetica node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExprAritmetica", node, false);

		visit(indent + 1, "expIzq", "Expresion",node.getExpIzq());
		print(indent + 1, "operator", "String", node.getOperator());
		visit(indent + 1, "expDrcha", "Expresion",node.getExpDrcha());
		return null;
	}

	//	class ExprComparacion { Expresion expIzq;  String operator;  Expresion expDrcha; }
	public Object visit(ExprComparacion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExprComparacion", node, false);

		visit(indent + 1, "expIzq", "Expresion",node.getExpIzq());
		print(indent + 1, "operator", "String", node.getOperator());
		visit(indent + 1, "expDrcha", "Expresion",node.getExpDrcha());
		return null;
	}

	//	class ExprLogica { Expresion expIzq;  String operator;  Expresion expDrcha; }
	public Object visit(ExprLogica node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExprLogica", node, false);

		visit(indent + 1, "expIzq", "Expresion",node.getExpIzq());
		print(indent + 1, "operator", "String", node.getOperator());
		visit(indent + 1, "expDrcha", "Expresion",node.getExpDrcha());
		return null;
	}

	//	class ExprMenosUnario { Expresion expr; }
	public Object visit(ExprMenosUnario node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExprMenosUnario", node, false);

		visit(indent + 1, "expr", "Expresion",node.getExpr());
		return null;
	}

	//	class ExprNot { Expresion expr; }
	public Object visit(ExprNot node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExprNot", node, false);

		visit(indent + 1, "expr", "Expresion",node.getExpr());
		return null;
	}

	//	class AccesoArray { Expresion identificador;  Expresion posicion; }
	public Object visit(AccesoArray node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "AccesoArray", node, false);

		visit(indent + 1, "identificador", "Expresion",node.getIdentificador());
		visit(indent + 1, "posicion", "Expresion",node.getPosicion());
		return null;
	}

	//	class ExpresionCast { Tipo tipoCast;  Expresion expresion; }
	public Object visit(ExpresionCast node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExpresionCast", node, false);

		visit(indent + 1, "tipoCast", "Tipo",node.getTipoCast());
		visit(indent + 1, "expresion", "Expresion",node.getExpresion());
		return null;
	}

	//	class AccesoStruct { Expresion expresion;  String identificador; }
	public Object visit(AccesoStruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "AccesoStruct", node, false);

		visit(indent + 1, "expresion", "Expresion",node.getExpresion());
		print(indent + 1, "identificador", "String", node.getIdentificador());
		return null;
	}

	//	class ExpresionInvocacion { String nombre;  List<Expresion> argumentos; }
	public Object visit(ExpresionInvocacion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExpresionInvocacion", node, false);

		print(indent + 1, "nombre", "String", node.getNombre());
		visit(indent + 1, "argumentos", "List<Expresion>",node.getArgumentos());
		return null;
	}

	//	class CTE_Real { String valor; }
	public Object visit(CTE_Real node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "CTE_Real", node, "valor", node.getValor());
		return null;
	}

	//	class CTE_Entera { String valor; }
	public Object visit(CTE_Entera node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "CTE_Entera", node, "valor", node.getValor());
		return null;
	}

	//	class CTE_Char { String lexema; }
	public Object visit(CTE_Char node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "CTE_Char", node, "lexema", node.getLexema());
		return null;
	}

	//	class Variable { String nombre; }
	public Object visit(Variable node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "Variable", node, "nombre", node.getNombre());
		return null;
	}

	//	class TipoInt {  }
	public Object visit(TipoInt node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoInt", node, true);

		return null;
	}

	//	class TipoStruct { String identificador; }
	public Object visit(TipoStruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "TipoStruct", node, "identificador", node.getIdentificador());
		return null;
	}

	//	class TipoFloat {  }
	public Object visit(TipoFloat node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoFloat", node, true);

		return null;
	}

	//	class TipoChar {  }
	public Object visit(TipoChar node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoChar", node, true);

		return null;
	}

	//	class TipoArray { Integer size;  Tipo tipoDe; }
	public Object visit(TipoArray node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoArray", node, false);

		print(indent + 1, "size", "Integer", node.getSize());
		visit(indent + 1, "tipoDe", "Tipo",node.getTipoDe());
		return null;
	}

	//	class TipoVoid {  }
	public Object visit(TipoVoid node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoVoid", node, true);

		return null;
	}

	//	class TipoFuncion { Tipo tipoRetorno;  List<Definicion> defParametros; }
	public Object visit(TipoFuncion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoFuncion", node, false);

		visit(indent + 1, "tipoRetorno", "Tipo",node.getTipoRetorno());
		visit(indent + 1, "defParametros", "List<Definicion>",node.getDefParametros());
		return null;
	}


	// -----------------------------------------------------------------
	// Métodos invocados desde los métodos visit -----------------------

	private void printName(int indent, String name, AST node, boolean empty) {
		String text = ls + tabula(indent) + name + " &rarr;  ";
		text = String.format("%1$-" + 93 + "s", text);
		if (empty)
			text = text.replace(name, valueTag(name));
		writer.print(text + getPosition(node));
	}

	private void print(int indent, String name, String type, Object value) {
		write(indent, formatValue(value) + "  " + typeTag(type));
	}

	private void print(int indent, String attName, String type, List<? extends Object> children) {
		write(indent, attName + "  " + typeTag(type) + " = ");
		if (children != null)
			for (Object child : children)
				write(indent + 1, formatValue(child));
		else
			writer.print(" " + valueTag(null));
	}

	// Versión compacta de una linea para nodos que solo tienen un atributo String
	private void printCompact(int indent, String nodeName, AST node, String attName, Object value) {
		String fullName = nodeName + '.' + attName;
		String text = ls + tabula(indent) + '\"' + value + "\"  " + fullName;
		text = String.format("%1$-" + 88 + "s", text);
		// text = text.replace(value.toString(), valueTag(value));
		text = text.replace(fullName, typeTag(fullName));
		writer.print(text + getPosition(node));
	}

	private void visit(int indent, String attName, String type, List<? extends AST> children) {
		write(indent, attName + "  " + typeTag(type) + " = ");
		if (children != null)
			for (AST child : children)
				child.accept(this, indent + 1);
		else
			writer.print(" " + valueTag(null));
	}

	private void visit(int indent, String attName, String type, AST child) {
		if (child != null)
			child.accept(this, new Integer(indent));
		else
			write(indent, valueTag(null) + "  " + attName + ':' + typeTag(type));
	}

	// -----------------------------------------------------------------
	// Métodos auxiliares privados -------------------------------------

	private void write(int indent, String text) {
		writer.print(ls + tabula(indent) + text);
	}

	private static String tabula(int count) {
		StringBuffer cadena = new StringBuffer("<span class=\"dots\">");
		for (int i = 0; i < count; i++)
			cadena.append(i % 2 == 0 && i > 0 ? "|  " : ".  ");
		return cadena.toString() + "</span>";
	}

	private String typeTag(String type) {
		if (type.equals("String"))
			return "";
		return "<span class=\"type\">" + type.replace("<", "&lt;").replace(">", "&gt;") + "</span>";
	}

	private String valueTag(Object value) {
		if (value == null)
			return "<span class=\"null\">null</span>";
		return "<span class=\"value\">" + value + "</span>";
	}

	private String formatValue(Object value) {
		String text = valueTag(value);
		if (value instanceof String)
			text = "\"" + text + '"';
		return text;
	}


	// -----------------------------------------------------------------
	// Métodos para mostrar las Posiciones -----------------------------

	private String getPosition(AST node) {
		String text = node.getStart() + "  " + node.getEnd();
		text = "<span class=\"pos\">" + String.format("%1$-" + 13 + "s", text) + "</span>";
		text = text.replace("null", "<span class=\"null\">null</span>");
		String sourceText = findSourceText(node);
		if (sourceText != null)
			text += sourceText;
		return text;
	}

	private String findSourceText(AST node) {
		if (sourceLines == null)
			return null;

		Position start = node.getStart();
		Position end = node.getEnd();
		if (start == null || end == null)
			return null;

		String afterText, text, beforeText;
		if (start.getLine() == end.getLine()) {
			String line = sourceLines.get(start.getLine() - 1);
			afterText = line.substring(0, start.getColumn() - 1);
			text = line.substring(start.getColumn() - 1, end.getColumn());
			beforeText = line.substring(end.getColumn());
		} else {
			String firstLine = sourceLines.get(start.getLine() - 1);
			String lastLine = sourceLines.get(end.getLine() - 1);

			afterText = firstLine.substring(0, start.getColumn() - 1);

			text = firstLine.substring(start.getColumn() - 1);
			text += "</span><span class=\"sourceText\">" + " ... " + "</span><span class=\"posText\">";
			text += lastLine.substring(0, end.getColumn()).replaceAll("^\\s+", "");

			beforeText = lastLine.substring(end.getColumn());
		}
		return "<span class=\"sourceText\">" + afterText.replaceAll("^\\s+", "")
				+ "</span><span class=\"posText\">" + text
				+ "</span><span class=\"sourceText\">" + beforeText + "</span>";
	}

	private static List<String> loadLines(String sourceFile, int tabWidth) {
		if (sourceFile == null)
			return null;
		try {
			String spaces = new String(new char[tabWidth]).replace("\0", " ");

			List<String> lines = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(sourceFile));
			String line;
			while ((line = br.readLine()) != null) {
			//	lines.add(line.replace("\t", spaces)); // Si tab = 4 espaces (Eclipse)
				lines.add(line);
            }
			br.close();
			return lines;
		} catch (FileNotFoundException e) {
			System.out.println("Warning. No se pudo encontrar el fichero fuente '" + sourceFile + "'. No se mostrará informaicón de posición.");
			return null;
		} catch (IOException e) {
			System.out.println("Warning. Error al leer del fichero fuente '" + sourceFile + "'. No se mostrará informaicón de posición.");
			return null;
		}
	}


	private List<String> sourceLines;
	private static String ls = System.getProperty("line.separator");
	private PrintWriter writer;
}
