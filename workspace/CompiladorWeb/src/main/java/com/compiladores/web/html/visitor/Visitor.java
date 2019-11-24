package main.java.com.compiladores.web.html.visitor;

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

public interface Visitor {
	
	Object visit(Bloque b,  Object param);
	
	Object visit(Body b,  Object param);
	
	Object visit(BoldText b,  Object param);
	
	Object visit(H1 h,  Object param);
	
	Object visit(H2 h,  Object param);
	
	Object visit(Head h,  Object param);
	
	Object visit(ItalicText i,  Object param);
	
	Object visit(Link l,  Object param);
	
	Object visit(P p,  Object param);
	
	Object visit(Parrafo p,  Object param);
	
	Object visit(Programa p,  Object param);
	
	Object visit(Title t,  Object param);
	
	Object visit(UnderlText u,  Object param);
	
	Object visit(Text t,  Object param);
}
