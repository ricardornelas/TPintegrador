package Dao;

import java.sql.ResultSet;

import Entidad.Usuario;

public interface UsuarioDao {
	
	public boolean Agregar(Usuario usuario);
	public boolean CambiarPassword(String Usuario, String PasswordNuevo);
	public Usuario DevolverUsuario(String Usuario);
	public ResultSet LeerUsuarios();
}
