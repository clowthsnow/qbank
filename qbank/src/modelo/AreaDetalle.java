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
public class AreaDetalle {
    String codigo;
    String curso;
    String cantidad;
    String estRegistro;
    
    public AreaDetalle(){
        
    }
    
    public AreaDetalle(String codigo, String curso, String cantidad, String estRegistro){
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.curso = curso;
        this.estRegistro = estRegistro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstRegistro() {
        return estRegistro;
    }

    public void setEstRegistro(String estRegistro) {
        this.estRegistro = estRegistro;
    }
    
    
}
