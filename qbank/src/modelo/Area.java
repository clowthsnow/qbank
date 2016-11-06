
public class Area {
	private int codigo;
	private String Nombre;
	private String CantidadPreg;
	private String EstRegistro;
	public Area(int codigo, String nombre, String cantidadPreg,
			String estRegistro) {
		super();
		this.codigo = codigo;
		Nombre = nombre;
		CantidadPreg = cantidadPreg;
		EstRegistro = estRegistro;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getCantidadPreg() {
		return CantidadPreg;
	}
	public void setCantidadPreg(String cantidadPreg) {
		CantidadPreg = cantidadPreg;
	}
	public String getEstRegistro() {
		return EstRegistro;
	}
	public void setEstRegistro(String estRegistro) {
		EstRegistro = estRegistro;
	}
}
