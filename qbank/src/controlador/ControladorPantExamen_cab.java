/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.AreaDAO;
import modelo.BalotaDAO;
import modelo.Examen_cabDAO;
import modelo.Examen_detDAO;
import vista.PantallaExamen_cab;
import vista.PantallaListaPreguntas;

/**
 *
 * @author richard
 */
public class ControladorPantExamen_cab implements ActionListener,KeyListener {
    boolean isNew = true;
    String currentCode = null;
    String currentCourse = null;
    PantallaExamen_cab vistaExamen_cab = new PantallaExamen_cab();
    Examen_cabDAO modeloExamen_cab = new Examen_cabDAO();
    JDesktopPane Escritorio;
    
    public ControladorPantExamen_cab(PantallaExamen_cab vistaExamen_cab, Examen_cabDAO modeloExamen_cab, JDesktopPane Escritorio){
        this.modeloExamen_cab = modeloExamen_cab;
        this.vistaExamen_cab = vistaExamen_cab;
        this.vistaExamen_cab.bttnGuardar.addActionListener(this);
        this.vistaExamen_cab.bttnNuevo.addActionListener(this);
        this.vistaExamen_cab.bttnEliminar.addActionListener(this);
        this.vistaExamen_cab.bttnModificar.addActionListener(this);
        this.Escritorio = Escritorio;
    }
    
    public void inicializarExamen_cabCRUD(){
        llenarTabla(vistaExamen_cab.dataExamen_cab);
        vistaExamen_cab.dataExamen_cab.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int filaEditar = vistaExamen_cab.dataExamen_cab.getSelectedRow();
                    String temp1 = String.valueOf(vistaExamen_cab.dataExamen_cab.getValueAt(filaEditar, 0));
                    String temp2 = String.valueOf(vistaExamen_cab.dataExamen_cab.getValueAt(filaEditar, 1));
                    Escritorio.removeAll();
                    Escritorio.repaint();
                    PantallaListaPreguntas internal = new PantallaListaPreguntas("Examen",true,true,true,true);
                    Examen_detDAO det  = new Examen_detDAO();
                    ControladorPantExamen_det controladorC = new ControladorPantExamen_det(internal, det,Escritorio,temp1);
                    controladorC.inicializarExamen_cabCRUD();
                    Escritorio.add(internal);
                    internal.show();
                }
            }
        });
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
        modeloT.addColumn("Proceso");
        modeloT.addColumn("Fecha");
        modeloT.addColumn("Estado");
        
        Object[] fila = new Object[4];
        AreaDAO area = new AreaDAO();
        
        int numRegistros = modeloExamen_cab.listarExamen_cab().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloExamen_cab.listarExamen_cab().get(i).getCodigo();
            fila[1] = modeloExamen_cab.listarExamen_cab().get(i).getProceso();
            fila[2] = modeloExamen_cab.listarExamen_cab().get(i).getFecha();
            fila[3] = modeloExamen_cab.listarExamen_cab().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaExamen_cab.bttnGuardar){
            String proceso = vistaExamen_cab.txtProceso.getText();
            String fecha = vistaExamen_cab.txtFecha.getText();
            if(proceso.equalsIgnoreCase("") || fecha.equals("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloExamen_cab.insertExamen_cab(proceso, fecha);
            else
                rptaRegistro = modeloExamen_cab.modificarExamen_cab(currentCode, proceso, fecha);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaExamen_cab.dataExamen_cab);
            vaciarCampos();
            
        }else if(e.getSource() == vistaExamen_cab.bttnNuevo){
            
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaExamen_cab.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaExamen_cab.dataExamen_cab.getSelectedRow();
            int numFS = vistaExamen_cab.dataExamen_cab.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                int cont = 0;
                currentCode = String.valueOf(vistaExamen_cab.dataExamen_cab.getValueAt(filaEditar, 0));
                vistaExamen_cab.txtProceso.setText(String.valueOf(vistaExamen_cab.dataExamen_cab.getValueAt(filaEditar, 1)));
                vistaExamen_cab.txtFecha.setText(String.valueOf(vistaExamen_cab.dataExamen_cab.getValueAt(filaEditar, 2)));
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaExamen_cab.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaExamen_cab.dataExamen_cab.getSelectedRow();
            int numFS = vistaExamen_cab.dataExamen_cab.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaExamen_cab.dataExamen_cab.getValueAt(filaEditar, 0));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar al examen de código: "+code+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloExamen_cab.eliminarExamen_cab(code);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaExamen_cab.dataExamen_cab);
        }
    }
    
    public void vaciarCampos(){
        vistaExamen_cab.txtProceso.setText("");
        vistaExamen_cab.txtFecha.setText("");
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