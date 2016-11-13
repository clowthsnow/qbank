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
public class AreaDAO {
    Conexion conexion;
    
    public AreaDAO(){
        conexion = new Conexion();
    }
    
    public String insertArea(String nombre, String preguntas){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`AREA` (`ArCod`, `ArNom`, `ArCantPreg`, `ArEstReg`) VALUES (NULL, ?, ?, ?);");
            cs.setString(1, nombre);
            cs.setString(2, preguntas);
            cs.setString(3, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarArea(String codigo, String nombre, String preguntas){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`AREA` SET  `ArNom` =  ?,`ArCantPreg` =  ? WHERE  `AREA`.`ArCod` =?;");
            cs.setString(1, nombre);
            cs.setString(2, preguntas);
            cs.setString(3, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarArea(String codigo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`AREA` SET  `ArEstReg` =  'I' WHERE  `AREA`.`ArCod` =?;");
            cs.setString(1, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Eliminación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public ArrayList<Area> listarArea(){
        ArrayList listaArea = new ArrayList();
        Area area;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from AREA");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                area = new Area();
                area.setCodigo(rs.getString(1));
                area.setNombre(rs.getString(2));
                area.setPreguntas(rs.getString(3));
                area.setEstRegistro(rs.getString(4));
                listaArea.add(area);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaArea;
    }
}
