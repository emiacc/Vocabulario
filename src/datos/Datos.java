/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
    public List getDocumentos(){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:DBVocabulario.s3db");
            stat = con.createStatement();
            rs = stat.executeQuery("select nombre from Documentos;");
            ArrayList<String> lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString(1));
            }          
            return lista;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }
    }
    
    public boolean consultarDocumento(String archivo) {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:DBVocabulario.s3db");
            stat = con.createStatement();
            rs = stat.executeQuery("select id from Documentos where nombre = '" + archivo + "';");
            if (rs.next()) {
                System.out.println("Documento ya procesado");
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return false;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }
    }

    public boolean insertarTabla(HashMap map, String archivo) {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        int idPalabra,idArchivo;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:DBVocabulario.s3db");
            con.setAutoCommit(false);
            stat = con.createStatement();

            //Guardar Documento
            stat.executeUpdate("insert into Documentos (nombre) values ('" + archivo + "');");
            rs = stat.executeQuery("SELECT last_insert_rowid();");
            idArchivo = rs.getInt(1);
            //Guardar Palabras
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                rs = stat.executeQuery("select id,contador from Palabras where palabra = '" + e.getKey() + "';");
                if (rs.next()) {
                    //update
                    idPalabra = rs.getInt(1);
                    int cont = rs.getInt(2) + (int) e.getValue();
                    stat.executeUpdate("update Palabras set contador = " + cont + " where palabra = '" + e.getKey() + "';");
                } else {
                    //insert
                    stat.executeUpdate("insert into Palabras (palabra,contador) values ('" + e.getKey() + "'," + e.getValue() + ");");
                    rs = stat.executeQuery("SELECT last_insert_rowid();");
                    idPalabra = rs.getInt(1);
                }
                stat.executeUpdate("insert into PalabrasDocumentos values ("+idPalabra+","+idArchivo+");");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return false;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }        
    }
    
    //FILTRO
    // select d.nombre from Palabras p, Documentos d, PalabrasDocumentos x where x.idPalabra=p.id and x.idDocumento = d.id and p.palabra LIKE "%%"
}
