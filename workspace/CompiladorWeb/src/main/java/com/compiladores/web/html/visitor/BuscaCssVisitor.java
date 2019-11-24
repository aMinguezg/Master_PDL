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

public class BuscaCssVisitor implements Visitor {

    @Override
    public Object visit(Parrafo p, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Head h, Object param) {

        Link link = h.link;
        String result = (String) link.href;

        return result;
    }

    @Override
    public Object visit(H1 h1, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(H2 h2, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Title t, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(P p, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Body b, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Link l, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Programa p, Object param) {

        Head head = p.head;
        String file = (String) head.accept(this, null);

        return file;
    }

    @Override
    public Object visit(Bloque b, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Object visit(BoldText b, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ItalicText i, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UnderlText u, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Text t, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
}