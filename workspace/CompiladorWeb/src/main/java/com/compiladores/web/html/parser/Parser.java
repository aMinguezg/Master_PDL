package main.java.com.compiladores.web.html.parser;

import main.java.com.compiladores.web.html.parser.Token;
import main.java.com.compiladores.web.html.parser.TokensId;

import java.util.ArrayList;
import java.util.List;

import main.java.com.compiladores.web.html.ast.Bloque;
import main.java.com.compiladores.web.html.ast.Body;
import main.java.com.compiladores.web.html.ast.BoldText;
import main.java.com.compiladores.web.html.ast.H1;
import main.java.com.compiladores.web.html.ast.H2;
import main.java.com.compiladores.web.html.ast.Head;
import main.java.com.compiladores.web.html.ast.ItalicText;
import main.java.com.compiladores.web.html.ast.Link;
import main.java.com.compiladores.web.html.ast.P;
import main.java.com.compiladores.web.html.ast.Parrafo;
import main.java.com.compiladores.web.html.ast.Programa;
import main.java.com.compiladores.web.html.ast.Text;
import main.java.com.compiladores.web.html.ast.Title;
import main.java.com.compiladores.web.html.ast.UnderlText;

public class Parser {

	Lexicon lex;
	boolean errorSint = false;

	public Parser (Lexicon lex) {
		this.lex = lex;
	}

