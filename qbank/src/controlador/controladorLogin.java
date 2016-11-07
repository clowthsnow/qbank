/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.*;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.JFlogin;
import javax.swing.*;
import vista.JFfinal;

/**
 *
 * @author richard
 */
public class controladorLogin implements ActionListener{
    JFlogin vistaLogin = new JFlogin();
    UsuarioDAO modeloLogin = new UsuarioDAO();
    Usuario usuario = new Usuario();
    
    public controladorLogin(JFlogin vistaLogin, UsuarioDAO modeloLogin){
        this.vistaLogin = vistaLogin;
        this.modeloLogin = modeloLogin;
        this.vistaLogin.ButtonIngresar.addActionListener(this);
    }
    
    public void initLogin(){
        
    }
    
    public void actionPerformed(ActionEvent e){
        String codigo = vistaLogin.FieldCodigo.getText();
        String password = String.valueOf(vistaLogin.FieldPassword.getPassword());
        usuario = modeloLogin.verificarUsuario(codigo, password);
        if(usuario==null){
            JOptionPane.showMessageDialog(null, "Empleado con datos ingresados no encontrado.");
        }else{
            JOptionPane.showMessageDialog(null, "Datos correctos.");
            JFfinal vistaF = new JFfinal();
            controladorFinal controlador = new controladorFinal(vistaF, modeloLogin);
            controlador.initFinal(codigo, password, usuario.getNombre());
            vistaF.setVisible(true);
            vistaF.setLocationRelativeTo(null);
            vistaLogin.setVisible(false);
        }
    }
}
