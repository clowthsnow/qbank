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
import modelo.UserTipoDAO;
import vista.PantallaUserTipo;

/**
 *
 * @author richard
 */
public class ControladorPantUserTipo implements ActionListener,KeyListener{

    boolean isNew = true;
    String currentCode = null;
    PantallaUserTipo vistaUserTipo = new PantallaUserTipo();
    UserTipoDAO modeloUserTipo = new UserTipoDAO();
    
    public ControladorPantUserTipo(PantallaUserTipo vistaUserTipo, UserTipoDAO modeloUserTipo){
        this.modeloUserTipo = modeloUserTipo;
        this.vistaUserTipo = vistaUserTipo;
        this.vistaUserTipo.bttnGuardar.addActionListener(this);
        this.vistaUserTipo.bttnNuevo.addActionListener(this);
        this.vistaUserTipo.bttnEliminar.addActionListener(this);
        this.vistaUserTipo.bttnModificar.addActionListener(this);
    }
    
    public void inicializarUserTipoCRUD(){
        llenarTabla(vistaUserTipo.DatosUserTipo);
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
        
        int numRegistros = modeloUserTipo.listarUserTipo().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloUserTipo.listarUserTipo().get(i).getCodigo();
            fila[1] = modeloUserTipo.listarUserTipo().get(i).getDescripcion();
            fila[2] = modeloUserTipo.listarUserTipo().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaUserTipo.bttnGuardar){
            String codigo = vistaUserTipo.txtCodigo.getText();
            String descripcion = vistaUserTipo.txtDescripcion.getText();
            if(codigo.equalsIgnoreCase("") || descripcion.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloUserTipo.insertUserTipo(codigo, descripcion);
            else
                rptaRegistro = modeloUserTipo.modificarUserTipo(codigo, currentCode, descripcion);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaUserTipo.DatosUserTipo);
            vaciarCampos();
            
        }else if(e.getSource() == vistaUserTipo.bttnNuevo){
      
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaUserTipo.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaUserTipo.DatosUserTipo.getSelectedRow();
            int numFS = vistaUserTipo.DatosUserTipo.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                currentCode = String.valueOf(vistaUserTipo.DatosUserTipo.getValueAt(filaEditar, 0));
                vistaUserTipo.txtCodigo.setText(String.valueOf(vistaUserTipo.DatosUserTipo.getValueAt(filaEditar, 0)));
                vistaUserTipo.txtDescripcion.setText(String.valueOf(vistaUserTipo.DatosUserTipo.getValueAt(filaEditar, 1)));
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaUserTipo.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaUserTipo.DatosUserTipo.getSelectedRow();
            int numFS = vistaUserTipo.DatosUserTipo.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaUserTipo.DatosUserTipo.getValueAt(filaEditar, 0));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar al usuario con código: "+code+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloUserTipo.eliminarUserTipo(code);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaUserTipo.DatosUserTipo);
        }
    }
    
    public void vaciarCampos(){
        vistaUserTipo.txtCodigo.setText("");
        vistaUserTipo.txtDescripcion.setText("");
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
