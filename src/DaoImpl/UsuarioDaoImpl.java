package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.UsuarioDao;
import Entidad.Cliente;
import Entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{

	private static final String Insertar = "INSERT INTO usuarios (Usuario_Usu, Contraseña_Usu, EsAdministrador_Usu ) VALUES(?, ?, ?)";
	private static final String EliminarUsuario = "Update usuarios Set usuarios.Estado_Usu=0 Where usuarios.Usuario_Usu=";
	private static final String CambiarContraseña = "Update usuarios Set Contraseña_Usu=? Where Usuario_Usu=?";
	private static final String BuscarUsuario = "SELECT * FROM Usuarios WHERE Estado_Usu = true AND Usuario_Usu = ";
	
	public boolean Agregar(Usuario usuario) {
		 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean SeInserto = false;
		try
		{
			statement = conexion.prepareStatement(Insertar);
			
			statement.setString(1, usuario.getNombreU());
			statement.setString(2, usuario.getContraseña());
			statement.setBoolean(3, usuario.getEsAdmin());
			
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				SeInserto= true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return SeInserto;
	}
	public boolean Eliminar(String Usuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean SeElimino = false;
		try
		{
			statement = conexion.prepareStatement(EliminarUsuario+"'"+Usuario+"'");

			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				SeElimino= true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return SeElimino;
	}
	@Override
	public boolean CambiarPassword(String Usuario, String PasswordNuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean SeModifico = false;
		try
		{
			statement = conexion.prepareStatement(CambiarContraseña);
			statement.setString(1, PasswordNuevo);
			statement.setString(2, Usuario);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				SeModifico= true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return SeModifico;
	}
	@Override
	public Usuario DevolverUsuario(String Usuario) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		Statement st;
		
	try {
		st = conexion.createStatement();
		ResultSet result = st.executeQuery(BuscarUsuario+ " '" +Usuario + "'");
		result.next();
		
		Usuario aux = new Usuario();
		aux.setNombreU(result.getString(1));
		aux.setContraseña(result.getString(2));
		aux.setEsAdmin(result.getBoolean(3));
		aux.setEstado(result.getBoolean(4));
		
		return aux;	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	
}
