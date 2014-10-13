/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Emi
 */
public class Indexador {

    private HashMap<String, Integer> map;
    private String directorio;
    private StringTokenizer tokens;

    public Indexador(String dir) {
        this.map = new HashMap();
        this.directorio = dir;
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public void indexar() {
        try {
            File dir = new File(directorio);
            if (dir.exists()) {
                try (BufferedReader info = new BufferedReader(new InputStreamReader(new FileInputStream(new File(directorio)), "ISO-8859-1"))) {
                    String linea = info.readLine();
                    while (linea != null) {
                        //Obtiene un token por cada palabra que encuentre
                        tokens = new StringTokenizer(linea);
                        //Mientras existan tokens
                        while (tokens.hasMoreTokens()) {
                            String palabra = tokens.nextToken().toUpperCase();
                             //Expresion regular para eliminar todos los caracteres y solo dejar letras
                            Pattern exp = Pattern.compile("[^ÁÉÍÓÚA-Z]");
                            Matcher match = exp.matcher(palabra);
                            if (match.find()) {
                                palabra = match.replaceAll("");
                            }
                            if(palabra.length()>1){
                            //Carga datos en el HashMap
                            if (!map.containsKey(palabra)) {
                                map.put(palabra, 1);
                            } else {
                                int cantidad = map.get(palabra);
                                map.put(palabra, ++cantidad);
                            }}
                        }
                        linea = info.readLine();
                    }

                }

            }

        } catch (IOException e) {

        }
    }
}
