package entidad;

public class Localidad {
	
	private Provincia provincia;
	private String idlocalidad;
	private String nombre;
	
	//Constructores
	
	public Localidad(Provincia provincia, String idlocalidad, String nombre) {
		super();
		this.provincia = provincia;
		this.idlocalidad = idlocalidad;
		this.nombre = nombre;
	}

	public Localidad() {}

	//Gets y Sets
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getIdlocalidad() {
		return idlocalidad;
	}

	public void setIdlocalidad(String idlocalidad) {
		this.idlocalidad = idlocalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//To String
	@Override
	public String toString() {
		return "Localidad [provincia=" + provincia + ", idlocalidad=" + idlocalidad + ", nombre=" + nombre + "]";
	}	
}
