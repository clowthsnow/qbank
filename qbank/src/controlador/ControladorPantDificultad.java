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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.DificultadDAO;
import modelo.UserTipo;
import modelo.UserTipoDAO;
import modelo.UsuarioDAO;
import vista.PantallaDificultadTipo;
import vista.PantallaUsuario;

/**
 *
 * @author richard
 */
public class ControladorPantDificultad implements ActionListener,KeyListener {
    boolean isNew = true;
    String currentCode = null;
    PantallaDificultadTipo vistaDificultad = new PantallaDificultadTipo();
    DificultadDAO modeloDificultad = new DificultadDAO();
    
    public ControladorPantDificultad(PantallaDificultadTipo vistaDificultad, DificultadDAO modeloDificultad){
        this.modeloDificultad = modeloDificultad;
        this.vistaDificultad = vistaDificultad;
        this.vistaDificultad.bttnGuardar.addActionListener(this);
        this.vistaDificultad.bttnNuevo.addActionListener(this);
        this.vistaDificultad.bttnEliminar.addActionListener(this);
        this.vistaDificultad.bttnModificar.addActionListener(this);
    }
    
    public void inicializarDificultadCRUD(){
        llenarTabla(vistaDificultad.DatosDificultadTipo);
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
        modeloT.addColumn("Descripción");
        modeloT.addColumn("Estado");
        
        Object[] fila = new Object[3];
        
        int numRegistros = modeloDificultad.listarDificultad().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloDificultad.listarDificultad().get(i).getCodigo();
            fila[1] = modeloDificultad.listarDificultad().get(i).getDescripcion();
            fila[2] = modeloDificultad.listarDificultad().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaDificultad.bttnGuardar){
            String descripcion = vistaDificultad.txtDescripcion.getText();
            if(descripcion.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloDificultad.insertDificultad(descripcion);
            else
                rptaRegistro = modeloDificultad.modificarDificultad(currentCode, descripcion);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaDificultad.DatosDificultadTipo);
            vaciarCampos();
            
        }else if(e.getSource() == vistaDificultad.bttnNuevo){
            
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaDificultad.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaDificultad.DatosDificultadTipo.getSelectedRow();
            int numFS = vistaDificultad.DatosDificultadTipo.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                currentCode = String.valueOf(vistaDificultad.DatosDificultadTipo.getValueAt(filaEditar, 0));
                vistaDificultad.txtDescripcion.setText(String.valueOf(vistaDificultad.DatosDificultadTipo.getValueAt(filaEditar, 1)));
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaDificultad.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaDificultad.DatosDificultadTipo.getSelectedRow();
            int numFS = vistaDificultad.DatosDificultadTipo.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaDificultad.DatosDificultadTipo.getValueAt(filaEditar, 0));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar al usuario con código: "+code+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloDificultad.eliminarDificultad(code);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaDificultad.DatosDificultadTipo);
        }
    }
    
    public void vaciarCampos(){
        vistaDificultad.txtDescripcion.setText("");
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
