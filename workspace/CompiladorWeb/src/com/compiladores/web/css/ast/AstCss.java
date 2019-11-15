package com.compiladores.web.css.ast;

import com.compiladores.web.css.visitor.Visitor;

public interface AstCss {
	Object accpet(Visitor v, Object param);
}
