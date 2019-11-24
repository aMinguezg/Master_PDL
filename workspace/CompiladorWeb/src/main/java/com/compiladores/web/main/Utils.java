package main.java.com.compiladores.web.main;

import java.io.File;
import java.net.URL;


public class Utils {

    static void listaTokensHtml(main.java.com.compiladores.web.html.parser.Lexicon lex) {

    	main.java.com.compiladores.web.html.parser.Token t = lex.getToken();

        while (t.getToken() != main.java.com.compiladores.web.html.parser.TokensId.EOF) {
            System.out.println(t.toString());
            t = lex.getToken();
        }

        System.out.println("\nFin de fichero. \n" + t.toString());
    }

    static void listaTokensCss(main.java.com.compiladores.web.css.parser.Lexicon lex) {

    	main.java.com.compiladores.web.css.parser.Token t = lex.getToken();

        while (t.getToken() != main.java.com.compiladores.web.css.parser.TokensId.EOF) {
            System.out.println(t.toString());
            t = lex.getToken();
        }
    }

    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}