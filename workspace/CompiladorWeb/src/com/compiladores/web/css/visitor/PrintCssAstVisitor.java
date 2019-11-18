package simpleCss.visitor;

import simpleCss.ast.*;

public class PrintCssAstVisitor implements Visitor {
	String sp = "   ";

	@Override
	public Object visit(Program p, Object param) {
		String sd = "", sr;
		for (Decl d : p.decls) {
			sd = sd + (String) d.accept (this,sp);
		}
		sr = "(CSS declarations\n"+ sd + ")";
		return sr;
	}

	@Override
	public Object visit(Decl d, Object param) {
		String spar = "", sr;
		for (Param p : d.params) {
			spar = spar + (String) p.accept (this,(String) param+sp);
		}
		sr = (String) param + "(" + d.ident + "\n" + spar + (String) param + ")\n";
		return sr;
	}

	@Override
	public Object visit(Param p, Object param) {
		return (String) param + p.label + " --> " + p.value + "\n";
	}

}
