package com.compiladores.web.html.visitor;

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
import com.compiladores.web.html.ast.Text;
import com.compiladores.web.html.ast.Title;
import com.compiladores.web.html.ast.UnderlText;

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
