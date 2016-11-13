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
public class UserTipoDAO {
    Conexion conexion;
    
    public UserTipoDAO(){
        conexion = new Conexion();
    }
    
    public String insertUserTipo(String codigo, String descripcion){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`USERTIPO` (`UsuTipCod`, `UsuTipDesc`, `UsuTipEstReg`) VALUES (?,?,?);");
            cs.setString(1, codigo);
            cs.setString(2, descripcion);
            cs.setString(3, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarUserTipo(String codigo,String oldCode, String descripcion){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`USERTIPO` SET  `UsuTipCod` =  ?,`UsuTipDesc` =  ? WHERE  `USERTIPO`.`UsuTipCod` =?;");
            cs.setString(1, codigo);
            cs.setString(2, descripcion);
            cs.setString(3, oldCode);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarUserTipo(String codigo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`USERTIPO` SET  `UsuTipEstReg` =  'I' WHERE  `USERTIPO`.`UsuCod` =?");
            cs.setString(1, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Eliminación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public ArrayList<UserTipo> listarUserTipo(){
        ArrayList listaUsuarioTipo = new ArrayList();
        UserTipo usuarioTipo;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from USERTIPO");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usuarioTipo = new UserTipo();
                usuarioTipo.setCodigo(rs.getString(1));
                usuarioTipo.setDescripcion(rs.getString(2));
                usuarioTipo.setEstRegistro(rs.getString(3));
                listaUsuarioTipo.add(usuarioTipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarioTipo;
    }
}
