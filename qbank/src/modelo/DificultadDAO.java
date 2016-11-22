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
public class DificultadDAO {
    Conexion conexion;
    
    public DificultadDAO(){
        conexion = new Conexion();
    }
    
    public String insertDificultad(String descripcion){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`DIFICULTAD_TIPO` (`DifTipCod`, `DifTipDesc`, `DifTipEstReg`) VALUES (NULL, ?, ?);");
            cs.setString(1, descripcion);
            cs.setString(2, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarDificultad(String codigo, String descripcion){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`DIFICULTAD_TIPO` SET  `DifTipDesc` =  ? WHERE  `DIFICULTAD_TIPO`.`DifTipCod` =?;");
            cs.setString(1, descripcion);
            cs.setString(2, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarDificultad(String codigo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`DIFICULTAD_TIPO` SET  `DifTipEstReg` =  'I' WHERE  `DIFICULTAD_TIPO`.`DifTipCod` =?;");
            cs.setString(1, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Eliminación exitosa.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public ArrayList<Dificultad> listarDificultad(){
        ArrayList listaDificultad = new ArrayList();
        Dificultad dificultad;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from DIFICULTAD_TIPO");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dificultad = new Dificultad();
                dificultad.setCodigo(rs.getString(1));
                dificultad.setDescripcion(rs.getString(2));
                dificultad.setEstRegistro(rs.getString(3));
                listaDificultad.add(dificultad);
            }
            accesoDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDificultad;
    }
    
    public Dificultad getName(String cod){
        ArrayList listaDificultad = new ArrayList();
        Dificultad dificultad;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from DIFICULTAD_TIPO where DifTipCod = "+cod);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dificultad = new Dificultad();
                dificultad.setCodigo(rs.getString(1));
                dificultad.setDescripcion(rs.getString(2));
                dificultad.setEstRegistro(rs.getString(3));
                listaDificultad.add(dificultad);
            }
            accesoDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Dificultad)listaDificultad.get(0);
    }
}
