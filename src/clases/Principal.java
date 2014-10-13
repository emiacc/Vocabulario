package clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Emi
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
         Indexador in = new Indexador("C:\\Users\\Emi\\Desktop\\texto.txt");
         in.indexar();
         System.out.println(in.toString());
    }

}
