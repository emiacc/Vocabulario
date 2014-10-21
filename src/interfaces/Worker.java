/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Indexador;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author Emi
 */
public class Worker extends SwingWorker<Void, Void> {

    private final JLabel estado;
    private final JTextField buscador;
    private boolean terminado;
    
    public Worker(JLabel estado, JTextField buscador) {
        this.estado = estado;        
        this.buscador = buscador;        
        this.terminado = true;
    }

    @Override
    protected Void doInBackground() throws Exception {   
        Object o = Pantalla.getArchivo();
        if(o == null) return null;
        terminado = false;
        File archivo = (File) o;
        this.estado.setText("procesando: "+archivo.getName());
        Indexador in = new Indexador(archivo.getPath());
        //Thread.sleep(10000);
        this.estado.setText(in.indexar());
        Pantalla.llenarListaProcesados();
        doInBackground();
        return null;
    }
    
    @Override
    protected void done() {
        estado.setIcon(null);
        terminado = true;
        buscador.setEnabled(true);
        //this.estado.setText("Desocupado");
    }    

    public boolean isTerminado() {
        return terminado;
    }

}
