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
public class Examen_cabDAO {
    Conexion conexion;
    
    public Examen_cabDAO(){
        conexion = new Conexion();
    }
    
    public String insertExamen_cab(String proceso, String fecha){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`EXAMEN_CAB` (`ExaCod`, `ExaProc`,  `ExaFech`, `ExaEstReg`) VALUES (NULL, ?, ?, ?);");
            cs.setString(1, proceso);
            cs.setString(2, fecha);
            cs.setString(3, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarExamen_cab(String codigo, String proceso, String fecha){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`EXAMEN_CAB` SET  `ExaProc` =  ?,`ExaFech` =  ? WHERE  `EXAMEN_CAB`.`ExaCod` = ?;");
            cs.setString(1, proceso);
            cs.setString(2, fecha);
            cs.setString(3, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarExamen_cab(String codigo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`EXAMEN_CAB` SET  `ExaEstReg` =  'I' WHERE  `EXAMEN_CAB`.`ExaCod` =?;");
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
    
    public ArrayList<Examen_cab> listarExamen_cab(){
        ArrayList listaExamen_cab = new ArrayList();
        Examen_cab examen_cab;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from EXAMEN_CAB");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                examen_cab = new Examen_cab();
                examen_cab.setCodigo(rs.getString(1));
                examen_cab.setProceso(rs.getString(2));
                examen_cab.setFecha(rs.getString(3));
                examen_cab.setEstRegistro(rs.getString(4));
                listaExamen_cab.add(examen_cab);
            }
            accesoDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaExamen_cab;
    }
}
