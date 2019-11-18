package com.compiladores.web.css.parser;

import com.compiladores.web.css.ast.AstCss;
import com.compiladores.web.css.ast.Definicion;
import com.compiladores.web.css.ast.Program;
import com.compiladores.web.css.ast.Regla;

import java.util.*;

public class Parser {
	
	Lexicon lex;
	boolean errorSint = false;
	
	public Parser (Lexicon lex) {
		this.lex = lex;
	}
	
	public Program parse () {
		
		Program p = null;
		List<Regla> reglas = new ArrayList<Regla>();
		Token token = lex.getToken();
		
		while(token.getToken() == TokensId.id) {
			lex.returnLastToken();
			Regla regla = parseRegla();
			
			if(regla != null && !errorSint) {
				reglas.add(regla);
			}
		}
		
		if(token.getToken() != TokensId.EOF) {
			errorSintactico(token.getLexeme(), token.getLine());
		}
		return p;
	}
	
	private Regla parseRegla () {
		Regla r = null;
		List<Definicion> definiciones = new ArrayList<Definicion>();
		Token token = lex.getToken();
		return r;
	}
	
	private Definicion parseDefinicion () {
		Definicion d = null;
		return d;
	}
	
	private Definicion parseVarConf () {
		Definicion d = null;
		return d;
	}
	
	private String colors() {
		Token token = lex.getToken();
		String result = "error";
		switch (token.lexeme) {
		case "black":
			result = "black";
			break;
		case "blue":
			result = "blue";
			break;
		case "green":
			result = "green";
			break;
		case "red":
			result = "red";
			break;
		default:
			errorSintactico(token.getLexeme(), token.getLine());
			break;
		}
		return result;
		
	}
	
	private String styles() {
		Token token = lex.getToken();
		String result = "error";
		switch (token.lexeme) {
		case "normal":
			result = "normal";
			break;
		case "underlined":
			result = "underlined";
			break;
		case "bold":
			result = "bold";
			break;
		case "italic":
			result = "italic";
			break;
		default:
			errorSintactico(token.getLexeme(), token.getLine());
			break;
		}
		return result;
	}
	
	private String alignments() {
		Token token = lex.getToken();
		String result = "error";
		switch (token.lexeme) {
		case "center":
			result = "center";
			break;
		case "left":
			result = "left";
			break;
		case "rigth":
			result = "rigth";
			break;
		default:
			errorSintactico(token.getLexeme(), token.getLine());
			break;
		}
		return result;
	}

	//Gestión de Errores Sintáctico
	void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sintáctico : "+e+" en la línea "+line);
	}
}
