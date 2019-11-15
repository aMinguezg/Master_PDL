package com.compiladores.web.css.ast;

import java.util.List;

import com.compiladores.web.css.visitor.Visitor;

public class Definicion  implements AstCss{

	public List<String> atributo;
	
	public Definicion(List<String> atributo) {
		this.atributo = atributo;
	}
	
	@Override
	public Object accpet(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}

	
	
}
