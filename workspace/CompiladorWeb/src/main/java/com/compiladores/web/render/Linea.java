package main.java.com.compiladores.web.render;

import java.util.HashMap;
import java.util.Map;

public class Linea {

    String tipo;
    String texto;
    Map<String, String> atributos = new HashMap<>();

    public Linea(String tipo, String texto,
        Map<String, String> atributos) {
            this.tipo = tipo;
            this.texto = texto;
            this.atributos = atributos;
        }

        public String toString() {
            return "TIPO: " + tipo + " TEXTO: " + texto +
            " ATRIBUTOS: " + atributos.toString();
        }
}