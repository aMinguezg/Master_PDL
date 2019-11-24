package main.java.com.compiladores.web.html.ast;

import main.java.com.compiladores.web.html.visitor.Visitor;

public class Title implements AstHtml{
	
	public String titulo;
	
	public Title(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
