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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Area;
import modelo.AreaDAO;
import modelo.AreaDetalleDAO;
import modelo.Curso;
import modelo.CursoDAO;
import vista.PantallaAreaDet;

/**
 *
 * @author richard
 */
public class ControladorPantAreaDet implements ActionListener,KeyListener {
    boolean isNew = true;
    String currentCode = null;
    String currentCourse = null;
    PantallaAreaDet vistaAreaDet = new PantallaAreaDet();
    AreaDetalleDAO modeloAreaDet = new AreaDetalleDAO();
    Par arr1[];
    Par arr2[];
    
    public ControladorPantAreaDet(PantallaAreaDet vistaAreaDet, AreaDetalleDAO modeloAreaDet){
        this.modeloAreaDet = modeloAreaDet;
        this.vistaAreaDet = vistaAreaDet;
        this.vistaAreaDet.bttnGuardar.addActionListener(this);
        this.vistaAreaDet.bttnNuevo.addActionListener(this);
        this.vistaAreaDet.bttnEliminar.addActionListener(this);
        this.vistaAreaDet.bttnModificar.addActionListener(this);
    }
    
    public void inicializarAreaDetCRUD(){
        AreaDAO area = new AreaDAO();
        ArrayList<Area> lista = area.listarArea();
        arr1 = new Par[lista.size()];
        int cont = 0;
        while(!lista.isEmpty()){
            Area temp = lista.remove(0);
            arr1[cont++] = new Par(temp.getCodigo(), temp.getNombre());
        }
        vistaAreaDet.cboxArea.setModel(new DefaultComboBoxModel(arr1));
        CursoDAO curso = new CursoDAO();
        ArrayList<Curso> lista2 = curso.listarCurso();
        arr2 = new Par[lista2.size()];
        cont = 0;
        while(!lista2.isEmpty()){
            Curso temp = lista2.remove(0);
            arr2[cont++] = new Par(temp.getCodigo(), temp.getNombre());
        }
        vistaAreaDet.cboxCurso.setModel(new DefaultComboBoxModel(arr2));
        llenarTabla(vistaAreaDet.DatosAreaDetalle);
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
        modeloT.addColumn("Área");
        modeloT.addColumn("Curso");
        modeloT.addColumn("Cantidad");
        modeloT.addColumn("Estado");
        
        Object[] fila = new Object[4];
        CursoDAO curso = new CursoDAO();
        AreaDAO area = new AreaDAO();
        
        int numRegistros = modeloAreaDet.listarAreaDetalle().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = area.getName(modeloAreaDet.listarAreaDetalle().get(i).getCodigo()).getNombre();
            fila[1] = curso.getName(modeloAreaDet.listarAreaDetalle().get(i).getCurso()).getNombre();
            fila[2] = modeloAreaDet.listarAreaDetalle().get(i).getCantidad();
            fila[3] = modeloAreaDet.listarAreaDetalle().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaAreaDet.bttnGuardar){
            String cantidad = vistaAreaDet.txtCantidad.getText();
            if(cantidad.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            Par temp = (Par)vistaAreaDet.cboxArea.getSelectedItem();
            String area = String.valueOf(temp.getCode());
            temp = (Par)vistaAreaDet.cboxCurso.getSelectedItem();
            String curso = String.valueOf(temp.getCode());
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloAreaDet.insertAreaDetalle(area, curso, cantidad);
            else
                rptaRegistro = modeloAreaDet.modificarAreaDetalle(area, curso, cantidad, currentCode, currentCourse);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaAreaDet.DatosAreaDetalle);
            vaciarCampos();
            
        }else if(e.getSource() == vistaAreaDet.bttnNuevo){
            
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaAreaDet.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaAreaDet.DatosAreaDetalle.getSelectedRow();
            int numFS = vistaAreaDet.DatosAreaDetalle.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                int cont = 0;
                String temp = String.valueOf(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 0));
                while(arr1.length > cont && !temp.equals(arr1[cont].getName()))
                    cont++;
                currentCode = arr1[cont].getCode();
                vistaAreaDet.cboxArea.setSelectedItem(arr1[cont]);
                cont = 0;
                temp = String.valueOf(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 1));
                while(arr2.length > cont && !temp.equals(arr2[cont].getName()))
                    cont++;
                currentCourse = arr2[cont].getCode();
                vistaAreaDet.cboxCurso.setSelectedItem(arr2[cont]);
                vistaAreaDet.txtCantidad.setText(String.valueOf(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 2)));
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaAreaDet.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaAreaDet.DatosAreaDetalle.getSelectedRow();
            int numFS = vistaAreaDet.DatosAreaDetalle.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 0));
                String course = String.valueOf(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 1));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar al detalle de área: "+code+" y curso: "+course+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloAreaDet.eliminarAreaDetalle(code,course);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaAreaDet.DatosAreaDetalle);
        }
    }
    
    public void vaciarCampos(){
        vistaAreaDet.txtCantidad.setText("");
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

