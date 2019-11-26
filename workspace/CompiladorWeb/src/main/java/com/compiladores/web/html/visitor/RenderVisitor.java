package main.java.com.compiladores.web.html.visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.compiladores.web.css.ast.AstCss;
import main.java.com.compiladores.web.css.parser.Lexicon;
import main.java.com.compiladores.web.css.parser.Parser;
import main.java.com.compiladores.web.css.visitor.BuscarParamEnCssVisitor;
import main.java.com.compiladores.web.html.ast.AstHtml;
import main.java.com.compiladores.web.html.ast.Bloque;
import main.java.com.compiladores.web.html.ast.Body;
import main.java.com.compiladores.web.html.ast.BoldText;
import main.java.com.compiladores.web.html.ast.H1;
import main.java.com.compiladores.web.html.ast.H2;
import main.java.com.compiladores.web.html.ast.Head;
import main.java.com.compiladores.web.html.ast.ItalicText;
import main.java.com.compiladores.web.html.ast.Link;
import main.java.com.compiladores.web.html.ast.P;
import main.java.com.compiladores.web.html.ast.Parrafo;
import main.java.com.compiladores.web.html.ast.Programa;
import main.java.com.compiladores.web.html.ast.Text;
import main.java.com.compiladores.web.html.ast.Title;
import main.java.com.compiladores.web.html.ast.UnderlText;
import main.java.com.compiladores.web.render.Linea;
import main.java.com.compiladores.web.render.Pagina;
import main.java.com.compiladores.web.main.Utils;

public class RenderVisitor implements Visitor {

	BuscaCssVisitor buscaCss = new BuscaCssVisitor();
	BuscarParamEnCssVisitor buscaParam = new BuscarParamEnCssVisitor();
	AstHtml astHtml;
	AstCss defaultCssAst;
	AstCss hrefCssAst;
	Utils utils = new Utils();
	List<String> atributos = new ArrayList<String>();
	FileReader fileReader = null;

	Pagina pagina = new Pagina();

	public RenderVisitor(AstHtml astHtml) throws FileNotFoundException {

		/*
		 * Se añaden los atributos del css
		 */
		atributos.add("color");
		atributos.add("font-size");
		atributos.add("text-align");
		atributos.add("font-style");

		
		/*
		 * Carga del fichero CSS por defecto
		 */
		File cssDefault = utils.getFileFromResources("Default.css");

		try {
			fileReader = new FileReader(cssDefault);
			Lexicon lex = new Lexicon(fileReader);
			Parser parser = new Parser(lex);
			defaultCssAst = parser.parse();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * Carga del fichero CSS que se encuentra en href usando buscaCss
		 */
		String cssUserPath = (String) astHtml.accept(buscaCss, null);
		cssUserPath = cssUserPath.substring(1, cssUserPath.length()-1); //Se eliminan las comillas
		File cssUser = utils.getFileFromResources(cssUserPath);

		try {
			fileReader = new FileReader(cssUser);
			Lexicon lex = new Lexicon(fileReader);
			Parser parser = new Parser(lex);
			hrefCssAst = parser.parse();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Object visit(Parrafo p, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Head h, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Se crea una línea para el h1 
	 */
	@Override
	public Object visit(H1 h1, Object param) {

		String texto = h1.h1;
		Map<String, String> atributos = new HashMap<>();

		for (String a: this.atributos) {
			String v = buscaParam.search("h1", a, hrefCssAst);

			if (v == null) {
				v = buscaParam.search("h1", a, defaultCssAst);
			}

			atributos.put(a, v);
		}

		Linea linea = new Linea("h1", texto, atributos);
		pagina.lineas.add(linea);

		return null;
	}

	/*
	 * Se crea una línea para el h2 
	 */
	@Override
	public Object visit(H2 h2, Object param) {

		String texto = h2.h2;
		Map<String, String> atributos = new HashMap<>();

		for (String a: this.atributos) {
			String v = buscaParam.search("h2", a, hrefCssAst);

			if (v == null) {
				v = buscaParam.search("h2", a, defaultCssAst);
			}

			atributos.put(a ,v);
		}

		Linea linea = new Linea("h2", texto, atributos);
		pagina.lineas.add(linea);

		return null;
	}

	@Override
	public Object visit(Title t, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Se crea una línea para el p
	 */
	@Override
	public Object visit(P p, Object param) {

		List<Bloque> bloques = p.bloque;
		Map<String, String> atributos = new HashMap<>();
		Linea linea = null;

		for (String a: this.atributos) {
			String v = buscaParam.search("p", a, hrefCssAst);

			if (v == null) {
				v = buscaParam.search("p", a, defaultCssAst);
			}

			atributos.put(a ,v);
		}

		for (Bloque b: bloques) {
			linea = new Linea(b.getTipoText(), b.getText(), atributos);
			pagina.lineas.add(linea);
		}

		return null;
	}

	@Override
	public Object visit(Body b, Object param) {

		List<Parrafo> parrafos = b.parrafo;

		for (Parrafo p: parrafos) {
			p.accept(this, null);
		}

		return pagina;
	}

	@Override
	public Object visit(Link l, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Se crea la página con las líneas generadas en el body
	 */
	@Override
	public Object visit(Programa p, Object param) {

		Head head = p.head;
		Body body = p.body;
		Pagina pagina = new Pagina();

		head.accept(this, null);
		pagina = (Pagina) body.accept(this, null);

		return pagina;
	}

	@Override
	public Object visit(Bloque b, Object param) {
		return null;
	}

	@Override
	public Object visit(BoldText b, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ItalicText i, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UnderlText u, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Text t, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
}