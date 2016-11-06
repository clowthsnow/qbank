
public class Curso {
	private int codigo;
	private String nombre;
	private String estRegistro;
	public Curso(int codigo, String nombre, String estRegistro) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.estRegistro = estRegistro;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
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
