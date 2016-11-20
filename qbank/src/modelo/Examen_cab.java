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
public class Examen_cab {
    String codigo;
    String proceso;
    String fecha;
    String estRegistro;
    
    public Examen_cab(){
        
    }

    public Examen_cab(String codigo, String proceso, String fecha, String estRegistro) {
        this.codigo = codigo;
        this.proceso = proceso;
        this.fecha = fecha;
        this.estRegistro = estRegistro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstRegistro() {
        return estRegistro;
    }

    public void setEstRegistro(String estRegistro) {
        this.estRegistro = estRegistro;
    }
    
    
}
