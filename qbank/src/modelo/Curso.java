package modelo;


public class Curso {
	String codigo;
	String nombre;
	String estRegistro;
        
        public Curso(){
            
        }
        
	public Curso(String codigo, String nombre, String estRegistro) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.estRegistro = estRegistro;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstRegistro() {
		return estRegistro;
	}
	public void setEstRegistro(String estRegistro) {
		this.estRegistro = estRegistro;
	}
	
	
	
}
