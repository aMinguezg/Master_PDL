package com.compiladores.web.html.ast;

import com.compiladores.web.html.visitor.Visitor;

public class Text implements Bloque{
	
	public String text;

	public Text(String text) {
		this.text = text;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
