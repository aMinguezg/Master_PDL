package com.compiladores.web.html.main;

import java.io.FileReader;
import java.io.IOException;

import com.compiladores.web.html.parser.Lexicon;
import com.compiladores.web.html.parser.Token;
import com.compiladores.web.html.parser.TokensId;
import com.compiladores.web.html.visitor.*;



public class Main {

	public static void main(String[] args) throws IOException {
		FileReader filereader = new FileReader ("resources/EX4.html");
		Lexicon lex = new Lexicon(filereader);
		listaTokens(lex);
		/*Parser parser = new Parser (lex);
		AstCss ast = parser.parse();
		PrintCssAstVisitor visitor = new PrintCssAstVisitor();
		String s = (String) ast.accept(visitor, null);
		System.out.println(s);*/
	}

	//Auxiliares
	//Lista de Tokens
	static void listaTokens (Lexicon lex) {
		Token t = lex.getToken();
		while (t.getToken() != TokensId.EOF) {
			System.out.println(t.toString());
			t = lex.getToken();
		}
		System.out.println ("\nFin de fichero. \n"+t.toString());	
	}
}
