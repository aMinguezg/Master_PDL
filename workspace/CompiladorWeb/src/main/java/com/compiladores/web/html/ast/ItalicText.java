package main.java.com.compiladores.web.html.ast;

import main.java.com.compiladores.web.html.visitor.Visitor;

public class ItalicText implements Bloque{
	
	public String italicText;

	public ItalicText(String italicText) {
		this.italicText = italicText;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return this.italicText;
	}

	@Override
	public String getTipoText() {
		// TODO Auto-generated method stub
		return "texto italica";
	}

}
