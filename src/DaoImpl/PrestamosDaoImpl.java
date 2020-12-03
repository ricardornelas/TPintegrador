package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Dao.PrestamosDao;
import Entidad.Prestamos;

public  class PrestamosDaoImpl implements PrestamosDao{

	private static final String Insertar = "INSERT INTO prestamos (IdPrestamo_Pre, CBU_Pre, Fecha_Pre, "
			+ "ImporteAPagar_Pre, ImportePedido_Pre, MontoXMes_Pre, Cuotas_Pre) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String Listar = "SELECT CUIL_Cue, CBU_Pre, ImportePedido_Pre, ImporteAPagar_Pre, Cuotas_Pre,  "
			+ "MontoXMes_Pre, IdPrestamo_Pre FROM prestamos INNER JOIN cuentas on CBU_Pre = CBU_Cue WHERE EstaAutorizado_Pre = 0 ";
	private static final String TodosLosPrestamos = "SELECT * FROM prestamos";
	private static final String RechazarPrestamo= " DELETE FROM bdintegrador.prestamos WHERE IdPrestamo_Pre=";
	private static final String AceptarPrestamo ="Update prestamos Set prestamos.EstaAutorizado_Pre=1 Where prestamos.IdPrestamo_Pre="; 
	//TODO: Falta depositar el dinero en la cuenta que eligio el cliente


	public boolean SolicitarPrestamo(Prestamos prestamo) {
		
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
			
			statement.setInt(1, prestamo.getIdPrestamo());
			statement.setString(2, prestamo.getCbu());
			statement.setString(3, prestamo.getFecha());
			statement.setFloat(4, prestamo.getImpAPagar());
			statement.setFloat(5, prestamo.getImpPedido());
			statement.setFloat(6, prestamo.getMontoXMes());
			statement.setInt(7, prestamo.getCuotas());

			
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
	
public ResultSet LeerMisPrestamos(){
		
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
	

public boolean AceptarPrestamo(int pre) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean SeAcepto = false;
	try
	{
		statement = conexion.prepareStatement(AceptarPrestamo +"'"+Integer.toString(pre)+"'");

		if(statement.executeUpdate() > 0)
		{
			conexion.commit();
			SeAcepto= true;
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
	
	return SeAcepto;
}

public boolean RechazarPrestamo(int pre) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean SeAcepto = false;
	try
	{
		statement = conexion.prepareStatement(RechazarPrestamo +"'"+Integer.toString(pre)+"'");

		if(statement.executeUpdate() > 0)
		{
			conexion.commit();
			SeAcepto= true;
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
	
	return SeAcepto;
}

public ResultSet TodosLosPrestamos(){
	
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
	ResultSet result = st.executeQuery(TodosLosPrestamos);
	return result;		
} catch (SQLException e) {
	e.printStackTrace();
}
return null;
}




}

