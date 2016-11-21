/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.*;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.FormLogin;
import javax.swing.*;
import vista.FormPantPrincipal;

/**
 *
 * @author richard
 */
public class ControladorLogin implements ActionListener{
    FormLogin vistaLogin = new FormLogin();
    UsuarioDAO modeloLogin = new UsuarioDAO();
    Usuario usuario = new Usuario();
    
    public ControladorLogin(FormLogin vistaLogin, UsuarioDAO modeloLogin){
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
            FormPantPrincipal vistaPantPrincipal = new FormPantPrincipal();
            ControladorPantPrincipal controlador = new ControladorPantPrincipal(vistaPantPrincipal, modeloLogin);
            controlador.initFinal(codigo, password, usuario.getNombre());
            switch(Integer.valueOf(usuario.getTipo())){
                case 1:
                    vistaPantPrincipal.Formulador.setEnabled(false);
                    vistaPantPrincipal.Digitador.setEnabled(false);
                break;
                case 2:
                    vistaPantPrincipal.Administrador.setEnabled(false);
                    vistaPantPrincipal.Digitador.setEnabled(false); 
                    vistaPantPrincipal.MantenimientoSecundario.setEnabled(false);
                break;
                case 3:
                    vistaPantPrincipal.Administrador.setEnabled(false);
                    vistaPantPrincipal.Formulador.setEnabled(false); 
                    vistaPantPrincipal.MantenimientoSecundario.setEnabled(false);
                break;
            }
            vistaPantPrincipal.setVisible(true);
            vistaPantPrincipal.setLocationRelativeTo(null);
            vistaLogin.setVisible(false);
        }
    }
}
