/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qbank;

import controlador.controladorLogin;
import modelo.UsuarioDAO;
import vista.FormLogin;

/**
 *
 * @author richard
 */
public class Qbank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FormLogin vistaL = new FormLogin();
        UsuarioDAO modeloL = new UsuarioDAO();
        controladorLogin controladorL = new controladorLogin(vistaL, modeloL);
        vistaL.setVisible(true);
        vistaL.setLocationRelativeTo(null);
    }
    
}
