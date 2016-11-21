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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Examen_cab;
import modelo.Examen_det;
import modelo.Examen_detDAO;
import modelo.Pregunta;
import modelo.PreguntaDAO;
import vista.PantallaListaPreguntas;

/**
 *
 * @author richard
 */
public class ControladorPantExamen_det implements ActionListener,KeyListener {
    boolean isNew = true;
    String currentCode = null;
    String currentCourse = null;
    PantallaListaPreguntas vistaExamen_det = new PantallaListaPreguntas();
    Examen_detDAO modeloExamen_det = new Examen_detDAO();
    JDesktopPane Escritorio;
    String exam = null;
    
    public ControladorPantExamen_det(PantallaListaPreguntas vistaExamen_det, Examen_detDAO modeloExamen_det, JDesktopPane Escritorio, String exam){
        this.modeloExamen_det = modeloExamen_det;
        this.vistaExamen_det = vistaExamen_det;
        this.vistaExamen_det.bttnBio.addActionListener(this);
        this.vistaExamen_det.bttnIng.addActionListener(this);
        this.vistaExamen_det.bttnSoc.addActionListener(this);
        this.vistaExamen_det.checkBio.addActionListener(this);
        this.vistaExamen_det.checkIng.addActionListener(this);
        this.vistaExamen_det.checkSoc.addActionListener(this);
        this.Escritorio = Escritorio;
        this.exam = exam;
    }
    
    public void inicializarExamen_cabCRUD(){
        llenarTabla(vistaExamen_det.dataPreguntas);
        vistaExamen_det.checkBio.setEnabled(true);
        vistaExamen_det.checkIng.setEnabled(true);
        vistaExamen_det.checkSoc.setEnabled(true);
        vistaExamen_det.lblTitulo.setText(exam);
        vistaExamen_det.dataPreguntas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                Examen_detDAO det = new Examen_detDAO();
                int filaEditar = vistaExamen_det.dataPreguntas.getSelectedRow();
                currentCode = String.valueOf(vistaExamen_det.dataPreguntas.getValueAt(filaEditar, 0));
                ArrayList<Examen_det> dets = det.getArea(exam, currentCode);
                int numRegistros = dets.size();
                vistaExamen_det.checkBio.setSelected(false);
                vistaExamen_det.checkIng.setSelected(false);
                vistaExamen_det.checkSoc.setSelected(false);
                for (int i = 0; i < numRegistros; i++){
                    switch(dets.get(i).getCodigo()){
                        case "1":
                            vistaExamen_det.checkIng.setSelected(true);
                            break;
                        case "2":
                            vistaExamen_det.checkBio.setSelected(true);
                            break;
                        case "3":
                            vistaExamen_det.checkSoc.setSelected(true);
                            break;
                    }
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
        modeloT.addColumn("Curso");
        modeloT.addColumn("Balota");
        modeloT.addColumn("Dificultad");
        modeloT.addColumn("Enunciado");
        modeloT.addColumn("Solución");
        
        Object[] fila = new Object[6];
        PreguntaDAO pregunta = new PreguntaDAO();
        ArrayList<Pregunta> preguntas = pregunta.listarPregunta();
        int numRegistros = preguntas.size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = preguntas.get(i).getCodigo();
            fila[1] = preguntas.get(i).getCurso();
            fila[2] = preguntas.get(i).getBalota();
            fila[3] = preguntas.get(i).getDificultad();
            fila[4] = preguntas.get(i).getEnunciado();
            fila[5] = preguntas.get(i).getSolucion();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void llenarTabla(JTable tablaD, String examen, String area){
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
        modeloT.addColumn("Dificultad");
        modeloT.addColumn("Enunciado");
        modeloT.addColumn("Solución");
        
        Object[] fila = new Object[6];
        PreguntaDAO pregunta = new PreguntaDAO();
        Examen_detDAO det = new Examen_detDAO();
        ArrayList<Examen_det> dets = det.listarExamen_det(examen, area);
        int numRegistros = dets.size();
        for (int i = 0; i < numRegistros; i++) {
            fila[0] = pregunta.getName(dets.get(i).getPregunta()).getCodigo();
            fila[1] = pregunta.getName(dets.get(i).getPregunta()).getCurso();
            fila[2] = pregunta.getName(dets.get(i).getPregunta()).getBalota();
            fila[3] = pregunta.getName(dets.get(i).getPregunta()).getDificultad();
            fila[4] = pregunta.getName(dets.get(i).getPregunta()).getEnunciado();
            fila[5] = pregunta.getName(dets.get(i).getPregunta()).getSolucion();
            modeloT.addRow((Object[]) fila);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaExamen_det.checkBio || e.getSource() == vistaExamen_det.checkIng || e.getSource() == vistaExamen_det.checkSoc){
            int filaEditar = vistaExamen_det.dataPreguntas.getSelectedRow();
            int numFS = vistaExamen_det.dataPreguntas.getSelectedRowCount();
            String rptaRegistro = null;
            if(filaEditar>=0 && numFS == 1){
                JCheckBox temp = (JCheckBox)e.getSource();
                String cont = null;
                String area = temp.getText();
                switch(area){
                    case "Ingenierias":
                        cont = "1";
                        break;
                    case "Biomedicas":
                        cont = "2";
                        break;
                    case "Sociales":
                        cont = "3";
                        break;
                }
                if(temp.isSelected()){
                    rptaRegistro = modeloExamen_det.insertExamen_det(exam, currentCode, cont);
                }else{
                    rptaRegistro = modeloExamen_det.eliminarExamen_det(exam, currentCode, cont);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Selección no válida.");
            }
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro erróneo");
            }
            llenarTabla(vistaExamen_det.dataPreguntas);
            vaciarCampos();
            
        }
    }
    
    public void vaciarCampos(){
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