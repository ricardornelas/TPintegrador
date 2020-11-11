package Entidad;

public class Usuario {

	private String NombreU;
	private String Contraseña;
	private Boolean EsAdmin;
	private Boolean Estado;
	
	public Usuario() {
		
	}
	
	public String getNombreU() {
		return NombreU;
	}
	public void setNombreU(String nombreU) {
		NombreU = nombreU;
	}
	public String getContraseña() {
		return Contraseña;
	}
	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	public Boolean getEsAdmin() {
		return EsAdmin;
	}

	public void setEsAdmin(Boolean esAdmin) {
		EsAdmin = esAdmin;
	}

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	
	
	
	
}
