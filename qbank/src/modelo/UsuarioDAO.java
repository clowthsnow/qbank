/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;

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
}
