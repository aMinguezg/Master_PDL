package main.java.com.compiladores.web.html.ast;

import main.java.com.compiladores.web.html.visitor.Visitor;

public class BoldText implements Bloque{
	
	public String boldText;

	public BoldText(String boldText) {
		this.boldText = boldText;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}

	@Override
	public String getText() {
		
		return this.boldText;
	}

	@Override
	public String getTipoText() {
		// TODO Auto-generated method stub
		return "negrita";
	}

}
