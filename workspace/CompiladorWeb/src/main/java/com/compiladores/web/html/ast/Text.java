package main.java.com.compiladores.web.html.ast;

import main.java.com.compiladores.web.html.visitor.Visitor;

public class Text implements Bloque{
	
	public String text;

	public Text(String text) {
		this.text = text;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return this.text;
	}

	@Override
	public String getTipoText() {
		// TODO Auto-generated method stub
		return "normal";
	}

}
