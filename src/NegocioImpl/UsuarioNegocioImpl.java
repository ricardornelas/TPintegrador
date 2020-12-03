package NegocioImpl;

import Dao.UsuarioDao;
import DaoImpl.UsuarioDaoImpl;
import Entidad.Usuario;
import Negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{
	
	private UsuarioDao pdao = new UsuarioDaoImpl();

	public boolean EliminarUsuario(String Usuario) {
		return new UsuarioDaoImpl().Eliminar(Usuario); 
	}

	@Override
	public boolean CambiarContrase�a(String Usuario, String NuevaContrase�a) {
		return new UsuarioDaoImpl().CambiarPassword(Usuario, NuevaContrase�a);
	}

	@Override
	public boolean ValidarLogin(String Usuario, String Password) {
		Usuario aux = new UsuarioDaoImpl().DevolverUsuario(Usuario);
		if(aux.getContrase�a().compareTo(Password)==0) return true;
		
		return false;
	}
	
	public Usuario DevolverUsuario(String Usuario) {
		Usuario aux = new UsuarioDaoImpl().DevolverUsuario(Usuario);
		return aux;
	}

}
