package com.compiladores.web.css.ast;

import java.util.List;

import com.compiladores.web.css.visitor.Visitor;

public class Regla  implements AstCss{

	public List<Definicion> definicion;
	public String id;
	
	public Regla(List<Definicion> definicion, String id) {
		this.definicion = definicion;
		this.id = id;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}

	
	
}
