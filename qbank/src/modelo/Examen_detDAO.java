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
public class Examen_detDAO {
    Conexion conexion;
    
    public Examen_detDAO(){
        conexion = new Conexion();
    }
    
    public String insertExamen_det(String examen, String pregunta, String area){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`EXAMEN_DET` (`DetCod`, `ExaCod`, `ExaPreg`, `ExaArea`, `ExaEstReg`) VALUES (NULL, ?, ?, ?, 'A');");
            cs.setString(1, examen);
            cs.setString(2, pregunta);
            cs.setString(3, area);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarExamen_det(String examen, String pregunta, String area){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("DELETE FROM `qbank`.`EXAMEN_DET` WHERE `EXAMEN_DET`.`ExaCod` = ? AND `EXAMEN_DET`.`ExaPreg` = ? AND `EXAMEN_DET`.`ExaArea` = ?");
            cs.setString(1, examen);
            cs.setString(2, pregunta);
            cs.setString(3, area);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Eliminación exitosa.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public ArrayList<Examen_det> listarExamen_det(String examen, String area){
        ArrayList listaExamen_det = new ArrayList();
        Examen_det examen_det;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM `EXAMEN_DET` WHERE `ExaCod`="+examen+" AND `ExaArea` = "+area+";");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                examen_det = new Examen_det();
                examen_det.setCodigo(rs.getString(1));
                examen_det.setExamen(rs.getString(2));
                examen_det.setPregunta(rs.getString(3));
                examen_det.setArea(rs.getString(4));
                examen_det.setEstRegistro(rs.getString(5));
                listaExamen_det.add(examen_det);
            }
            accesoDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaExamen_det;
    }
    
    public ArrayList<Examen_det> getArea(String examen, String pregunta){
        ArrayList listaExamen_det = new ArrayList();
        Examen_det examen_det;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM `EXAMEN_DET` WHERE `ExaCod`="+examen+" AND `ExaPreg` = "+pregunta+";");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                examen_det = new Examen_det();
                examen_det.setCodigo(rs.getString(1));
                examen_det.setExamen(rs.getString(2));
                examen_det.setPregunta(rs.getString(3));
                examen_det.setArea(rs.getString(4));
                examen_det.setEstRegistro(rs.getString(5));
                listaExamen_det.add(examen_det);
            }
            accesoDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaExamen_det;
    }
}
