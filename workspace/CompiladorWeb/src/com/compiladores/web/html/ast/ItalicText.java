package com.compiladores.web.html.ast;

import com.compiladores.web.html.visitor.Visitor;

public class ItalicText implements Bloque{
	
	public String italicText;

	public ItalicText(String italicText) {
		this.italicText = italicText;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
