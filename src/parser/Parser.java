package parser;

import java.util.List;

import ast.*;
import java.util.*;

public class Parser {
	
	Lexicon lex;
	boolean errorSint = false;
	
	public Parser (Lexicon lex) {
		this.lex = lex;
	}
	
	public AstCss parse () {
		AstCss ast = null;
		return ast;
	}

	//Gesti�n de Errores Sint�ctico
	void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sint�ctico : "+e+" en la l�nea "+line);
	}
}
