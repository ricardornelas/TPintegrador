package Entidad;

public class Provincia {
	
	private int idprovincia;
	private String nombre;
	
	public Provincia(){}


	public Provincia(int id, String n) {
		super();
		idprovincia = id;
		nombre = n;
	}


	public int getIdprovincia() {
		return idprovincia;
	}

	public void setIdprovincia(int idprovincia) {
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
