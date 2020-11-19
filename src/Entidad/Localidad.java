package Entidad;

public class Localidad {
	
	int IdProvLoc;
	int IdLocalidad;
	String Nombre;
	
	public Localidad(){
		
	}
	

	public Localidad(int id,int id2, String n) {
		super();
		IdProvLoc = id;
		IdLocalidad = id2;
		Nombre = n;
	}
	
	public int getIdProvLoc() {
		return IdProvLoc;
	}

	public void setIdProvLoc(int idProvLoc) {
		IdProvLoc = idProvLoc;
	}

	public int getIdLocalidad() {
		return IdLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		IdLocalidad = idLocalidad;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}



}
