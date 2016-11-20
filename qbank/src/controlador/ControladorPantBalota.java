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
import modelo.BalotaDAO;
import modelo.Curso;
import modelo.CursoDAO;
import modelo.UserTipo;
import vista.PantallaBalota;

/**
 *
 * @author richard
 */
public class ControladorPantBalota implements ActionListener,KeyListener {
    boolean isNew = true;
    String currentCode = null;
    PantallaBalota vistaBalota = new PantallaBalota();
    BalotaDAO modeloBalota = new BalotaDAO();
    Par arr[];
    
    public ControladorPantBalota(PantallaBalota vistaBalota, BalotaDAO modeloBalota){
        this.modeloBalota = modeloBalota;
        this.vistaBalota = vistaBalota;
        this.vistaBalota.bttnGuardar.addActionListener(this);
        this.vistaBalota.bttnNuevo.addActionListener(this);
        this.vistaBalota.bttnEliminar.addActionListener(this);
        this.vistaBalota.bttnModificar.addActionListener(this);
    }
    
    public void inicializarBalotaCRUD(){
        CursoDAO curso = new CursoDAO();
        ArrayList<Curso> lista = curso.listarCurso();
        arr = new Par[lista.size()];
        int cont = 0;
        while(!lista.isEmpty()){
            Curso temp = lista.remove(0);
            arr[cont++] = new Par(temp.getCodigo(), temp.getNombre());
        }
        vistaBalota.cboxCurso.setModel(new javax.swing.DefaultComboBoxModel(arr));
        llenarTabla(vistaBalota.DatosBalota);
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
        modeloT.addColumn("Descripción");
        modeloT.addColumn("Curso");
        modeloT.addColumn("Estado");
        
        Object[] fila = new Object[5];
        
        CursoDAO curso = new CursoDAO();
        
        int numRegistros = modeloBalota.listarBalota().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloBalota.listarBalota().get(i).getCodigo();
            fila[1] = modeloBalota.listarBalota().get(i).getNombre();
            fila[2] = modeloBalota.listarBalota().get(i).getDescripcion();
            fila[3] = curso.getName(modeloBalota.listarBalota().get(i).getCurso()).getNombre();
            fila[4] = modeloBalota.listarBalota().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaBalota.bttnGuardar){
            String nombre = vistaBalota.txtNombre.getText();
            String descripcion = vistaBalota.txtDescripcion.getText();
            if(nombre.equalsIgnoreCase("") || descripcion.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            Par temp = (Par)vistaBalota.cboxCurso.getSelectedItem();
            String curso = String.valueOf(temp.getCode());
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloBalota.insertBalota(nombre, descripcion, curso);
            else
                rptaRegistro = modeloBalota.modificarBalota(currentCode, nombre, descripcion, curso);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaBalota.DatosBalota);
            vaciarCampos();
            
        }else if(e.getSource() == vistaBalota.bttnNuevo){
            
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaBalota.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaBalota.DatosBalota.getSelectedRow();
            int numFS = vistaBalota.DatosBalota.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                currentCode = String.valueOf(vistaBalota.DatosBalota.getValueAt(filaEditar, 0));
                vistaBalota.txtNombre.setText(String.valueOf(vistaBalota.DatosBalota.getValueAt(filaEditar, 1)));
                vistaBalota.txtDescripcion.setText(String.valueOf(vistaBalota.DatosBalota.getValueAt(filaEditar, 2)));
                String temp = String.valueOf(vistaBalota.DatosBalota.getValueAt(filaEditar, 3));
                int cont = 0;
                while(arr.length > cont && !temp.equals(arr[cont].getName()))
                    cont++;
                vistaBalota.cboxCurso.setSelectedItem(arr[cont]);
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaBalota.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaBalota.DatosBalota.getSelectedRow();
            int numFS = vistaBalota.DatosBalota.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaBalota.DatosBalota.getValueAt(filaEditar, 0));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar la balota con código: "+code+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloBalota.eliminarBalota(code);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaBalota.DatosBalota);
        }
    }
    
    public void vaciarCampos(){
        vistaBalota.txtNombre.setText("");
        vistaBalota.txtDescripcion.setText("");
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

