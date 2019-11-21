package com.compiladores.web.html.ast;

import com.compiladores.web.html.visitor.Visitor;

public class Head implements AstHtml{
	
	public Title title;
	public Link link;
	
	public Head(Title title, Link link) {
		this.title = title;
		this.link = link;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
