/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.JFfinal;

/**
 *
 * @author richard
 */
public class controladorFinal {
    JFfinal vistaFinal = new JFfinal();
    UsuarioDAO modeloFinal = new UsuarioDAO();
    Usuario usuario = new Usuario();
    String codigo, password, nombre;
    
    public controladorFinal(JFfinal vistaFinal, UsuarioDAO modeloFinal){
        this.vistaFinal = vistaFinal;
        this.modeloFinal = modeloFinal;
    }
    
    public void initFinal(String codigo, String password, String nombre){
        this.codigo = codigo;
        this.password = password;
        this.nombre = nombre;
        vistaFinal.hola.setText("Hola " + nombre);
    }
    
    
}
