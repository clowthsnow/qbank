package modelo;


public class Area {
	private String codigo;
	private String Nombre;
	private String Preguntas;
	private String EstRegistro;
	public Area(String codigo, String nombre, String cantidadPreg,
			String estRegistro) {
		this.codigo = codigo;
		Nombre = nombre;
		Preguntas = cantidadPreg;
		EstRegistro = estRegistro;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getPreguntas() {
		return Preguntas;
	}
	public void setPreguntas(String cantidadPreg) {
		Preguntas = cantidadPreg;
	}
	public String getEstRegistro() {
		return EstRegistro;
	}
	public void setEstRegistro(String estRegistro) {
		EstRegistro = estRegistro;
	}
}
