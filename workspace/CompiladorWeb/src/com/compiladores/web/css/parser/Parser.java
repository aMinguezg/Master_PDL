package com.compiladores.web.css.parser;

import java.util.List;

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
		return p;
	}
	
	private Regla parseRegla () {
		Regla r = null;
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

	//Gestión de Errores Sintáctico
	void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sintáctico : "+e+" en la línea "+line);
	}
}
