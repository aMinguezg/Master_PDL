package com.compiladores.web.css.visitor;

import com.compiladores.web.css.ast.AstCss;
import com.compiladores.web.css.ast.Definicion;
import com.compiladores.web.css.ast.Program;
import com.compiladores.web.css.ast.Regla;

public class BuscarParamEnCssVisitor implements Visitor {

	String ident = null;
	String label = null;

	@Override
	public Object visit(Program p, Object param) {
		for (Regla r: p.regla) {
			if (r.id.equals(ident))
				return (String) r.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Regla r, Object param) {
		for (Definicion d: r.definicion) {
			if (d.label.equals(label))
				return (String) d.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Definicion d, Object param) {
		return d.value;
	}

	public String search(String ident, String label, AstCss prog) {
		this.ident = ident;
		this.label = label;

		if ((ident == null) || (label==null))
			return null;

		return (String) prog.accept(this, null);
	}


}
