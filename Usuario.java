
public class Usuario {
	private int codigo;
	private String nombre;
	private String contrase�a;
	private String tipo;
	private String EstRegistro;
	public Usuario(int codigo, String nombre, String contrase�a, String tipo,
			String estRegistro) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.contrase�a = contrase�a;
		this.tipo = tipo;
		EstRegistro = estRegistro;
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
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
