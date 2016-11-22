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
public class BalotaDAO {
    Conexion conexion;
    
    public BalotaDAO(){
        conexion = new Conexion();
    }
    
    public String insertBalota(String nombre, String descripcion, String curso){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`BALOTA` (`BalCod`, `BalNom`, `BalDesc`, `BalCurs`, `BalEstReg`) VALUES (NULL, ?, ?, ?, ?);");
            cs.setString(1, nombre);
            cs.setString(2, descripcion);
            cs.setString(3, curso);
            cs.setString(4, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarBalota(String codigo, String nombre, String descripcion, String curso){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`BALOTA` SET  `BalNom` =  ?,`BalDesc` =  ?,`BalCurs` =  ? WHERE  `BALOTA`.`BalCod` =?;");
            cs.setString(1, nombre);
            cs.setString(2, descripcion);
            cs.setString(3, curso);
            cs.setString(5, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
            accesoDB.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarBalota(String codigo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`BALOTA` SET  `BalEstReg` =  'I' WHERE  `BALOTA`.`BalCod` =?;");
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
    
    public ArrayList<Balota> listarBalota(){
        ArrayList listaBalota = new ArrayList();
        Balota balota;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from BALOTA");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                balota = new Balota();
                balota.setCodigo(rs.getString(1));
                balota.setNombre(rs.getString(2));
                balota.setDescripcion(rs.getString(3));
                balota.setCurso(rs.getString(4));
                balota.setEstRegistro(rs.getString(5));
                listaBalota.add(balota);
            }
            accesoDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaBalota;
    }
    
    public Balota getName(String cod){
        ArrayList listaBalota = new ArrayList();
        Balota balota;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from BALOTA where BalCod = "+cod);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                balota = new Balota();
                balota.setCodigo(rs.getString(1));
                balota.setNombre(rs.getString(2));
                balota.setDescripcion(rs.getString(3));
                balota.setCurso(rs.getString(4));
                balota.setEstRegistro(rs.getString(5));
                listaBalota.add(balota);
            }
            accesoDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Balota)listaBalota.get(0);
    }
}
