package main.java.com.compiladores.web.html.parser;

import java.io.FileReader;
import java.util.*;

import main.java.com.compiladores.web.html.parser.Token;
import main.java.com.compiladores.web.html.parser.TokensId;

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
							errorLexico ("Encontrado "+lexemah+". Se esperada un token SIZE.");
							break;
						}
						break;
					case 't':
						String lexemat = getTextId(String.valueOf(sig));
						if(lexemat.equals("<title>")) {
							tokens.add(new Token(TokensId.TITLEOPEN, lexemat, line));
						}
						else {
							errorLexico ("Encontrado "+lexemat+". Se esperada un token SIZE.");
						}
						break;
					case 'l':
						String lexemal = getTextIdLink(String.valueOf(sig));
						if(lexemal.equals("<link")) {
							tokens.add(new Token(TokensId.LINKOPEN, lexemal, line));
							char sigLink = nextChar();
							if(sigLink == 'h'){
								String result = String.valueOf(sigLink);
								sigLink=nextChar();
								while (sigLink != '=') {
									result = result+(sigLink);
									sigLink=nextChar();
								}
								if(result.equals("href")){
									tokens.add(new Token(TokensId.HREF, result, line));
									tokens.add(new Token(TokensId.EQUAL, "=", line));
									sigLink=nextChar();
									if(sigLink == '"'){
										String result2 = String.valueOf(sigLink);
										sigLink=nextChar();
										while (sigLink != ' ') {
											result2 = result2+(sigLink);
											sigLink=nextChar();
										}
										tokens.add(new Token(TokensId.STRING, result2, line));
										sigLink=nextChar(); //tiene que leer rel
										if(sigLink == 'r'){
											String result3 = String.valueOf(sigLink);
											sigLink=nextChar();
											while (sigLink != '=') {
												result3 = result3+(sigLink);
												sigLink=nextChar();
											}
											if(result3.equals("rel")){
												tokens.add(new Token(TokensId.REL, result3, line));
												tokens.add(new Token(TokensId.EQUAL, "=", line));
												sigLink=nextChar();
												if(sigLink == '"'){
													String result4 = String.valueOf(sigLink);
													sigLink=nextChar();
													while (sigLink != ' ') {
														result4 = result4+(sigLink);
														sigLink=nextChar();
													}
													tokens.add(new Token(TokensId.STRING, result4, line));
													sigLink=nextChar(); //tiene que leer rel
													if(sigLink == 't'){
														String result5 = String.valueOf(sigLink);
														sigLink=nextChar();
														while (sigLink != '=') {
															result5 = result5+(sigLink);
															sigLink=nextChar();
														}
														if(result5.equals("type")){
															tokens.add(new Token(TokensId.TYPE, result5, line));
															tokens.add(new Token(TokensId.EQUAL, "=", line));
															sigLink=nextChar();
															if(sigLink == '"'){
																String result6 = String.valueOf(sigLink);
																sigLink=nextChar();
																while (sigLink != '"') {
																	result6 = result6+(sigLink);
																	sigLink=nextChar();
																}
																result6 = result6+(sigLink);
																tokens.add(new Token(TokensId.STRING, result6, line));

															}
															else{
																errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
																break;
															}
														}
														else{
															errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
															break;
														}
													}
													else{
														errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
														break;
													}
												}
												else{
													errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
													break;
												}
											}
											else{
												errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
												break;
											}
										}
										else{
											errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
											break;
										}

									}
									else{
										errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
										break;
									}
								}
								else{
									errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
									break;
								}
							}
							else{
								errorLexico ("Encontrado "+sigLink+". Se esperada un token SIZE.");
								break;
							}
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
							errorLexico ("Encontrado "+lexemab+". Se esperada un token SIZE.");
							break;
						}
						break;
					case 'p':
						String lexemap = getTextId(String.valueOf(sig));
						if(lexemap.equals("<p>")) {
							tokens.add(new Token(TokensId.POPEN, lexemap, line));
						}
						else {
							errorLexico ("Encontrado "+lexemap+". Se esperada un token SIZE.");
							break;
						}
						break;
					case 'i':
						String lexemai = getTextId(String.valueOf(sig));
						if(lexemai.equals("<i>")) {
							tokens.add(new Token(TokensId.IOPEN, lexemai, line));
						}
						else {
							errorLexico ("Encontrado "+lexemai+". Se esperada un token SIZE.");
							break;
						}
						break;
					case 'u':
						String lexemau = getTextId(String.valueOf(sig));
						if(lexemau.equals("<u>")) {
							tokens.add(new Token(TokensId.UOPEN, lexemau, line));
						}
						else {
							errorLexico ("Encontrado "+lexemau+". Se esperada un token SIZE.");
							break;
						}
						break;
					case '/':
						char sigClose = nextChar();
						switch(sigClose) {
						case 'h':
							String lexemahClose = getTextIdClose(String.valueOf(sigClose));
							switch(lexemahClose) {
							case "</html>": 
								tokens.add(new Token(TokensId.HTMLCLOSE, lexemahClose, line));
								break;
							case "</head>":
								tokens.add(new Token(TokensId.HEADCLOSE, lexemahClose, line));
								break;
							case "</h1>":
								tokens.add(new Token(TokensId.H1CLOSE, lexemahClose, line));
								break;
							case "</h2>":
								tokens.add(new Token(TokensId.H2CLOSE, lexemahClose, line));
								break;
							default:
								errorLexico ("Encontrado "+lexemahClose+". Se esperada un token SIZE.");
								break;
							}
							break;
						case 't':
							String lexematClose = getTextIdClose(String.valueOf(sigClose));
							if(lexematClose.equals("</title>")) {
								tokens.add(new Token(TokensId.TITLECLOSE, lexematClose, line));
							}
							else {
								errorLexico ("Encontrado "+lexematClose+". Se esperada un token SIZE.");
								break;
							}
							break;
						case 'b':
							String lexemabClose = getTextIdClose(String.valueOf(sigClose));
							if(lexemabClose.equals("</body>")) {
								tokens.add(new Token(TokensId.BODYCLOSE, lexemabClose, line));
							}
							else if(lexemabClose.equals("</b>")) {
								tokens.add(new Token(TokensId.BCLOSE, lexemabClose, line));
							}
							else {
								errorLexico ("Encontrado "+lexemabClose+". Se esperada un token SIZE.");
								break;
							}
							break;
						case 'p':
							String lexemapClose = getTextIdClose(String.valueOf(sigClose));
							if(lexemapClose.equals("</p>")) {
								tokens.add(new Token(TokensId.PCLOSE, lexemapClose, line));
							}
							else {
								errorLexico ("Encontrado "+lexemapClose+". Se esperada un token SIZE.");
								break;
							}
							break;
						case 'i':
							String lexemaiClose = getTextIdClose(String.valueOf(sigClose));
							if(lexemaiClose.equals("</i>")) {
								tokens.add(new Token(TokensId.ICLOSE, lexemaiClose, line));
							}
							else {
								errorLexico ("Encontrado "+lexemaiClose+". Se esperada un token SIZE.");
								break;
							}
							break;
						case 'u':
							String lexemauClose = getTextIdClose(String.valueOf(sigClose));
							if(lexemauClose.equals("</u>")) {
								tokens.add(new Token(TokensId.UCLOSE, lexemauClose, line));
							}
							else {
								errorLexico ("Encontrado "+lexemauClose+". Se esperada un token SIZE.");
								break;
							}
							break;
						default:
							errorLexico ("Encontrado "+sigClose+". Se esperada un token SIZE.");
							break;
						}
						break;
					default:
						errorLexico ("Encontrado "+sig+". Se esperada un token SIZE.");
						break;

					}
					break;
				case '\n':
					line++;
					break;
				case '\r':
					break;
				case '\t':
					break;
				case '>':
					tokens.add(new Token(TokensId.LINKCLOSE, String.valueOf(valor), line));
					break;
				case ' ':
					break;
				case (char) -1:
					break;
				default:
					String texto = getTextComplete((String.valueOf(valor)));
					tokens.add(new Token(TokensId.TEXTO, texto, line));
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

	String getTextIdLink (String lexStart) throws IOException {
		String lexReturned = "<" + lexStart;
		char valor = nextChar();
		while (valor != ' ') {
			lexReturned = lexReturned+(valor);
			valor=nextChar();
		}
		return lexReturned;
	}

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
	
	String getTextIdClose (String lexStart) throws IOException {
		String lexReturned = "</" + lexStart;
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
		System.out.println("Error léxico en : "+e);
	}
}
