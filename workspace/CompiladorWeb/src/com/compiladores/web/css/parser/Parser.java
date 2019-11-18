package com.compiladores.web.css.parser;

import java.util.List;

import com.compiladores.web.css.ast.*;
import java.util.*;

public class Parser {
	
	Lexicon lex;
	boolean errorSint = false;
	
	public Parser (Lexicon lex) {
		this.lex = lex;
	}
	
	public Program parse () {
		Program prog = null;
		List<Regla> decls = new ArrayList<Regla>();
		Token token = lex.getToken();
		while (token.getToken() == TokensId.id) {
			lex.returnLastToken();
			Regla decl = parseDecl();
			if ((decl != null) && (!errorSint))
				decls.add(decl);
			token = lex.getToken();
		}
		if (token.getToken() != TokensId.EOF) {
			errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}
		if (!errorSint)
			prog = new Program (decls);
		return prog;
	}
	
	Regla parseDecl () {
		Regla decl = null;
		String ident = null;
		List<Definicion> params = new ArrayList<Definicion>();
		Token token = lex.getToken();
		ident = token.getLexeme(); // No hace falta comprobarlo porque viene de Program
		token = lex.getToken();
		if (token.getToken() != TokensId.llaveOpen)
			errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba '{'", token.getLine());
		token = lex.getToken();
		while ((token.getToken() == TokensId.color) || (token.getToken() == TokensId.fontSize)
				|| (token.getToken() == TokensId.fontStyle) || (token.getToken() == TokensId.textAlign)) {
			lex.returnLastToken();
			Definicion param = parseParam();
			if ((param != null) && (!errorSint))
				params.add(param);
			token = lex.getToken();
		}
		//token = lex.getToken();
		if (token.getToken() != TokensId.llaveClose)
			errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba '}'", token.getLine());
		if (!errorSint)
			decl = new Regla(params,ident);
		return decl;
	}
	
	Definicion parseParam () {
		Definicion param = null;
		Token token = lex.getToken();
		switch (token.getToken()) {
			case color:
			case fontSize:
			case fontStyle:
			case textAlign:
				lex.returnLastToken();
				param = parseVarconf();
				break;
				default:
					errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba una de las siguiente etiquetas: color, font-style, font-size o text-align.", token.getLine());
		}
		token = lex.getToken();
		if (token.getToken() != TokensId.semiColon)
			errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba ';'", token.getLine());
		if (errorSint)
			param = null;
		return param;
	}
	
	// Por la simpleza del lenguaje Varcond devuekve ya el param
	Definicion parseVarconf () {
		Definicion varconf = null;
		String label;
		String value = null;
		Token token = lex.getToken();
		label = token.getLexeme();
		Token tAnt = token;
		token = lex.getToken();
		if (token.getToken() != TokensId.colon)
			errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba ':'", token.getLine());
		switch (tAnt.getToken()) {
			case color:
				value = parseColors();
				break;
			case fontSize:
				token = lex.getToken();
				if (token.getToken() != TokensId.numero)
					errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba tamaño de fuente NNpx", token.getLine());
				value = token.getLexeme();
				break;
			case fontStyle:
				value = parseStyles();
				break;
			case textAlign:
				value = parseAlignments();
				break;
		}
		if (!errorSint)
			varconf = new Definicion(label, value);
		return varconf;
	}
	
	String parseColors () {
		Token token = lex.getToken();
		switch (token.getToken()) {
			case black:
				return ("black");
			case blue:
				return ("blue");
			case green:
				return ("green");
			case red:
				return ("red");
			default:
				errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba un color: black, green, blue o red", token.getLine());
				return null;
		}
	}

	String parseAlignments () {
		Token token = lex.getToken();
		switch (token.getToken()) {
			case center:
				return ("center");
			case left:
				return ("left");
			case rigth:
				return ("right");
			default:
				errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba una ubicación de texto: left, center o right", token.getLine());
				return null;
		}
	}

	String parseStyles () {
		Token token = lex.getToken();
		switch (token.getToken()) {
			case normal:
				return ("normal");
			case italic:
				return ("italic");
			case bold:
				return ("blod");
			default:
				errorSintactico ("Encontrado "+token.getLexeme()+". Se esperaba un estilo de texto: normal, italic o bold", token.getLine());
				return null;
		}
	}

	//Gestión de Errores Sintáctico
	void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sintáctico : "+e+" en la línea "+line);
	}
}
