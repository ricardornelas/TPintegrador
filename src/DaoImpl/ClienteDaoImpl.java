package DaoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Dao.ClienteDao;
import Entidad.Cliente;

public class ClienteDaoImpl implements ClienteDao{
	
	private static final String Insertar = "INSERT INTO clientes (CUIL_Cli, DNI_Cli, Nombre_Cli, Apellido_Cli, Sexo_Cli, Nacionalidad_Cli, FechaNacimiento_Cli, Direccion_Cli, IdLocalidad_Cli, IdProvincia_Cli,"
			+ " CorreoElectronico_Cli, Usuario_Cli, NroTelefono_Cli) VALUES(?, ?, ?, ?,?, ?,?, ?,?, ?,?, ?,?)";
	private static final String Listar = "SELECT * FROM Clientes INNER JOIN Usuarios ON Usuario_Usu = Usuario_Cli WHERE Estado_Usu = 1";
	private static final String ListarTodo = "SELECT * FROM Clientes";
	private static final String Filtrar = "SELECT * FROM Clientes INNER JOIN Usuarios ON Usuario_Usu = Usuario_Cli WHERE Estado_Usu = 1 AND CUIL_Cli LIKE '%";
	private static final String FiltrarXProv = "SELECT * FROM Clientes INNER JOIN Usuarios ON Usuario_Cli = Usuario_Usu WHERE Estado_Usu = 1 and IdProvincia_Cli= ";
	private static final String BuscarClienteXUsuario = "SELECT CUIL_Cli,DNI_Cli,Nombre_Cli,Apellido_Cli,Sexo_Cli,Nacionalidad_Cli,FechaNacimiento_Cli,Direccion_Cli,"
			+ "Descripcion_Loc,Descripcion_Pro,CorreoElectronico_Cli,Usuario_Cli,NroTelefono_Cli FROM clientes INNER JOIN localidades ON IdLocalidad_Loc=IdLocalidad_Cli"
			+ " INNER JOIN provincias ON IdProvincia_Pro=IdProvincia_Cli WHERE Usuario_Cli = ";
	private static final String delete = "UPDATE bdintegrador.Clientes SET Deleted = 1 WHERE Cuil_Cli = ?";
	private static final String Modificar = "UPDATE Clientes SET Nombre_Cli = ?, Apellido_Cli = ?, Sexo_Cli = ?, Nacionalidad_Cli = ?,FechaNacimiento_Cli = ?, Direccion_Cli = ?, IdLocalidad_Cli = ?, IdProvincia_Cli = ?, CorreoElectronico_Cli = ?, NroTelefono_Cli  = ? WHERE Cuil_Cli = ?";
	
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
	
public ResultSet FiltrarXProvincia(int id){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		Statement st;
		
	try {
		st = conexion.createStatement();
		ResultSet result = st.executeQuery(FiltrarXProv+ " '" +id + "'");
		return result;		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	
	public boolean verificarCliente(String Cuil) {
		Statement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String Buscar = "Select Count(CUIL_Cli) From Clientes Where CUIL_Cli = '" +Cuil + "'" ;
		boolean respuesta = false;
		try{
			statement = conexion.createStatement();
			ResultSet result = statement.executeQuery(Buscar);    
			result.next();
         if(result.getInt(1)!=0) {
             respuesta = true;
         }
     } catch(Exception e){
         System.err.print("Ha ocurrido un error: "+ e.getMessage());
     } 
     return respuesta;
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
	
	public ResultSet LeerClientesCUIL(String CUIL){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		Statement st;
		
	try {
		st = conexion.createStatement();
		ResultSet result = st.executeQuery(Filtrar+CUIL+"%'");
		return result;		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public ResultSet DevolverCliente(String Usuario) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		Statement st;
		
	try {
		st = conexion.createStatement();
		ResultSet result = st.executeQuery(BuscarClienteXUsuario+ " '" +Usuario + "'");
		return result;	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	
	public boolean delete(Cliente cliente) {
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			cst = conexion.prepareCall(delete);
			cst.setString(1, cliente.getCuil());
			if(cst.executeUpdate() > 0)
			{
				//onexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return isdeleteExitoso;
	}

	public boolean Modificar(Cliente cliente) {
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean Modificado = false;
		try
		{
			
			cst = conexion.prepareCall(Modificar); 
			cst.setString(1, cliente.getNombre());
			cst.setString(2, cliente.getApellido());
			cst.setInt(3, cliente.getSexo());
			cst.setString(4,cliente.getNacionalidad());
			cst.setString(5,cliente.getFecha());
			cst.setString(6,cliente.getDireccion());
			cst.setInt(7,cliente.getLocalidad());
			cst.setInt(8,cliente.getProvincia());
			cst.setString(9,cliente.getCorreo());
			cst.setString(10,cliente.getTelefono());
			cst.setString(11, cliente.getCuil());
			
			
			if(cst.executeUpdate() > 0)
			{
				Modificado = true;
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
		return Modificado;
	}

	@Override
	public ResultSet LeerTodosLosClientes() {
		
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
		ResultSet result = st.executeQuery(ListarTodo);
		return result;		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
}
