/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Emi
 */
public class Datos {

    public static Datos instance = new Datos();

    private Datos() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Error " + ex.getMessage());
        }
    }

    public static Datos getInstance() {
        return instance;
    }

    public boolean insertarTabla(HashMap map, String archivo) {
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBVocabulario.s3db");
            con.setAutoCommit(false);
            Statement stat = con.createStatement();

            //Guardar Documento
            ResultSet rs = stat.executeQuery("select id from Documentos where nombre = '" + archivo + "';");
            if (rs.next()) {
                System.out.println("Documento ya procesado");
                stat.close();
                con.commit();
                con.close();
                return false;
            }
            stat.executeUpdate("insert into Documentos (nombre) values ('" + archivo + "');");

            //Guardar Palabras
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                rs = stat.executeQuery("select contador from Palabras where palabra = '" + e.getKey() + "';");
                if (rs.next()) {
                    //update
                    int cont = rs.getInt(1) + (int) e.getValue();
                    stat.executeUpdate("update Palabras set contador = " + cont + " where palabra = '" + e.getKey() + "';");
                } else {
                    //insert
                    stat.executeUpdate("insert into Palabras values ('" + e.getKey() + "'," + e.getValue() + ");");
                }
            }

            stat.close();
            con.commit();
            con.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return false;
        }
        return true;
    }
}
