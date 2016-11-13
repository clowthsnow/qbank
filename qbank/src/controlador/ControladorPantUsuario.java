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
import modelo.*;
import vista.*;
/**
 *
 * @author richard
 */
public class ControladorPantUsuario implements ActionListener,KeyListener {
    boolean isNew = true;
    String currentCode = null;
    PantallaUsuario vistaUsuario = new PantallaUsuario();
    UsuarioDAO modeloUsuario = new UsuarioDAO();
    
    public ControladorPantUsuario(PantallaUsuario vistaUsuario, UsuarioDAO modeloUsuario){
        this.modeloUsuario = modeloUsuario;
        this.vistaUsuario = vistaUsuario;
        this.vistaUsuario.bttnGuardar.addActionListener(this);
        this.vistaUsuario.bttnNuevo.addActionListener(this);
        this.vistaUsuario.bttnEliminar.addActionListener(this);
        this.vistaUsuario.bttnModificar.addActionListener(this);
    }
    
    public void inicializarusuarioCRUD(){
        UserTipoDAO userTipo = new UserTipoDAO();
        ArrayList<UserTipo> lista = userTipo.listarUserTipo();
        String array[] = new String[lista.size()];
        int cont = 0;
        while(!lista.isEmpty()){
            UserTipo temp = lista.remove(0);
            array[cont++] = temp.getCodigo();
        }
        vistaUsuario.cboxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(array));
        llenarTabla(vistaUsuario.DatosUsuarios);
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
        modeloT.addColumn("Contraseña");
        modeloT.addColumn("Tipo");
        modeloT.addColumn("Estado");
        
        Object[] fila = new Object[5];
        
        int numRegistros = modeloUsuario.listarUsuario().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloUsuario.listarUsuario().get(i).getCodigo();
            fila[1] = modeloUsuario.listarUsuario().get(i).getNombre();
            fila[2] = modeloUsuario.listarUsuario().get(i).getContraseña();
            fila[3] = modeloUsuario.listarUsuario().get(i).getTipo();
            fila[4] = modeloUsuario.listarUsuario().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
        tablaD.getColumnModel().getColumn(2).setMinWidth(0);
        tablaD.getColumnModel().getColumn(2).setMaxWidth(0);
        tablaD.getColumnModel().getColumn(2).setWidth(0);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaUsuario.bttnGuardar){
            String nombre = vistaUsuario.txtNombre.getText();
            String contraseña = String.valueOf(vistaUsuario.txtContraseña.getPassword());
            if(nombre.equalsIgnoreCase("") || contraseña.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            String tipo = String.valueOf(vistaUsuario.cboxTipo.getSelectedItem());
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloUsuario.insertUsuario(nombre, contraseña, tipo);
            else
                rptaRegistro = modeloUsuario.modificarUsuario(currentCode, nombre, contraseña, tipo);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaUsuario.DatosUsuarios);
            vaciarCampos();
            
        }else if(e.getSource() == vistaUsuario.bttnNuevo){
            
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaUsuario.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaUsuario.DatosUsuarios.getSelectedRow();
            int numFS = vistaUsuario.DatosUsuarios.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                currentCode = String.valueOf(vistaUsuario.DatosUsuarios.getValueAt(filaEditar, 0));
                vistaUsuario.txtNombre.setText(String.valueOf(vistaUsuario.DatosUsuarios.getValueAt(filaEditar, 1)));
                vistaUsuario.txtContraseña.setText(String.valueOf(vistaUsuario.DatosUsuarios.getValueAt(filaEditar, 2)));
                vistaUsuario.cboxTipo.setSelectedItem(String.valueOf(vistaUsuario.DatosUsuarios.getValueAt(filaEditar, 3)));
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaUsuario.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaUsuario.DatosUsuarios.getSelectedRow();
            int numFS = vistaUsuario.DatosUsuarios.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaUsuario.DatosUsuarios.getValueAt(filaEditar, 0));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar al usuario con código: "+code+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloUsuario.eliminarUsuario(code);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaUsuario.DatosUsuarios);
        }
    }
    
    public void vaciarCampos(){
        vistaUsuario.txtNombre.setText("");
        vistaUsuario.txtContraseña.setText("");
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
