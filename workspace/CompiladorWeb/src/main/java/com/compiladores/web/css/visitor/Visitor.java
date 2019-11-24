package main.java.com.compiladores.web.css.visitor;

import main.java.com.compiladores.web.css.ast.Definicion;
import main.java.com.compiladores.web.css.ast.Program;
import main.java.com.compiladores.web.css.ast.Regla;

public interface Visitor {
	
	Object visit(Program p,  Object param);
	
	Object visit(Regla r,  Object param);
	
	Object visit(Definicion d,  Object param);
	
}
