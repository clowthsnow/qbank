/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class UsuarioDAO {
    Conexion conexion;
    
    public UsuarioDAO(){
        conexion = new Conexion();
    }
    
    public Usuario verificarUsuario(String codigo, String password){
        Usuario usuario = null;
        Connection acceso = conexion.getConexion();
        try{
            PreparedStatement ps = acceso.prepareStatement("select * from USUARIO where UsuCod=? and UsuContra=?");
            ps.setString(1, codigo);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario = new Usuario();
                usuario.setCodigo(rs.getString(1));
                usuario.setNombre(rs.getString(2));
                usuario.setContraseña(rs.getString(3));
                usuario.setTipo(rs.getString(4));
                usuario.setEstRegistro(rs.getString(5));
                return usuario;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    
    public String insertUsuario(String nombre, String contraseña, String tipo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO `qbank`.`USUARIO` (`UsuCod`, `UsuNom`, `UsuContra`, `UsuTip`, `UsuEstReg`) VALUES (NULL, ?, ?, ?, ?);");
            cs.setString(1, nombre);
            cs.setString(2, contraseña);
            cs.setString(3, tipo);
            cs.setString(4, "A");
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Registro exitoso.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String modificarUsuario(String codigo, String nombre, String contraseña, String tipo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`USUARIO` SET  `UsuNom` = ?,`UsuContra` =  ?,`UsuTip` =  ?,`UsuEstReg` =  ? WHERE  `USUARIO`.`UsuCod` =?");
            cs.setString(1, nombre);
            cs.setString(2, contraseña);
            cs.setString(3, tipo);
            cs.setString(4, "A");
            cs.setString(5, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Modificación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public String eliminarUsuario(String codigo){
        String rptaRegistro = null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("UPDATE  `qbank`.`USUARIO` SET  `UsuEstReg` =  'I' WHERE  `USUARIO`.`UsuCod` =?");
            cs.setString(1, codigo);
            
            int numFAfectadas = cs.executeUpdate();
            if(numFAfectadas > 0)
                rptaRegistro = "Eliminación exitosa.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return rptaRegistro;
    }
    
    public ArrayList<Usuario> listarUsuario(){
        ArrayList listaUsuario = new ArrayList();
        Usuario usuario;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("Select * from USUARIO");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setCodigo(rs.getString(1));
                usuario.setNombre(rs.getString(2));
                usuario.setContraseña(rs.getString(3));
                usuario.setTipo(rs.getString(4));
                usuario.setEstRegistro(rs.getString(5));
                listaUsuario.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuario;
    }
}
