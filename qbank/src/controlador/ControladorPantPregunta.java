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
import modelo.Balota;
import modelo.BalotaDAO;
import modelo.Curso;
import modelo.CursoDAO;
import modelo.Dificultad;
import modelo.DificultadDAO;
import modelo.PreguntaDAO;
import vista.PantallaPregunta;

/**
 *
 * @author richard
 */
public class ControladorPantPregunta implements ActionListener,KeyListener {
    boolean isNew = true;
    String currentCode = null;
    PantallaPregunta vistaPregunta = new PantallaPregunta();
    PreguntaDAO modeloPregunta = new PreguntaDAO();
    
    public ControladorPantPregunta(PantallaPregunta vistaPregunta, PreguntaDAO modeloPregunta){
        this.modeloPregunta = modeloPregunta;
        this.vistaPregunta = vistaPregunta;
        this.vistaPregunta.bttnGuardar.addActionListener(this);
        this.vistaPregunta.bttnNuevo.addActionListener(this);
        this.vistaPregunta.bttnEliminar.addActionListener(this);
        this.vistaPregunta.bttnModificar.addActionListener(this);
    }
    
    public void inicializarUsuarioCRUD(){
        BalotaDAO balota = new BalotaDAO();
        ArrayList<Balota> lista = balota.listarBalota();
        String array[] = new String[lista.size()];
        int cont = 0;
        while(!lista.isEmpty()){
            Balota temp = lista.remove(0);
            array[cont++] = temp.getCodigo();
        }
        vistaPregunta.cboxBalota.setModel(new javax.swing.DefaultComboBoxModel<>(array));
        
        CursoDAO curso = new CursoDAO();
        ArrayList<Curso> lista2 = curso.listarCurso();
        String array2[] = new String[lista2.size()];
        cont = 0;
        while(!lista2.isEmpty()){
            Curso temp = lista2.remove(0);
            array2[cont++] = temp.getCodigo();
        }
        vistaPregunta.cboxCurso.setModel(new javax.swing.DefaultComboBoxModel<>(array2));
        
        DificultadDAO dificultad = new DificultadDAO();
        ArrayList<Dificultad> lista3 = dificultad.listarDificultad();
        String array3[] = new String[lista3.size()];
        cont = 0;
        while(!lista3.isEmpty()){
            Dificultad temp = lista3.remove(0);
            array3[cont++] = temp.getCodigo();
        }
        vistaPregunta.cboxDificultad.setModel(new javax.swing.DefaultComboBoxModel<>(array3));
        llenarTabla(vistaPregunta.DatosPregunta);
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
        modeloT.addColumn("Curso");
        modeloT.addColumn("Balota");
        modeloT.addColumn("Formulador");
        modeloT.addColumn("Dificultad");
        modeloT.addColumn("Fecha");
        modeloT.addColumn("Enunciado");
        modeloT.addColumn("Solución");
        modeloT.addColumn("Alternativa 1");
        modeloT.addColumn("Alternativa 2");
        modeloT.addColumn("Alternativa 3");
        modeloT.addColumn("Alternativa 4");
        modeloT.addColumn("Alternativa 5");
        modeloT.addColumn("Respuesta");
        modeloT.addColumn("Estado");
        
        Object[] fila = new Object[15];
        
        int numRegistros = modeloPregunta.listarPregunta().size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = modeloPregunta.listarPregunta().get(i).getCodigo();
            fila[1] = modeloPregunta.listarPregunta().get(i).getCurso();
            fila[2] = modeloPregunta.listarPregunta().get(i).getBalota();
            fila[3] = modeloPregunta.listarPregunta().get(i).getFormulador();
            fila[4] = modeloPregunta.listarPregunta().get(i).getDificultad();
            fila[5] = modeloPregunta.listarPregunta().get(i).getFecha();
            fila[6] = modeloPregunta.listarPregunta().get(i).getEnunciado();
            fila[7] = modeloPregunta.listarPregunta().get(i).getSolucion();
            fila[8] = modeloPregunta.listarPregunta().get(i).getAlternativa1();
            fila[9] = modeloPregunta.listarPregunta().get(i).getAlternativa2();
            fila[10] = modeloPregunta.listarPregunta().get(i).getAlternativa3();
            fila[11] = modeloPregunta.listarPregunta().get(i).getAlternativa4();
            fila[12] = modeloPregunta.listarPregunta().get(i).getAlternativa5();
            fila[13] = modeloPregunta.listarPregunta().get(i).getRespuesta();
            fila[14] = modeloPregunta.listarPregunta().get(i).getEstRegistro();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaPregunta.bttnGuardar){
            String formulador = vistaPregunta.txtFormulador.getText();
            String fecha = vistaPregunta.txtFecha.getText();
            String enunciado = vistaPregunta.txtEnunciado.getText();
            String solucion = vistaPregunta.txtSolucion.getText();
            String alternativa1 = vistaPregunta.txtAlternativa1.getText();
            String alternativa2 = vistaPregunta.txtAlternativa2.getText();
            String alternativa3 = vistaPregunta.txtAlternativa3.getText();
            String alternativa4 = vistaPregunta.txtAlternativa4.getText();
            String alternativa5 = vistaPregunta.txtAlternativa5.getText();
            String respuesta = vistaPregunta.txtRespuesta.getText();
            if(formulador.equalsIgnoreCase("") || fecha.equalsIgnoreCase("") || enunciado.equalsIgnoreCase("") || solucion.equalsIgnoreCase("") || alternativa1.equalsIgnoreCase("") || alternativa2.equalsIgnoreCase("") || alternativa3.equalsIgnoreCase("") || alternativa4.equalsIgnoreCase("") || alternativa5.equalsIgnoreCase("") || respuesta.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Datos incompletos.");
                return;
            }
            String curso = String.valueOf(vistaPregunta.cboxCurso.getSelectedItem());
            String balota = String.valueOf(vistaPregunta.cboxBalota.getSelectedItem());
            String difucultad = String.valueOf(vistaPregunta.cboxDificultad.getSelectedItem());
            String rptaRegistro = null;
            if(isNew)
                rptaRegistro = modeloPregunta.insertPregunta(curso,balota,difucultad,formulador,fecha,enunciado,solucion,alternativa1,alternativa2,alternativa3,alternativa4,alternativa5,respuesta);
            else
                rptaRegistro = modeloPregunta.modificarPregunta(currentCode,curso,balota,difucultad,formulador,fecha,enunciado,solucion,alternativa1,alternativa2,alternativa3,alternativa4,alternativa5,respuesta);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaPregunta.DatosPregunta);
            vaciarCampos();
            
        }else if(e.getSource() == vistaPregunta.bttnNuevo){
            
            isNew = true;
            vaciarCampos();
            
        }else if(e.getSource() == vistaPregunta.bttnModificar){
            
            isNew = false;
            int filaEditar = vistaPregunta.DatosPregunta.getSelectedRow();
            int numFS = vistaPregunta.DatosPregunta.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                currentCode = String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 0));
                vistaPregunta.cboxCurso.setSelectedItem(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 1)));
                vistaPregunta.cboxBalota.setSelectedItem(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 2)));
                vistaPregunta.cboxDificultad.setSelectedItem(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 3)));
                vistaPregunta.txtFormulador.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 4)));
                vistaPregunta.txtFecha.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 5)));
                vistaPregunta.txtEnunciado.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 6)));
                vistaPregunta.txtSolucion.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 7)));
                vistaPregunta.txtAlternativa1.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 8)));
                vistaPregunta.txtAlternativa2.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 9)));
                vistaPregunta.txtAlternativa3.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 10)));
                vistaPregunta.txtAlternativa4.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 11)));
                vistaPregunta.txtAlternativa5.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 12)));
                vistaPregunta.txtRespuesta.setText(String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 13)));
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            
        }else if(e.getSource() == vistaPregunta.bttnEliminar){
            
            isNew = false;
            int filaEditar = vistaPregunta.DatosPregunta.getSelectedRow();
            int numFS = vistaPregunta.DatosPregunta.getSelectedRowCount();
            if(filaEditar>=0 && numFS == 1){
                String code = String.valueOf(vistaPregunta.DatosPregunta.getValueAt(filaEditar, 0));
                int rptaUsuario = JOptionPane.showConfirmDialog(null,"Está seguro de eliminar al usuario con código: "+code+"?");
                String rptaRegistro = null;
                if(rptaUsuario==0)
                    rptaRegistro = modeloPregunta.eliminarPregunta(code);
                if(rptaRegistro != null){
                    JOptionPane.showMessageDialog(null, rptaRegistro);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            llenarTabla(vistaPregunta.DatosPregunta);
        }
    }
    
    public void vaciarCampos(){
        vistaPregunta.txtFormulador.setText("");
        vistaPregunta.txtFecha.setText("");
        vistaPregunta.txtEnunciado.setText("");
        vistaPregunta.txtSolucion.setText("");
        vistaPregunta.txtAlternativa1.setText("");
        vistaPregunta.txtAlternativa2.setText("");
        vistaPregunta.txtAlternativa3.setText("");
        vistaPregunta.txtAlternativa4.setText("");
        vistaPregunta.txtAlternativa5.setText("");
        vistaPregunta.txtRespuesta.setText("");
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