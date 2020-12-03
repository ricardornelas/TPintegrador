package Negocio;

import java.util.ArrayList;

import Entidad.Usuario;

public interface UsuarioNegocio {
	public boolean EliminarUsuario(String Usuario);
	public boolean CambiarContraseña(String Usuario,String NuevaContraseña);
	public boolean ValidarLogin(String Usuario,String Password);
	public boolean AgregarUsuario(Usuario usuario);
}
