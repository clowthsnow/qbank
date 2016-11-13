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
public class Pregunta {
    String codigo;
    String curso;
    String balota;
    String dificultad;
    String formulador;
    String fecha;
    String enunciado;
    String solucion;
    String alternativa1;
    String alternativa2;
    String alternativa3;
    String alternativa4;
    String alternativa5;
    String respuesta;
    String estRegistro;
    
    public Pregunta(){
        
    }

    public Pregunta(String codigo, String curso, String balota, String dificultad, String formulador, String fecha, String enunciado, String solucion, String alternativa1, String alternativa2, String alternativa3, String alternativa4, String alternativa5, String respuesta, String estRegistro) {
        this.codigo = codigo;
        this.curso = curso;
        this.balota = balota;
        this.dificultad = dificultad;
        this.formulador = formulador;
        this.fecha = fecha;
        this.enunciado = enunciado;
        this.solucion = solucion;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
        this.alternativa3 = alternativa3;
        this.alternativa4 = alternativa4;
        this.alternativa5 = alternativa5;
        this.respuesta = respuesta;
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

    public String getBalota() {
        return balota;
    }

    public void setBalota(String balota) {
        this.balota = balota;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getFormulador() {
        return formulador;
    }

    public void setFormulador(String formulador) {
        this.formulador = formulador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(String alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public String getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(String alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public String getAlternativa3() {
        return alternativa3;
    }

    public void setAlternativa3(String alternativa3) {
        this.alternativa3 = alternativa3;
    }

    public String getAlternativa4() {
        return alternativa4;
    }

    public void setAlternativa4(String alternativa4) {
        this.alternativa4 = alternativa4;
    }

    public String getAlternativa5() {
        return alternativa5;
    }

    public void setAlternativa5(String alternativa5) {
        this.alternativa5 = alternativa5;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getEstRegistro() {
        return estRegistro;
    }

    public void setEstRegistro(String estRegistro) {
        this.estRegistro = estRegistro;
    }
    
    
}
