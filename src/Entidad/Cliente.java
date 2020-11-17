
package Entidad;

public class Cliente {

	private String Cuil;
	private String Dni;
	private String Nombre;
	private String Apellido;
	private int Sexo;
	private String Nacionalidad;
	private String Fecha;
	private String Direccion;
	private int Localidad;
	private int Provincia;
	private String Correo;
	private String Usuario;
	private String Telefono;
	
public Cliente() {
		
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCuil() {
		return Cuil;
	}
	public void setCuil(String cuil) {
		Cuil = cuil;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public int getSexo() {
		return Sexo;
	}
	public void setSexo(int sexo) {
		Sexo = sexo;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public int getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(int localidad) {
		Localidad = localidad;
	}
	public int getProvincia() {
		return Provincia;
	}
	public void setProvincia(int provincia) {
		Provincia = provincia;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	@Override
	public String toString() {
		return "Cliente [Cuil=" + Cuil + ", Dni=" + Dni + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Sexo="
				+ Sexo + ", Nacionalidad=" + Nacionalidad + ", Fecha=" + Fecha + ", Direccion=" + Direccion
				+ ", Localidad=" + Localidad + ", Provincia=" + Provincia + ", Correo=" + Correo + ", Usuario="
				+ Usuario + ", Telefono=" + Telefono + "]";
	}


	}

	
