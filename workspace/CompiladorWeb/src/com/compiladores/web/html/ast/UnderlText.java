package com.compiladores.web.html.ast;

import com.compiladores.web.html.visitor.Visitor;

public class UnderlText implements Bloque{

	public String underlText;

	public UnderlText(String underlText) {
		this.underlText = underlText;
	}


	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
}
