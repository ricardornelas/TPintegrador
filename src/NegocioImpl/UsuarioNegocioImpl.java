package NegocioImpl;

import DaoImpl.UsuarioDaoImpl;
import Entidad.Usuario;
import Negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{


	public boolean EliminarUsuario(String Usuario) {
		return new UsuarioDaoImpl().Eliminar(Usuario); 
	}

	@Override
	public boolean CambiarContraseña(String Usuario, String NuevaContraseña) {
		return new UsuarioDaoImpl().CambiarPassword(Usuario, NuevaContraseña);
	}

	@Override
	public boolean ValidarLogin(String Usuario, String Password) {
		Usuario aux = new UsuarioDaoImpl().DevolverUsuario(Usuario);
		if(aux.getContraseña().compareTo(Password)==0) return true;
		
		return false;
	}
	
	public Usuario DevolverUsuario(String Usuario) {
		Usuario aux = new UsuarioDaoImpl().DevolverUsuario(Usuario);
		return aux;
	}

}
