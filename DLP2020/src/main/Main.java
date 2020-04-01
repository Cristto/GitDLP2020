/**
 * Tutorial de Diseño de Lenguajes de Programación
 * @author Raúl Izquierdo
 */

package main;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.AST;
import codegeneration.CodeGeneration;
import parser.GrammarLexer;
import parser.GrammarParser;
import semantic.SemanticAnalisys;
import visitor.ASTPrinter;

/**
 * Clase que inicia el compilador e invoca a todas sus fases.
 *
 * Normalmente, no será necesario modificar este fichero. En su lugar, modificar
 * los ficheros de cada fase (los cuales son llamados desde aquí):
 * - Para Análisis Léxico: 'Lexico.g4'.
 * - Para Análisis Sintáctico: 'Grammar.g4'.
 * - Para Análisis Semántico: 'Identification.java' y 'TypeChecking.java'.
 * - Para Generación de Código: 'MemoryAllocation.java' y 'CodeSelection.java'.
 */
public class Main {
   // public static final String program = "entradas/source.txt"; // Fichero de prueba durante el desarrollo
   // public static final String program = "tests/1. Funciones.txt";
   // public static final String program = "tests/2. Estructuras.txt";
   // public static final String program = "tests/3. Variables.txt";  
	public static final String program = "tests/Test Comprobaci�n de Tipos.txt";
    
    public static void main(String[] args) throws Exception {
        ErrorManager errorManager = new ErrorManager();

        AST ast = compile(program, errorManager); // Poner args[0] en vez de "program" en la versión final
        if (errorManager.errorsCount() == 0)
            System.out.println("El programa se ha compilado correctamente.");

        ASTPrinter.toHtml(program, ast, "AST"); // Utilidad generada por VGen (opcional)
    }

    /**
     * Método que coordina todas las fases del compilador
     */
    public static AST compile(String sourceName, ErrorManager errorManager) throws Exception {

        // 1. Fases de Análisis Léxico y Sintáctico
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName(sourceName));
        GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));

        AST ast = null;

        // IMPORTANTE: Cuando se genere el AST, INTERCAMBIAR las dos líneas siguientes:
        //parser.start();
         ast = parser.start().ast;

        if (parser.getNumberOfSyntaxErrors() > 0 || ast == null) { // Hay errores o el AST no se ha implementado aún
            errorManager.notify("El AST no ha sido creado.");
            return null;
        }

        // 2. Fase de Análisis Semántico
        SemanticAnalisys analyzer = new SemanticAnalisys(errorManager);
        analyzer.analize(ast);
        if (errorManager.errorsCount() > 0)
            return ast;

        // 3. Fase de Generación de Código
        File sourceFile = new File(sourceName);
        Writer out = new FileWriter(new File(sourceFile.getParent(), "output.txt"));

        CodeGeneration generator = new CodeGeneration();
        generator.generate(sourceFile.getName(), ast, out);
        out.close();

        return ast;
    }
}
