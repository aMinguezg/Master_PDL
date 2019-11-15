package parser;

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
			//...
			}
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
			lexReturned = lexReturned+(valor);
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
