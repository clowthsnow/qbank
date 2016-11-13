/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.AreaDAO;
import vista.PantallaArea;

/**
 *
 * @author richard
 */
public class ControladorPantArea implements ActionListener,KeyListener{
    boolean isNew = true;
    String currentCode = null;
    PantallaArea vistaArea = new PantallaArea();
    AreaDAO modeloArea = new AreaDAO();
    
    public ControladorPantArea(PantallaArea vistaArea, AreaDAO modeloArea){
        this.modeloArea = modeloArea;
        this.vistaArea = vistaArea;
        this.vistaArea.bttnGuardar.addActionListener(this);
        this.vistaArea.bttnNuevo.addActionListener(this);
        this.vistaArea.bttnEliminar.addActionListener(this);
        this.vistaArea.bttnModificar.addActionListener(this);
    }
    
    public void inicializarAreaCRUD(){
        llenarTabla(vistaArea.DatosArea);
    }
    
    public void llenarTabla(JTable tablaD){
        DefaultTableModel modeloT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        tablaD.setModel(modeloT);
        modeloT.addColumn("Código");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Preguntas");
        modeloT.addColumn("Estado");
        
        Object[] fila = new Object[4];
        
        int numRegistros = modeloArea.listarArea().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloArea.listarArea().get(i).getCodigo();
            fila[1] = modeloArea.listarArea().get(i).getNombre();
            fila[2] = modeloArea.listarArea().get(i).getPreguntas();
            fila[3] = modeloArea.listarArea().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaArea.bttnGuardar){
            String nombre = vistaArea.txtNombre.getText();
            String preguntas = vistaArea.txtPreguntas.getText();
            if(nombre.equalsIgnoreCase("") || preguntas.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloArea.insertArea(nombre, preguntas);
            else
                rptaRegistro = modeloArea.modificarArea(currentCode, nombre, preguntas);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaArea.DatosArea);
            vaciarCampos();
            
        }else if(e.getSource() == vistaArea.bttnNuevo){
            
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaArea.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaArea.DatosArea.getSelectedRow();
            int numFS = vistaArea.DatosArea.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                currentCode = String.valueOf(vistaArea.DatosArea.getValueAt(filaEditar, 0));
                vistaArea.txtNombre.setText(String.valueOf(vistaArea.DatosArea.getValueAt(filaEditar, 1)));
                vistaArea.txtPreguntas.setText(String.valueOf(vistaArea.DatosArea.getValueAt(filaEditar, 2)));
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaArea.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaArea.DatosArea.getSelectedRow();
            int numFS = vistaArea.DatosArea.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaArea.DatosArea.getValueAt(filaEditar, 0));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar al usuario con código: "+code+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloArea.eliminarArea(code);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaArea.DatosArea);
        }
    }
    
    public void vaciarCampos(){
        vistaArea.txtNombre.setText("");
        vistaArea.txtPreguntas.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
