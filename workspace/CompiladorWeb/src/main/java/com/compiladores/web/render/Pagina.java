package main.java.com.compiladores.web.render;

import java.util.ArrayList;
import java.util.List;

public class Pagina {

    public List<Linea> lineas = new ArrayList<Linea>();

    public String toString() {

        String s = "";
        int i = 0;

        for (Linea l: lineas) {
            s = s.concat("LINEA " + i + ": " + l.toString() + "\n");
            i++;
        }

        return s;
    }

}