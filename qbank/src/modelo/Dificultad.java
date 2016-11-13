/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author richard
 */
public class Dificultad {
    String codigo;
    String descripcion;
    String estRegistro;
    
    public Dificultad(){
        
    }
    
    public Dificultad(String codigo, String descripcion, String estRegistro){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estRegistro = estRegistro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstRegistro() {
        return estRegistro;
    }

    public void setEstRegistro(String estRegistro) {
        this.estRegistro = estRegistro;
    }
    
    
}
