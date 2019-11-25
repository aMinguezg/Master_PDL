package main.java.com.compiladores.web.html.ast;

import main.java.com.compiladores.web.html.visitor.Visitor;

public class UnderlText implements Bloque{

	public String underlText;

	public UnderlText(String underlText) {
		this.underlText = underlText;
	}


	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}


	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return this.underlText;
	}


	@Override
	public String getTipoText() {
		// TODO Auto-generated method stub
		return "subrayado";
	}
}
