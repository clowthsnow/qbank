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
public class AreaDetalleDAO {
    Conexion conexion;
    
    public AreaDetalleDAO(){
        conexion = new Conexion();
    }
    
    public String insertAreaDetalle(String area, String curso, String cantidad){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`AREA_DET` (`ArCod`, `ArCursos`, `ArCursCant`, `ArEstReg`) VALUES (?, ?, ?, ?);");
            cs.setString(1, area);
            cs.setString(2, curso);
            cs.setString(3, cantidad);
            cs.setString(4, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarAreaDetalle(String area, String curso, String cantidad, String oldArea, String oldCurso){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`AREA_DET` SET  `ArCod` =  ?,`ArCursos` =  ?,`ArCursCant` =  ? WHERE  `AREA_DET`.`ArCod` =? AND  `AREA_DET`.`ArCursos` =?;");
            cs.setString(1, area);
            cs.setString(2, curso);
            cs.setString(3, cantidad);
            cs.setString(4, oldArea);
            cs.setString(5, oldCurso);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarAreaDetalle(String area, String curso){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`AREA_DET` SET  `ArEstReg` =  'I' WHERE  `AREA_DET`.`ArCod` =? AND  `AREA_DET`.`ArCursos` =?;");
            cs.setString(1, area);
            cs.setString(2, curso);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Eliminación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public ArrayList<AreaDetalle> listarAreaDetalle(){
        ArrayList listaAreaDetalle = new ArrayList();
        AreaDetalle areaDetalle;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from AREA_DET");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                areaDetalle = new AreaDetalle();
                areaDetalle.setCodigo(rs.getString(1));
                areaDetalle.setCurso(rs.getString(2));
                areaDetalle.setCantidad(rs.getString(3));
                listaAreaDetalle.add(areaDetalle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaAreaDetalle;
    }
}
