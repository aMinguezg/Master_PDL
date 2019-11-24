package main.java.com.compiladores.web.css.ast;

import java.util.List;

import main.java.com.compiladores.web.css.visitor.Visitor;

public class Definicion  implements AstCss{

	public String label;
	public String value;
	
	public Definicion(String label, String value) {
		this.label = label;
		this.value = value;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}

	
	
}
