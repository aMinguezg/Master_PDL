package com.compiladores.web.html.ast;

import com.compiladores.web.html.visitor.Visitor;

public interface AstHtml {
	
	Object accept(Visitor v, Object param);
}
