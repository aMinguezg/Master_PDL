package com.compiladores.web.html.parser;

import java.io.FileReader;
import java.util.*;

import com.compiladores.web.html.parser.Token;
import com.compiladores.web.html.parser.TokensId;

import java.io.*;

public class Lexicon {

	// Gestión de tokens
	List<Token> tokens = new ArrayList<Token>();
	int i = 0; //Último token entregado en getToken()
	//Gestión de lectura del fichero
	FileReader filereader;
	boolean charBuffUsed = false;
	char charBuff;
	int line = 1; // indica la línea del fichero fuente

	HashSet<Character> charText = new HashSet<Character>();

	public Lexicon (FileReader f) {
		filereader = f;
		String lex;
		try{
			char valor=(char) 0;
			while(valor!=(char) -1){
				valor=nextChar();
				switch(valor) {
				case '<':
					char sig = nextChar();
					switch(sig) {
					case 'h':
						String lexemah = getTextId(String.valueOf(sig));
						switch(lexemah) {
						case "<html>": 
							tokens.add(new Token(TokensId.HTMLOPEN, lexemah, line));
							break;
						case "<head>":
							tokens.add(new Token(TokensId.HEADOPEN, lexemah, line));
							break;
						case "<h1>":
							tokens.add(new Token(TokensId.H1OPEN, lexemah, line));
							break;
						case "<h2>":
							tokens.add(new Token(TokensId.H2OPEN, lexemah, line));
							break;
						default:
							//error
							break;
						}
						break;
					case 't':
						String lexemat = getTextId(String.valueOf(sig));
						if(lexemat.equals("<title>")) {
							tokens.add(new Token(TokensId.TITLEOPEN, lexemat, line));
						}
						else {
							//error
						}
						break;
					case 'l':
						String lexemal = getTextId(String.valueOf(sig));
						if(lexemal.equals("<link>")) {
							tokens.add(new Token(TokensId.LINKOPEN, lexemal, line));
						}
						else {
							//error
						}
						break;
					case 'b':
						String lexemab = getTextId(String.valueOf(sig));
						if(lexemab.equals("<body>")) {
							tokens.add(new Token(TokensId.BODYOPEN, lexemab, line));
						}
						else if(lexemab.equals("<b>")) {
							tokens.add(new Token(TokensId.BOPEN, lexemab, line));
						}
						else {
							//error
						}
						break;
					case 'p':
						String lexemap = getTextId(String.valueOf(sig));
						if(lexemap.equals("<p>")) {
							tokens.add(new Token(TokensId.POPEN, lexemap, line));
						}
						else {
							//error
						}
						break;
					case 'i':
						String lexemai = getTextId(String.valueOf(sig));
						if(lexemai.equals("<i>")) {
							tokens.add(new Token(TokensId.IOPEN, lexemai, line));
						}
						else {
							//error
						}
						break;
					case 'u':
						String lexemau = getTextId(String.valueOf(sig));
						if(lexemau.equals("<u>")) {
							tokens.add(new Token(TokensId.UOPEN, lexemau, line));
						}
						else {
							//error
						}
						break;
					case '/':
						char sigClose = nextChar();
						switch(sig) {
						case 'h':
							String lexemahClose = getTextId(String.valueOf(sigClose));
							switch(lexemahClose) {
							case "<html>": 
								tokens.add(new Token(TokensId.HTMLCLOSE, lexemahClose, line));
								break;
							case "<head>":
								tokens.add(new Token(TokensId.HEADCLOSE, lexemahClose, line));
								break;
							case "<h1>":
								tokens.add(new Token(TokensId.H1CLOSE, lexemahClose, line));
								break;
							case "<h2>":
								tokens.add(new Token(TokensId.H2CLOSE, lexemahClose, line));
								break;
							default:
								//error
								break;
							}
							break;
						case 't':
							String lexematClose = getTextId(String.valueOf(sigClose));
							if(lexematClose.equals("<title>")) {
								tokens.add(new Token(TokensId.TITLECLOSE, lexematClose, line));
							}
							else {
								//error
							}
							break;
						case 'l':
							String lexemalClose = getTextId(String.valueOf(sigClose));
							if(lexemalClose.equals("<link>")) {
								tokens.add(new Token(TokensId.LINKCLOSE, lexemalClose, line));
							}
							else {
								//error
							}
							break;
						case 'b':
							String lexemabClose = getTextId(String.valueOf(sigClose));
							if(lexemabClose.equals("<body>")) {
								tokens.add(new Token(TokensId.BODYCLOSE, lexemabClose, line));
							}
							else if(lexemabClose.equals("<b>")) {
								tokens.add(new Token(TokensId.BCLOSE, lexemabClose, line));
							}
							else {
								//error
							}
							break;
						case 'p':
							String lexemapClose = getTextId(String.valueOf(sigClose));
							if(lexemapClose.equals("<p>")) {
								tokens.add(new Token(TokensId.PCLOSE, lexemapClose, line));
							}
							else {
								//error
							}
							break;
						case 'i':
							String lexemaiClose = getTextId(String.valueOf(sigClose));
							if(lexemaiClose.equals("<i>")) {
								tokens.add(new Token(TokensId.ICLOSE, lexemaiClose, line));
							}
							else {
								//error
							}
							break;
						case 'u':
							String lexemauClose = getTextId(String.valueOf(sigClose));
							if(lexemauClose.equals("<u>")) {
								tokens.add(new Token(TokensId.UCLOSE, lexemauClose, line));
							}
							else {
								//error
							}
							break;
						default:
							//error
							break;
						}
						break;
					default:
						//error
						break;

					}
					break;
				case '\n':
					line++;
					break;
				case '\r':
					break;
				case ' ':
					break;
				case (char) -1:
					break;
				default:
					String texto = getTextComplete(getTextId(String.valueOf(valor)));
					break;

				}}
			filereader.close();
		}catch(IOException e){
			System.out.println("Error E/S: "+e);
		}

	}

	// ++
	// ++ Operaciones para el Sintactico
	// ++
	// Devolver el último token
	public void returnLastToken () {
		i--;
	}

	// Get Token
	public Token getToken () {
		if (i < tokens.size()) {
			return tokens.get(i++);
		}
		return new Token (TokensId.EOF,"EOF", line);
	}	
	// ++
	// ++ Operaciones para el Sintactico
	// ++

	String getTextId (String lexStart) throws IOException {
		String lexReturned = "<" + lexStart;
		char valor = nextChar();
		while (valor != '>') {
			lexReturned = lexReturned+(valor);
			valor=nextChar();
		}
		lexReturned = lexReturned+(valor);
		return lexReturned;
	}
	
	String getTextComplete (String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor = nextChar();
		while (valor != '<') {
			lexReturned = lexReturned+(valor);
			valor=nextChar();
		}
		return lexReturned;
	}

	char nextChar() throws IOException{
		if (charBuffUsed) {
			charBuffUsed = false;
			return charBuff;
		} else {
			int valor=filereader.read();
			return ((char) valor);
		}
	}

	void returnChar (char r) {
		charBuffUsed = true;
		charBuff = r;
	}

	void errorLexico (String e) {
		System.out.println("Error léxico en : "+e);
	}
}
