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
public class Examen_det {
    String codigo;
    String examen;
    String pregunta;
    String area;
    String estRegistro;

    public Examen_det() {
    }
    
    public Examen_det(String codigo, String examen, String pregunta, String area, String estRegistro) {
        this.codigo = codigo;
        this.examen = examen;
        this.pregunta = pregunta;
        this.area = area;
        this.estRegistro = estRegistro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEstRegistro() {
        return estRegistro;
    }

    public void setEstRegistro(String estRegistro) {
        this.estRegistro = estRegistro;
    }
}
