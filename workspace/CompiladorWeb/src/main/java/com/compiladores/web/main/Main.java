package main.java.com.compiladores.web.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import main.java.com.compiladores.web.html.*;
import main.java.com.compiladores.web.html.ast.AstHtml;
import main.java.com.compiladores.web.html.parser.Lexicon;
import main.java.com.compiladores.web.html.parser.Parser;
import main.java.com.compiladores.web.html.visitor.RenderVisitor;
import main.java.com.compiladores.web.render.*;
import main.java.com.compiladores.web.main.Utils;


public class Main 
{

    public static void main( String[] args ) 
        throws IOException
    {
    	Utils utils = new Utils();
        File file = utils.getFileFromResources("EX4.html");
        FileReader fileReader = new FileReader(file);
        
        //Lexico y sintactico
        Lexicon lex = new Lexicon(fileReader);
        Parser parser = new Parser(lex);
        AstHtml astHtml = parser.parse();

        //Renderizar la pagina en modo texto
        RenderVisitor rv = new RenderVisitor(astHtml);
        Pagina pagina = (Pagina) astHtml.accept(rv, null);
        System.out.println(pagina.toString());
    }
}