package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.CuotasDao;
import Entidad.Cuotas;

public class CuotasDaoImpl implements CuotasDao {

private static final String Insertar = "INSERT INTO cuotas ( IdPrestamo_Cuo, NroCuota_Cuo, EstaPagado_Cuo, FechaDePago_Cuo) "
			+ "VALUES(?, ?, ?, ?)";
private static final String TodasLasCuotas ="SELECT * FROM cuotas";
private static final String RegistrarPago = "UPDATE cuotas SET EstaPagado_Cuo=1, FechaPagado_Cuo=? WHERE IdPrestamo_Cuo=? AND NroCuota_Cuo=?";
	

public ResultSet TodasLasCuotas(){
	
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
	ResultSet result = st.executeQuery(TodasLasCuotas);
	
	return result;		
} catch (SQLException e) {
	e.printStackTrace();
}
return null;
}

	public boolean AgregarCuota(Cuotas cuota) {
		
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
			
			statement.setInt(1, cuota.getIdPrestamo());
			statement.setInt(2, cuota.getNroCuota());
			statement.setBoolean(3,cuota.getPagado());
			statement.setString(4,cuota.getFechaDePago());
			
			
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

	@Override
	public boolean RegistrarPagoCuota(Cuotas cuota) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean SeRegistro = false;
		try
		{
			statement = conexion.prepareStatement(RegistrarPago);
			
			statement.setString(1, cuota.getFechaPagado());
			statement.setInt(2, cuota.getIdPrestamo());
			statement.setInt(3,cuota.getNroCuota());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				SeRegistro= true;
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
		
		return SeRegistro;
	}
	
}