	public Programa parse () {
		Programa prog = null;
		Head head = null;
		Body body = null;
		Token token = lex.getToken();

		if(token.getToken().equals(TokensId.HTMLOPEN)) {
			head = parseHead();
			body = parseBody();
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		token = lex.getToken();
		if(token.getToken().equals(TokensId.HTMLCLOSE)) {
			token = lex.getToken();
			if (token.getToken() != TokensId.EOF) {
				errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
			}		
		}
		else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if (!errorSint) {
			prog = new Programa(head,body);
		}

		return prog;
	}

	Head parseHead(){
		Head head = null;
		Title titulo = null;
		Link link = null;

		Token token = lex.getToken();

		if(token.getToken().equals(TokensId.HEADOPEN)) {
			titulo = parseTitle();
			link = parseLink();
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		token = lex.getToken();

		if (token.getToken().equals(TokensId.HEADCLOSE) && !errorSint) {
			head = new Head(titulo,link);
		}

		return head;
	}

	Title parseTitle(){
		Title title = null;
		String titulo = null;
		Token token = lex.getToken();
		if(token.getToken().equals(TokensId.TITLEOPEN)) {
			token = lex.getToken();
			if(token.getToken().equals(TokensId.TEXTO)) {
				titulo = token.getLexeme();
				token = lex.getToken();
			}else {
				errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
			}
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if (token.getToken().equals(TokensId.TITLECLOSE) && !errorSint) {
			title = new Title(titulo);
		}

		return title;
	}

	Link parseLink(){
		Link link = null;
		String href = null;
		String rel = null;
		String type = null;
		Token token = lex.getToken();
		if(token.getToken().equals(TokensId.LINKOPEN)) {
			token = lex.getToken();
		}
		else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if(token.getToken().equals(TokensId.HREF)) {
			token = lex.getToken();
			if(token.getToken().equals(TokensId.EQUAL)) {
				token = lex.getToken();
				if(token.getToken().equals(TokensId.STRING)) {
					href = token.getLexeme();
					token = lex.getToken();
				}else {
					errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
				}
			}else {
				errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
			}
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if(token.getToken().equals(TokensId.REL)) {
			token = lex.getToken();
			if(token.getToken().equals(TokensId.EQUAL)) {
				token = lex.getToken();
				if(token.getToken().equals(TokensId.STRING)) {
					rel = token.getLexeme();
					token = lex.getToken();
				}else {
					errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
				}
			}else {
				errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
			}
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if(token.getToken().equals(TokensId.TYPE)) {
			token = lex.getToken();
			if(token.getToken().equals(TokensId.EQUAL)) {
				token = lex.getToken();
				if(token.getToken().equals(TokensId.STRING)) {
					type = token.getLexeme();
					token = lex.getToken();
				}else {
					errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
				}
			}else {
				errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
			}
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}


		if (token.getToken().equals(TokensId.LINKCLOSE) && !errorSint) {
			link = new Link(href,rel,type);
		}

		return link;
	}

	Body parseBody(){
		Body body = null;
		List<Parrafo> parrafos = new ArrayList<Parrafo>();
		Token token = lex.getToken();

		if(token.getToken().equals(TokensId.BODYOPEN)) {
			token = lex.getToken();
			while(token.getToken() == TokensId.H1OPEN || token.getToken() == TokensId.H2OPEN 
					|| token.getToken() == TokensId.POPEN){
				lex.returnLastToken();
				Parrafo p = parseParrafo();
				if((p != null) && (!errorSint)) {
					parrafos.add(p);
				}
				token = lex.getToken();
			}
		}
		else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if (token.getToken().equals(TokensId.BODYCLOSE) && !errorSint) {
			body = new Body(parrafos);
		}

		return body;
	}

	Parrafo parseParrafo(){
		Parrafo parrafo = null;
		Token token = lex.getToken();
		H1 h1 = null;
		H2 h2 = null;
		P p = null;
		if(token.getToken().equals(TokensId.H1OPEN)) {
			h1 = parseH1();
			if((h1 != null) && (!errorSint)) {
				return h1;
			}else {
				return null;
			}
		}
		else if(token.getToken().equals(TokensId.H2OPEN)) {
			h2 = parseH2();
			if((h2 != null) && (!errorSint)) {
				return h2;
			}else {
				return null;
			}
		}
		else if(token.getToken().equals(TokensId.POPEN)) {
			p = parseP();
			if((p != null) && (!errorSint)) {
				return p;
			}else {
				return null;
			}
		}
		else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
			return parrafo;
		}


	}

	H1 parseH1(){
		H1 h1 = null;
		String text = null;
		Token token = lex.getToken();
		if(token.getToken().equals(TokensId.TEXTO)) {
			text = token.getLexeme();
			token = lex.getToken();
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if (token.getToken().equals(TokensId.H1CLOSE) && !errorSint) {
			h1 = new H1(text);
		}

		return h1;
	}

	H2 parseH2(){
		H2 h2 = null;
		String text = null;
		Token token = lex.getToken();
		if(token.getToken().equals(TokensId.TEXTO)) {
			text = token.getLexeme();
			token = lex.getToken();
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if (token.getToken().equals(TokensId.H2CLOSE) && !errorSint) {
			h2 = new H2(text);
		}

		return h2;
	}

	P parseP(){
		P p = null;

		List<Bloque> bloques = new ArrayList<Bloque>();
		Token token = lex.getToken();

		while(token.getToken() == TokensId.BOPEN || token.getToken() == TokensId.UOPEN 
				|| token.getToken() == TokensId.IOPEN || token.getToken() == TokensId.TEXTO){
			lex.returnLastToken();
			Bloque b = parseBloque();
			if((b != null) && (!errorSint)) {
				bloques.add(b);
			}
			token = lex.getToken();
		}

		if (token.getToken().equals(TokensId.PCLOSE) && !errorSint) {
			p = new P(bloques);
		}

		return p;
	}

	Bloque parseBloque(){
		Bloque bloque = null;
		Token token = lex.getToken();
		Text text = null;
		BoldText boldText = null;
		ItalicText italicText = null;
		UnderlText underlText = null;
		
		if(token.getToken().equals(TokensId.BOPEN)) {
			boldText = parseBoldText();
			if((boldText != null) && (!errorSint)) {
				return boldText;
			}else {
				return null;
			}
		}
		else if(token.getToken().equals(TokensId.IOPEN)) {
			italicText = parseItalicText();
			if((italicText != null) && (!errorSint)) {
				return italicText;
			}else {
				return null;
			}
		}
		else if(token.getToken().equals(TokensId.TEXTO)) {
			 text = new Text(token.getLexeme());
			 return text;	
		}
		else if(token.getToken().equals(TokensId.UOPEN)) {
			underlText = parseUnderlText();
			if((underlText != null) && (!errorSint)) {
				return underlText;
			}else {
				return null;
			}
		}
		else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
			return bloque;
		}

	}

	BoldText parseBoldText(){
		BoldText boldText = null;
		String text = null;
		Token token = lex.getToken();
		if(token.getToken().equals(TokensId.TEXTO)) {
			text = token.getLexeme();
			token = lex.getToken();
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if (token.getToken().equals(TokensId.BCLOSE) && !errorSint) {
			boldText = new BoldText(text);
		}


		return boldText;
	}

	ItalicText parseItalicText(){
		ItalicText italicText = null;
		String text = null;
		Token token = lex.getToken();
		if(token.getToken().equals(TokensId.TEXTO)) {
			text = token.getLexeme();
			token = lex.getToken();
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if (token.getToken().equals(TokensId.ICLOSE) && !errorSint) {
			italicText = new ItalicText(text);
		}


		return italicText;
	}

	UnderlText parseUnderlText(){
		UnderlText underlText = null;
		String text = null;
		Token token = lex.getToken();
		if(token.getToken().equals(TokensId.TEXTO)) {
			text = token.getLexeme();
			token = lex.getToken();
		}else {
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el final del fichero", token.getLine());
		}

		if (token.getToken().equals(TokensId.UCLOSE) && !errorSint) {
			underlText = new UnderlText(text);
		}

		return underlText;
	}


	//Gestión de Errores Sintáctico
	void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sintáctico : "+e+" en la línea "+line);
	}

}
