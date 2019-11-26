package main.java.com.compiladores.web.main;

import java.io.File;
import java.net.URL;


public class Utils {

    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("El archivo no existe");
        } else {
            return new File(resource.getFile());
        }
    }
}