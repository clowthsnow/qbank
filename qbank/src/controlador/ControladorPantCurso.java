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
import modelo.CursoDAO;
import modelo.UserTipo;
import modelo.UserTipoDAO;
import modelo.UsuarioDAO;
import vista.PantallaCurso;
import vista.PantallaUsuario;

/**
 *
 * @author richard
 */
public class ControladorPantCurso implements ActionListener,KeyListener{
    boolean isNew = true;
    String currentCode = null;
    PantallaCurso vistaCurso = new PantallaCurso();
    CursoDAO modeloCurso = new CursoDAO();
    
    public ControladorPantCurso(PantallaCurso vistaCurso, CursoDAO modeloCurso){
        this.modeloCurso = modeloCurso;
        this.vistaCurso = vistaCurso;
        this.vistaCurso.bttnGuardar.addActionListener(this);
        this.vistaCurso.bttnNuevo.addActionListener(this);
        this.vistaCurso.bttnEliminar.addActionListener(this);
        this.vistaCurso.bttnModificar.addActionListener(this);
    }
    
    public void inicializarCursoCRUD(){
        llenarTabla(vistaCurso.DatosCurso);
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
        modeloT.addColumn("Estado");
        
        Object[] fila = new Object[3];
        
        int numRegistros = modeloCurso.listarCurso().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloCurso.listarCurso().get(i).getCodigo();
            fila[1] = modeloCurso.listarCurso().get(i).getNombre();
            fila[2] = modeloCurso.listarCurso().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaCurso.bttnGuardar){
            String nombre = vistaCurso.txtNombre.getText();
            if(nombre.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloCurso.insertCurso(nombre);
            else
                rptaRegistro = modeloCurso.modificarCurso(currentCode, nombre);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaCurso.DatosCurso);
            vaciarCampos();
            
        }else if(e.getSource() == vistaCurso.bttnNuevo){
            
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaCurso.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaCurso.DatosCurso.getSelectedRow();
            int numFS = vistaCurso.DatosCurso.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                currentCode = String.valueOf(vistaCurso.DatosCurso.getValueAt(filaEditar, 0));
                vistaCurso.txtNombre.setText(String.valueOf(vistaCurso.DatosCurso.getValueAt(filaEditar, 1)));
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaCurso.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaCurso.DatosCurso.getSelectedRow();
            int numFS = vistaCurso.DatosCurso.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaCurso.DatosCurso.getValueAt(filaEditar, 0));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar al curso con código: "+code+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloCurso.eliminarCurso(code);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaCurso.DatosCurso);
        }
    }
    
    public void vaciarCampos(){
        vistaCurso.txtNombre.setText("");
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
