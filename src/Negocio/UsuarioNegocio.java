package Negocio;

import Entidad.Usuario;

public interface UsuarioNegocio {
	public boolean EliminarUsuario(String Usuario);
	public boolean CambiarContrase�a(String Usuario,String NuevaContrase�a);
	public boolean ValidarLogin(String Usuario,String Password);
	
}
