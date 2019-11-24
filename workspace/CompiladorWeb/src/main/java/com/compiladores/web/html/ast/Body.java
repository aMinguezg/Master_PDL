package main.java.com.compiladores.web.html.ast;

import java.util.List;

import main.java.com.compiladores.web.html.visitor.Visitor;

	

public class Body implements AstHtml{

	public List<Parrafo> parrafo;
	
	public Body(List<Parrafo> parrafo) {
		this.parrafo = parrafo;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
