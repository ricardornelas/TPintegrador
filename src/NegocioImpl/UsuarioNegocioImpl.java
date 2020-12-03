package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoImpl.ClienteDaoImpl;
import DaoImpl.UsuarioDaoImpl;
import Entidad.Cliente;
import Entidad.Usuario;
import Excepciones.CUILExistente;
import Excepciones.DNIExistente;
import Excepciones.LoginIncorrecto;
import Excepciones.UsuarioExistente;
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
	public boolean ValidarLogin(String Usuario, String Password) throws LoginIncorrecto {
		Usuario aux = new UsuarioDaoImpl().DevolverUsuario(Usuario);
		if(aux==null) throw new LoginIncorrecto();
		if(aux.getContraseña().compareTo(Password)!=0) throw new LoginIncorrecto();
		
		return true;
	}
	
	public Usuario DevolverUsuario(String Usuario) {
		Usuario aux = new UsuarioDaoImpl().DevolverUsuario(Usuario);
		return aux;
	}
	
	public ArrayList<String> VerificarUsuario(Usuario usuario){
		ResultSet RS = new UsuarioDaoImpl().LeerUsuarios();
		boolean UsuarioExistente = false;
		try {
			while(RS.next()) {
				if(RS.getString("Usuario_Usu").compareTo(usuario.getNombreU())==0) UsuarioExistente = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayList <String> Lista = new ArrayList<String>();
		if(UsuarioExistente)
			Lista.add(new UsuarioExistente().getMessage());
		
		return Lista;
	}
	
	@Override
	public boolean AgregarUsuario(Usuario usuario) {
		
	return new UsuarioDaoImpl().Agregar(usuario);
	}

}
