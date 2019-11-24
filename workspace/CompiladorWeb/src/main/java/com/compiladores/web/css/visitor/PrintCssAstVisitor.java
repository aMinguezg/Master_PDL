package main.java.com.compiladores.web.css.visitor;

import main.java.com.compiladores.web.css.ast.*;

public class PrintCssAstVisitor implements Visitor {
	String sp = "   ";

	@Override
	public Object visit(Program p, Object param) {
		String sd = "", sr;
		for (Regla d : p.regla) {
			sd = sd + (String) d.accept (this,sp);
		}
		sr = "(CSS declarations\n"+ sd + ")";
		return sr;
	}

	@Override
	public Object visit(Regla r, Object param) {
		String spar = "", sr;
		for (Definicion p : r.definicion) {
			spar = spar + (String) p.accept (this,(String) param+sp);
		}
		sr = (String) param + "(" + r.id + "\n" + spar + (String) param + ")\n";
		return sr;
		
	}

	@Override
	public Object visit(Definicion d, Object param) {
		return (String) param + d.label + " --> " + d.value + "\n";
	}

}
