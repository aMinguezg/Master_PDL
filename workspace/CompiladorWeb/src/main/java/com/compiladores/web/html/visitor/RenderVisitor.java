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

//Visitor para recorrer el arbol
//Renderiza la pagina
public class RenderVisitor implements Visitor {

	BuscaCssVisitor buscaCss = new BuscaCssVisitor();
    BuscarParamEnCssVisitor buscaParam = new BuscarParamEnCssVisitor();
    AstHtml astHtml;
    AstCss defaultCssAst;
    AstCss userCssAst;
    Utils utils = new Utils();
    List<String> atributos = new ArrayList<String>();
    Pagina pagina = new Pagina();

    public RenderVisitor(AstHtml astHtml) throws FileNotFoundException {

    	File cssDefault = utils.getFileFromResources("Default.css");
        FileReader fileReader = null;

        atributos.add("color");
        atributos.add("font-size");
        atributos.add("text-align");
        atributos.add("font-style");

        try {
            fileReader = new FileReader(cssDefault);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Lexicon lex = new Lexicon(fileReader);
        Parser parser = new Parser(lex);
        String cssUserPath = (String) astHtml.accept(buscaCss, null);
        cssUserPath = cssUserPath.substring(1, cssUserPath.length()-1);
        File cssUser = utils.getFileFromResources(cssUserPath);

        defaultCssAst = parser.parse();

        try {
            fileReader = new FileReader(cssUser);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        lex = new Lexicon(fileReader);
        parser = new Parser(lex);
        userCssAst = parser.parse();

    }

    @Override
    public Object visit(Parrafo p, Object param) {
        return null;
    }

    @Override
    public Object visit(Head h, Object param) {
        return null;
    }

    @Override
    public Object visit(H1 h1, Object param) {

        String texto = h1.h1;
        Map<String, String> atributos = new HashMap<>();

        for (String a: this.atributos) {
            String v = buscaParam.search("h1", a, userCssAst);

            if (v == null) {
                v = buscaParam.search("h1", a, defaultCssAst);
            }

            atributos.put(a, v);
        }

        Linea linea = new Linea("h1", texto, atributos);
        pagina.lineas.add(linea);

        return null;
    }

    @Override
        public Object visit(H2 h2, Object param) {

        String texto = h2.h2;
        Map<String, String> atributos = new HashMap<>();

        for (String a: this.atributos) {
            String v = buscaParam.search("h2", a, userCssAst);

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
        return null;
    }

    @Override
    public Object visit(P p, Object param) {

        List<Bloque> bloques = p.bloque;
        Map<String, String> atributos = new HashMap<>();
        Linea linea = null;

        for (String a: this.atributos) {
            String v = buscaParam.search("p", a, userCssAst);

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
        return null;
    }

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