package main.java.com.compiladores.web.html.visitor;

import main.java.com.compiladores.web.css.ast.Regla;
import main.java.com.compiladores.web.html.ast.*;

public class PrintHtmlAstVisitor implements Visitor {
	String sp = "   ";

	@Override
	public Object visit(Bloque b, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Body b, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(BoldText b, Object param) {
		return (String) param + b.boldText + "\n";
	}

	@Override
	public Object visit(H1 h, Object param) {
		return (String) param + h.h1 + "\n";
	}

	@Override
	public Object visit(H2 h, Object param) {
		return (String) param + h.h2 + "\n";
	}

	@Override
	public Object visit(Head h, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ItalicText i, Object param) {
		return (String) param + i.italicText + "\n";
	}

	@Override
	public Object visit(Link l, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(P p, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Parrafo p, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Programa p, Object param) {
		String sd = "", sr;
		
		sr = "(HTML declarations\n"+ p.head.toString() + p.body.toString() + ")";
		return sr;
	}

	@Override
	public Object visit(Title t, Object param) {
		return (String) param + t.titulo + "\n";
	}

	@Override
	public Object visit(UnderlText u, Object param) {
		return (String) param + u.underlText + "\n";
	}

	@Override
	public Object visit(Text t, Object param) {
		return (String) param + t.text + "\n";
	}

	
}
