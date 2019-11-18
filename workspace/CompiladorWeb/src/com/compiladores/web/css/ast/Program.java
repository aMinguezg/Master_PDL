package com.compiladores.web.css.ast;

import java.util.List;

import com.compiladores.web.css.visitor.Visitor;

public class Program  implements AstCss{

	public List<Regla> regla;
	
	public Program(List<Regla> regla) {
		this.regla = regla;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}

	
	
}
