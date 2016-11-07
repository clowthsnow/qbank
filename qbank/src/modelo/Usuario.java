package modelo;

public class Usuario {
        private String codigo;
	private String nombre;
	private String contraseña;
	private String tipo;
	private String EstRegistro;
	public Usuario() {
		this.codigo = "";
		this.nombre = "";
		this.contraseña = "";
		this.tipo = "";
		EstRegistro = "";
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
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstRegistro() {
		return EstRegistro;
	}
	public void setEstRegistro(String estRegistro) {
		EstRegistro = estRegistro;
	}
	
}
