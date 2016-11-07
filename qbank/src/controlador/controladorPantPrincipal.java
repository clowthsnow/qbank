/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.FormPantPrincipal;

/**
 *
 * @author richard
 */
public class controladorPantPrincipal {
    FormPantPrincipal vistaPantPrincipal = new FormPantPrincipal();
    UsuarioDAO modeloFinal = new UsuarioDAO();
    Usuario usuario = new Usuario();
    String codigo, password, nombre;
    
    public controladorPantPrincipal(FormPantPrincipal vistaPantPrincipal, UsuarioDAO modeloFinal){
        this.vistaPantPrincipal = vistaPantPrincipal;
        this.modeloFinal = modeloFinal;
    }
    
    public void initFinal(String codigo, String password, String nombre){
        this.codigo = codigo;
        this.password = password;
        this.nombre = nombre;
        //vistaPantPrincipal.hola.setText("Hola " + nombre);
    }
    
    
}
