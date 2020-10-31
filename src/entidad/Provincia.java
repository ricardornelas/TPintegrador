package entidad;

public class Provincia {
	
	private String idprovincia;
	private String nombre;
	
	public Provincia(){}

	public Provincia(String idprovincia, String nombre) {
		super();
		this.idprovincia = idprovincia;
		this.nombre = nombre;
	}

	public String getIdprovincia() {
		return idprovincia;
	}

	public void setIdprovincia(String idprovincia) {
		this.idprovincia = idprovincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Provincia [idprovincia=" + idprovincia + ", nombre=" + nombre + "]";
	}
}
