/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.ModeloPalabras;
import datos.Datos;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Emi
 */
public class Pantalla extends javax.swing.JFrame {

    private Worker worker;
    private static Queue cola;
    private static DefaultListModel modelCola, modelProcesados;
    private static ModeloPalabras modeloTabla;
    private boolean mensaje = true;
    private boolean filtrar = false;

    /**
     * Creates new form Pantalla
     */
    public Pantalla() {
        initComponents();
        cola = new LinkedList();
        worker = new Worker(jLabelEstado, botonBuscar);
        modelCola = new DefaultListModel();
        modelProcesados = new DefaultListModel();
        listCola.setModel(modelCola);
        listProcesados.setModel(modelProcesados);
        llenarListaProcesados();
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(1000);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);
        //tabla.removeColumn(tabla.getColumnModel().getColumn(0));

    }

    public static Object getArchivo() {
        if (cola.isEmpty()) {
            return null;
        }
        modelCola.removeElementAt(0);
        return cola.remove();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelBuscar = new javax.swing.JPanel();
        txtPalabra = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        botonBuscar = new javax.swing.JButton();
        panelIndexar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabelEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCola = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listProcesados = new javax.swing.JList();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));

        txtPalabra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPalabraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPalabraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPalabraKeyTyped(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Palabra", "Contador"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla);

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(txtPalabra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonBuscar)))
                .addContainerGap())
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelIndexar.setBorder(javax.swing.BorderFactory.createTitledBorder("Indexar"));
        panelIndexar.setMaximumSize(new java.awt.Dimension(100, 32767));

        jButton1.setText("Seleccionar Archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelEstado.setText("Estado Indexador");

        listCola.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivos en cola"));
        listCola.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listCola);

        listProcesados.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivos Procesados"));
        listProcesados.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listProcesados);

        javax.swing.GroupLayout panelIndexarLayout = new javax.swing.GroupLayout(panelIndexar);
        panelIndexar.setLayout(panelIndexarLayout);
        panelIndexarLayout.setHorizontalGroup(
            panelIndexarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIndexarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIndexarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jLabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        panelIndexarLayout.setVerticalGroup(
            panelIndexarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIndexarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabelEstado.getAccessibleContext().setAccessibleName("jLabelEstado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelIndexar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelIndexar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto", "txt");
        chooser.setFileFilter(filter);
        //chooser.showOpenDialog(null);
        if (chooser.showOpenDialog(null) != JFileChooser.CANCEL_OPTION) {
            botonBuscar.setEnabled(false);
            final File archivo = chooser.getSelectedFile();
            if (!archivo.getName().endsWith(".txt")) {
                JOptionPane.showMessageDialog(this, " Sólo archivos de texto", " Atención ", JOptionPane.WARNING_MESSAGE);
                if (worker.isTerminado()) {
                    botonBuscar.setEnabled(true);
                }
            } else {
                // Sumo el archivo a la cola de archivos sin procesar, si el worker ya habia finalizado creo uno nuevo
                cola.add(archivo);
                modelCola.addElement(archivo.getName());
                if (worker.isTerminado()) {
                    worker = new Worker(jLabelEstado, botonBuscar);
                    worker.execute();
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPalabraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPalabraKeyPressed
        // TODO add your handling code here:  
        if (evt.getExtendedKeyCode() == 8) {
            txtPalabra.setText("");
            if (!mensaje) {
                modeloTabla.removeAllRows();
                modeloTabla.fireTableDataChanged();
                filtrar = false;
            }
        } else if (evt.getExtendedKeyCode() == 10 && botonBuscar.isEnabled()==true) {
            this.botonBuscarActionPerformed(null);            
        }
    }//GEN-LAST:event_txtPalabraKeyPressed

    private void txtPalabraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPalabraKeyTyped
        // TODO add your handling code here:       
    }//GEN-LAST:event_txtPalabraKeyTyped

    private void txtPalabraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPalabraKeyReleased
        // TODO add your handling code here:
        String c = txtPalabra.getText().toUpperCase();
        txtPalabra.setText(c);
        if (filtrar) {
            for (int i = modeloTabla.getRowCount() - 1; i >= 0; i--) {
                String palabra = (String) modeloTabla.getValueAt(i, 1);
                if (palabra.indexOf(c) != 0) {
                    modeloTabla.removeRow(i);
                }
            }
            modeloTabla.fireTableDataChanged();
        }
    }//GEN-LAST:event_txtPalabraKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (!botonBuscar.isEnabled()) {
                JOptionPane.showMessageDialog(this, "Espere unos segundos. Se está actualiazndo la base de datos", "Aguarde", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int id = (int) tabla.getValueAt(tabla.getSelectedRow(), 0);
            List<String> lista = Datos.getInstance().getDocumentos(id);
            if (lista == null) {
                return;
            }
            String r = "";
            for (String s : lista) {
                r += s + "<br>";
            }
            JOptionPane.showMessageDialog(this,
                    "<html><body><p style='width: 200px;'>" + r + "</body></html>",
                    "Documentos en los que aparece", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        // TODO add your handling code here:
        String c = txtPalabra.getText().toUpperCase();
        modeloTabla = new ModeloPalabras(Datos.getInstance().getListado(c));
        if (modeloTabla != null) {
            tabla.setModel(modeloTabla);
        }
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(1000);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);
        //tabla.removeColumn(tabla.getColumnModel().getColumn(0));
        //centrar contador
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        if (mensaje) {
            JOptionPane.showMessageDialog(this, "Al hacer doble click sobre una palabra podrá ver en los documentos en los que aparece.\nPuede seguir escribiendo la palabra para filrtar la busqueda.\nPuede ordenar la búsqueda haciendo click en la cabecera ed la columna.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        filtrar = true;
        mensaje = false;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modeloTabla);
        tabla.setRowSorter(elQueOrdena);
    }//GEN-LAST:event_botonBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listCola;
    private javax.swing.JList listProcesados;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelIndexar;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtPalabra;
    // End of variables declaration//GEN-END:variables

    public static void llenarListaProcesados() {
        List<String> lista = Datos.getInstance().getDocumentos();
        if (lista == null) {
            return;
        }
        modelProcesados.clear();
        for (String s : lista) {
            modelProcesados.addElement(s);
        }

    }
}
