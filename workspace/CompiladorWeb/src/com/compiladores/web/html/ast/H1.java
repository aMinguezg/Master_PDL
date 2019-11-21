package com.compiladores.web.html.ast;

import com.compiladores.web.html.visitor.Visitor;

public class H1 implements Parrafo{
	
	public String h1;

	public H1(String h1) {
		this.h1 = h1;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
