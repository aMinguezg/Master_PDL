package main.java.com.compiladores.web.html.ast;

import main.java.com.compiladores.web.html.visitor.Visitor;

public class Link implements AstHtml{

	public String href;
	public String rel;
	public String type;
	
	public Link(String href,String rel, String type) {
		this.href = href;
		this.rel = rel;
		this.type = type;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return v.visit(this, param);
	}

}
