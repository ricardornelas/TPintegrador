package Negocio;

public interface UsuarioNegocio {
	public boolean EliminarUsuario(String Usuario);
	public boolean CambiarContraseña(String Usuario,String NuevaContraseña);
	public boolean ValidarLogin(String Usuario,String Password);
}
