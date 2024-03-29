package main.java.com.compiladores.web.css.parser;

import java.io.FileReader;
import java.util.*;
import java.io.*;

public class Lexicon {

	// Gesti�n de tokens
	List<Token> tokens = new ArrayList<Token>();
	int i = 0; //�ltimo token entregado en getToken()
	//Gesti�n de lectura del fichero
	FileReader filereader;
	boolean charBuffUsed = false;
	char charBuff;
	int line = 1; // indica la l�nea del fichero fuente

	HashSet<Character> charText = new HashSet<Character>();

	public Lexicon (FileReader f) {
		filereader = f;
		String lex;
		try{
			char valor=(char) 0;
			while(valor!=(char) -1){
				valor=nextChar();
				switch(valor) {
				case '{':
					tokens.add(new Token(TokensId.llaveOpen, "{", line));
					break;
				case '}':
					tokens.add(new Token(TokensId.llaveClose, "}", line));
					break;
				case ':':
					tokens.add(new Token(TokensId.colon, ":", line));
					break;
				case ';':
					tokens.add(new Token(TokensId.semiColon, ";", line));
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
					if(Character.isAlphabetic(valor)) {
						lex = getText(String.valueOf(valor));
							switch(lex) {
							case "black":
								tokens.add(new Token(TokensId.black, lex, line));
								break;
							case "blue":
								tokens.add(new Token(TokensId.blue, lex, line));
								break;
							case "green":
								tokens.add(new Token(TokensId.green, lex, line));
								break;
							case "red":
								tokens.add(new Token(TokensId.red, lex, line));
								break;
							case "center":
								tokens.add(new Token(TokensId.center, lex, line));
								break;
							case "rigth":
								tokens.add(new Token(TokensId.rigth, lex, line));
								break;
							case "left":
								tokens.add(new Token(TokensId.left, lex, line));
								break;
							case "normal":
								tokens.add(new Token(TokensId.normal, lex, line));
								break;
							case "italic":
								tokens.add(new Token(TokensId.italic, lex, line));
								break;
							case "bold":
								tokens.add(new Token(TokensId.bold, lex, line));
								break;
							case "text-align":
								tokens.add(new Token(TokensId.textAlign, lex, line));
								break;
							case "color":
								tokens.add(new Token(TokensId.color, lex, line));
								break;
							case "font-style":
								tokens.add(new Token(TokensId.fontStyle, lex, line));
								break;
							case "font-size":
								tokens.add(new Token(TokensId.fontSize, lex, line));
								break;
							default:
								tokens.add(new Token(TokensId.id, lex, line));
								break;
							}
						
					}
					else if(Character.isDigit(valor)) {
						lex = getSize(String.valueOf(valor));
						tokens.add(new Token(TokensId.numero, lex, line));
					}
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
		// Devolver el �ltimo token
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

		//Privadas
		String getSize (String lexStart) throws IOException {
			String lexReturned = lexStart;
			char valor;
			do {
				valor=nextChar();
				lexReturned = lexReturned+(valor);
			} while ((valor != 'p') && (valor != -1));
			//returnChar(valor);
			if (valor == 'p') {
				valor=nextChar();
				if (valor == 'x') {
					lexReturned = lexReturned+(valor);
				} else {
					errorLexico ("Encontrado "+lexReturned+". Se esperada un token SIZE.");
					return null;
				}
			}
			return lexReturned;
		}

		String getText (String lexStart) throws IOException {
			String lexReturned = lexStart;
			char valor = nextChar();
			while (Character.isDigit(valor) || Character.isAlphabetic(valor) || (valor == '-')) {
				lexReturned = lexReturned+(valor);
				valor=nextChar();
			}
			returnChar(valor);
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
			System.out.println("Error l�xico en : "+e);
		}
	}
