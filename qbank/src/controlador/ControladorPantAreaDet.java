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
    
    public ControladorPantAreaDet(PantallaAreaDet vistaAreaDet, AreaDetalleDAO modeloAreaDet){
        this.modeloAreaDet = modeloAreaDet;
        this.vistaAreaDet = vistaAreaDet;
        this.vistaAreaDet.bttnGuardar.addActionListener(this);
        this.vistaAreaDet.bttnNuevo.addActionListener(this);
        this.vistaAreaDet.bttnEliminar.addActionListener(this);
        this.vistaAreaDet.bttnModificar.addActionListener(this);
    }
    
    public void inicializarAreaDetCRUD(){
        AreaDAO userTipo = new AreaDAO();
        ArrayList<Area> lista = userTipo.listarArea();
        String array[] = new String[lista.size()];
        int cont = 0;
        while(!lista.isEmpty()){
            Area temp = lista.remove(0);
            array[cont++] = temp.getCodigo();
        }
        vistaAreaDet.cboxArea.setModel(new DefaultComboBoxModel<>(array));
        CursoDAO curso = new CursoDAO();
        ArrayList<Curso> lista2 = curso.listarCurso();
        String array2[] = new String[lista2.size()];
        cont = 0;
        while(!lista2.isEmpty()){
            Curso temp = lista2.remove(0);
            array2[cont++] = temp.getCodigo();
        }
        vistaAreaDet.cboxCurso.setModel(new DefaultComboBoxModel<>(array2));
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
        
        int numRegistros = modeloAreaDet.listarAreaDetalle().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloAreaDet.listarAreaDetalle().get(i).getCodigo();
            fila[1] = modeloAreaDet.listarAreaDetalle().get(i).getCurso();
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
            String area = String.valueOf(vistaAreaDet.cboxArea.getSelectedItem());
            String curso = String.valueOf(vistaAreaDet.cboxCurso.getSelectedItem());
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
                currentCode = String.valueOf(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 0));
                currentCourse = String.valueOf(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 1));
                vistaAreaDet.cboxArea.setSelectedItem(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 0));
                vistaAreaDet.cboxCurso.setSelectedItem(vistaAreaDet.DatosAreaDetalle.getValueAt(filaEditar, 1));
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

