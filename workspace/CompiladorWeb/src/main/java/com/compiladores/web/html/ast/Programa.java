package main.java.com.compiladores.web.html.ast;

import main.java.com.compiladores.web.html.visitor.Visitor;

public class Programa implements AstHtml{
	
	public Head head;
	public Body body;
	
	public Programa(Head head, Body body) {
		this.head = head;
		this.body = body;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
