package com.compiladores.web.css.visitor;

import com.compiladores.web.css.ast.Definicion;
import com.compiladores.web.css.ast.Program;
import com.compiladores.web.css.ast.Regla;

public interface Visitor {
	
	Object visit(Program p,  Object param);
	
	Object visit(Regla r,  Object param);
	
	Object visit(Definicion d,  Object param);
	
}
