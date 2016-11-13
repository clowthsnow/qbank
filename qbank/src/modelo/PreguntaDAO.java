/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class PreguntaDAO {
    Conexion conexion;
    
    public PreguntaDAO(){
        conexion = new Conexion();
    }
    public String insertPregunta(String curso, String balota, String dificultad, String formulador, String fecha, String enunciado, String solucion, String alt1, String alt2, String alt3, String alt4, String alt5, String respuesta){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`PREGUNTA` (`PregCod`, `PregCurs`, `PregBal`, `PregForm`, `PregDif`, `PregFecha`, `PregEnum`, `PregSol`, `PregAltUno`, `PregAltDos`, `PregAltTres`, `PregAltCuat`, `PregAltCinc`, `PregRespt`, `PregEstReg`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            cs.setString(1, curso);
            cs.setString(2, balota);
            cs.setString(3, formulador);
            cs.setString(4, dificultad);
            cs.setString(5, fecha);
            cs.setString(6, enunciado);
            cs.setString(7, solucion);
            cs.setString(8, alt1);
            cs.setString(9, alt2);
            cs.setString(10, alt3);
            cs.setString(11, alt4);
            cs.setString(12, alt5);
            cs.setString(13, respuesta);
            cs.setString(14, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarPregunta(String codigo, String curso, String balota, String dificultad, String formulador, String fecha, String enunciado, String solucion, String alt1, String alt2, String alt3, String alt4, String alt5, String respuesta){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE `qbank`.`PREGUNTA` SET `PregCurs` = ?, `PregBal` = ?, `PregForm` = ?, `PregDif` = ?, `PregFecha` = ?, `PregEnum` = ?, `PregSol` = ?, `PregAltUno` = ?, `PregAltDos` = ?, `PregAltTres` = ?, `PregAltCuat` = ?, `PregAltCinc` = ?, `PregRespt` = ? WHERE `PREGUNTA`.`PregCod` = ?");
            cs.setString(1, curso);
            cs.setString(2, balota);
            cs.setString(3, formulador);
            cs.setString(4, dificultad);
            cs.setString(5, fecha);
            cs.setString(6, enunciado);
            cs.setString(7, solucion);
            cs.setString(8, alt1);
            cs.setString(9, alt2);
            cs.setString(10, alt3);
            cs.setString(11, alt4);
            cs.setString(12, alt5);
            cs.setString(13, respuesta);
            cs.setString(14, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarPregunta(String codigo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`PREGUNTA` SET  `PregEstReg` =  'I' WHERE  `PREGUNTA`.`PregCod` =?;");
            cs.setString(1, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Eliminación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public ArrayList<Pregunta> listarPregunta(){
        ArrayList listaPregunta = new ArrayList();
        Pregunta pregunta;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from PREGUNTA");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pregunta = new Pregunta();
                pregunta.setCodigo(rs.getString(1));
                pregunta.setCurso(rs.getString(2));
                pregunta.setBalota(rs.getString(3));
                pregunta.setFormulador(rs.getString(4));
                pregunta.setDificultad(rs.getString(5));
                pregunta.setFecha(rs.getString(6));
                pregunta.setEnunciado(rs.getString(7));
                pregunta.setSolucion(rs.getString(8));
                pregunta.setAlternativa1(rs.getString(9));
                pregunta.setAlternativa2(rs.getString(10));
                pregunta.setAlternativa3(rs.getString(11));
                pregunta.setAlternativa4(rs.getString(12));
                pregunta.setAlternativa5(rs.getString(13));
                pregunta.setRespuesta(rs.getString(14));
                pregunta.setEstRegistro(rs.getString(15));
                listaPregunta.add(pregunta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPregunta;
    }
}
