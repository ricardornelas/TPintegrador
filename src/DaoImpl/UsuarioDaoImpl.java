package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Entidad.Usuario;

public class UsuarioDaoImpl {

	private static final String Insertar = "INSERT INTO usuarios (Usuario_Usu, Contraseña_Usu, EsAdministrador_Usu ) VALUES(?, ?, ?)";

	
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
	
}
