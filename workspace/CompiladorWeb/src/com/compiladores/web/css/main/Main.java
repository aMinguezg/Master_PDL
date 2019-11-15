package com.compiladores.web.css.main;

import java.io.FileReader;
import java.io.IOException;

import com.compiladores.web.css.parser.Lexicon;
import com.compiladores.web.css.parser.Token;
import com.compiladores.web.css.parser.TokensId;

public class Main {

	public static void main(String[] args) throws IOException {
		FileReader filereader = new FileReader ("resources/EX1.css");
		Lexicon lex = new Lexicon(filereader);
		listaTokens(lex);
		//Parser parser = new Parser (lex);
		//AstCss ast = parser.parse();
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
