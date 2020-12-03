package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.CuentaDao;
import Entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao{

	private static final String Insertar = "INSERT INTO cuentas (CBU_Cue,CUIL_Cue,FechaCreacion_Cue,IdTipoCuenta_Cue,NroCuenta_Cue,Saldo_Cue) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String ConsultaContar = "SELECT COUNT(CBU_CUE) FROM cuentas";
	private static final String BuscarCuentas = "SELECT * FROM Cuentas WHERE Cuil_Cue = ";
	private static final String EliminarCuenta = "Update cuentas Set cuentas.Estado_Cue=0 Where cuentas.CBU_Cue=";
	private static final String Listar = "SELECT * FROM CUENTAS WHERE Estado_Cue=1";
	private static final String ModificarCuenta = "UPDATE cuentas SET CUIL_Cue=?, IdTipoCuenta_Cue=?,Saldo_Cue=? WHERE CBU_Cue=?";
;
	
	public boolean Agregar(Cuenta cuenta) {
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
			
			statement.setString(1, cuenta.getCBU());
			statement.setString(2, cuenta.getCUIL());
			statement.setString(3, cuenta.getFechaCreacion());
			statement.setInt(4, cuenta.getIdTipoCuenta());
			statement.setInt(5, cuenta.getNroCuenta());
			statement.setFloat(6, cuenta.getSaldo());
			
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
	public int CantidadDeCuentas(String CUIL) {
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
		ResultSet result = st.executeQuery(ConsultaContar +  " WHERE CUIL_CUE =" + CUIL + " AND Estado_Cue = '1'");
		result.next();
		return result.getInt(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
	}

	@Override
	public int CantidadDeCuentasTotales() {
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
		ResultSet result = st.executeQuery(ConsultaContar);
		result.next();
		return result.getInt(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
	}

	@Override
	public ResultSet CuentasXCUIL(String CUIL) {
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
		ResultSet result = st.executeQuery(BuscarCuentas + CUIL + " AND Estado_Cue = '1'");
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	public boolean Eliminar(String CBU) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean SeElimino = false;
		
		try
		{
			statement = conexion.prepareStatement(EliminarCuenta+"'"+CBU+"'");
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
	
public ResultSet LeerCuentas() {
		
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

public boolean Modificar(Cuenta cuenta) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean SeModifico = false;
	try
	{
		statement = conexion.prepareStatement(ModificarCuenta);
		statement.setString(1, cuenta.getCUIL());
		statement.setInt(2, cuenta.getIdTipoCuenta());
		statement.setFloat(3, cuenta.getSaldo());
		statement.setString(4, cuenta.getCBU());
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

	
}
