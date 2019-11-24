package main.java.com.compiladores.web.html.ast;

import main.java.com.compiladores.web.html.visitor.Visitor;

public interface AstHtml {
	
	Object accept(Visitor v, Object param);
}
