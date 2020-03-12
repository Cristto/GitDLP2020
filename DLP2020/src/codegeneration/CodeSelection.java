/**
 * Tutorial de Diseño de Lenguajes de Programación
 * @author Raúl Izquierdo
 */

package codegeneration;

import java.io.PrintWriter;
import java.io.Writer;

import ast.AST;
import ast.Position;
import visitor.DefaultVisitor;

public class CodeSelection extends DefaultVisitor {

    public CodeSelection(Writer writer, String sourceFile) {
        this.writer = new PrintWriter(writer);
        this.sourceFile = sourceFile;
    }

    // # ----------------------------------------------------------
    /*
     * Poner aquí los visit.
     *
     * Si se ha usado VGen, solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
     */

    // Ejemplo:
    //
    // public Object visit(Program node, Object param) {
    //      out("#source \"" + sourceFile + "\"");
    //      out("call main");
    //      out("halt");
    //
    //      super.visit(node, param); // Recorrer los hijos
    //      return null;
    // }

    // # ----------------------------------------------------------
    // Métodos auxiliares recomendados (opcionales) -------------

    // Imprime una línea en el fichero de salida
    private void out(String instruction) {
        writer.println(instruction);
    }

    // Imprime la directiva `#line` junto con la línea del fichero de entrada donde estaba el nodo
    // indicado
    private void line(AST node) {
        line(node.getEnd());
    }

    private void line(Position pos) {
        out("\n#line " + pos.getLine());
    }

    private PrintWriter writer;
    private String sourceFile;
}
