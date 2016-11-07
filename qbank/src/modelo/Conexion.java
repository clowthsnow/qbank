/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author richard
 */
public class Conexion {
    public Conexion(){
        
    }
    
    public Connection getConexion(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/qbank","root","qwerty");
        }catch(Exception e){
            System.err.println(e);
        }
        return con;
    }
}
