package main.java.com.compiladores.web.html.ast;

import java.util.List;

import main.java.com.compiladores.web.html.visitor.Visitor;

public class P implements Parrafo{

	public List<Bloque> bloque;
	
	public P(List<Bloque> bloque) {
		this.bloque = bloque;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
