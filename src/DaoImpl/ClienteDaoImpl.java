package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Entidad.Cliente;



public class ClienteDaoImpl {
	
	private static final String Insertar = "INSERT INTO clientes (CUIL_Cli, DNI_Cli, Nombre_Cli, Apellido_Cli, Sexo_Cli, Nacionalidad_Cli, FechaNacimiento_Cli, Direccion_Cli, IdLocalidad_Cli, IdProvincia_Cli,"
			+ " CorreoElectronico_Cli, Usuario_Cli, NroTelefono_Cli) VALUES(?, ?, ?, ?,?, ?,?, ?,?, ?,?, ?,?)";
	private static final String Listar = "SELECT Nombre_Cli, Apellido_Cli, Usuario_Cli FROM Clientes";
	private static final String Eliminar = "Delete from personas where Usuario_Cli = ?";
	
	public boolean Agregar(Cliente cliente) {
		
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
			
			statement.setString(1, cliente.getCuil());
			statement.setString(2, cliente.getDni());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getApellido());
			statement.setInt(5, cliente.getSexo());
			statement.setString(6, cliente.getNacionalidad());
			statement.setString(7, cliente.getFecha());
			statement.setString(8, cliente.getDireccion());
			statement.setInt(9,cliente.getLocalidad());
			statement.setInt(10, cliente.getProvincia());
			statement.setString(11,cliente.getCorreo());
			statement.setString(12, cliente.getUsuario());
			statement.setString(13, cliente.getTelefono());
			
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
	

	
	public ResultSet LeerClientes(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		Statement st;
		
	try {
		st = conexion.createStatement();
		ResultSet result = st.executeQuery(Listar);
		return result;		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	
	public boolean Eliminar(Cliente cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean SeElimino = false;
		try
		{
			statement = conexion.prepareStatement(Eliminar);
			statement.setString(1, cliente.getUsuario());

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
	
}
