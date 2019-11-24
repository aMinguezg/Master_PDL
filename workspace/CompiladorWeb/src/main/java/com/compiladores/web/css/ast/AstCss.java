package main.java.com.compiladores.web.css.ast;

import main.java.com.compiladores.web.css.visitor.Visitor;

public interface AstCss {
	

	Object accept(Visitor v, Object param);
}
