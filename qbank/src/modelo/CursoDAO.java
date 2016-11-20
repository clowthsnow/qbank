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
public class CursoDAO {
    Conexion conexion;
    
    public CursoDAO(){
        conexion = new Conexion();
    }
    
    public String insertCurso(String nombre){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`CURSO` (`CuCod`, `CuNom`, `CuEstReg`) VALUES (NULL, ?, ?);");
            cs.setString(1, nombre);
            cs.setString(2, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarCurso(String codigo, String nombre){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`CURSO` SET  `CuNom` =  ? WHERE  `CURSO`.`CuCod` =?;");
            cs.setString(1, nombre);
            cs.setString(2, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarCurso(String codigo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`CURSO` SET  `CuEstReg` =  'I' WHERE  `CURSO`.`CuCod` =?;");
            cs.setString(1, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Eliminación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public ArrayList<Curso> listarCurso(){
        ArrayList listaCurso = new ArrayList();
        Curso curso;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from CURSO");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                curso = new Curso();
                curso.setCodigo(rs.getString(1));
                curso.setNombre(rs.getString(2));
                curso.setEstRegistro(rs.getString(3));
                listaCurso.add(curso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCurso;
    }
    
    public Curso getName(String code){
        ArrayList listaCurso = new ArrayList();
        Curso curso;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM  `CURSO` WHERE CuCod ="+code);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                curso = new Curso();
                curso.setCodigo(rs.getString(1));
                curso.setNombre(rs.getString(2));
                curso.setEstRegistro(rs.getString(3));
                listaCurso.add(curso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Curso)listaCurso.get(0);
    }
}
