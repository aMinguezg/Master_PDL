package com.compiladores.web.html.parser;

import com.compiladores.web.html.ast.Bloque;
import com.compiladores.web.html.ast.Body;
import com.compiladores.web.html.ast.BoldText;
import com.compiladores.web.html.ast.H1;
import com.compiladores.web.html.ast.H2;
import com.compiladores.web.html.ast.Head;
import com.compiladores.web.html.ast.ItalicText;
import com.compiladores.web.html.ast.Link;
import com.compiladores.web.html.ast.P;
import com.compiladores.web.html.ast.Parrafo;
import com.compiladores.web.html.ast.Programa;
import com.compiladores.web.html.ast.Title;
import com.compiladores.web.html.ast.UnderlText;

public class Parser {
	
	Lexicon lex;
	boolean errorSint = false;
	
	public Parser (Lexicon lex) {
		this.lex = lex;
	}
	
	public Programa parse () {
		Programa prog = null;
		
		return prog;
	}
	
	Head parseHead(){
		Head head = null;
				
		return head;
	}
	
	Title parseTitle(){
		Title title = null;
				
		return title;
	}
	
	Link parseLink(){
		Link link = null;
				
		return link;
	}
	
	Body parseBody(){
		Body body = null;
				
		return body;
	}
	
	Parrafo parseParrafo(){
		Parrafo parrafo = null;
				
		return parrafo;
	}
	
	H1 parseH1(){
		H1 h1 = null;
				
		return h1;
	}
	
	H2 parseH2(){
		H2 h2 = null;
				
		return h2;
	}
	
	P parseP(){
		P p = null;
				
		return p;
	}
	
	Bloque parseBloque(){
		Bloque bloque = null;
				
		return bloque;
	}
	
	BoldText parseBoldText(){
		BoldText boldText = null;
				
		return boldText;
	}
	
	ItalicText parseItalicText(){
		ItalicText italicText = null;
				
		return italicText;
	}
	
	UnderlText parseUnderlText(){
		UnderlText underlText = null;
				
		return underlText;
	}
	
	
	//Gestión de Errores Sintáctico
	void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sintáctico : "+e+" en la línea "+line);
	}

}
