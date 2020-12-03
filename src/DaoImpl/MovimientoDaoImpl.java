package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.MovimientoDao;
import Entidad.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{
	
	private static final String Agregar = "INSERT INTO MOVIMIENTOS(CBURemitente_Mov,CBUDestinatario_Mov,Monto_Mov,IdTipoMovimiento_Mov,Fecha_Mov) values (?,?,?,?,?) ";
	private static final String Leer = "SELECT * FROM MOVIMIENTOS";
	
	
	public boolean Agregar(Movimiento mov) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean SeInserto = false;
		try
		{
			statement = conexion.prepareStatement(Agregar);
			
			statement.setString(1, mov.getCBURemitente_Mov());
			statement.setString(2,mov.getCBUDestinatario_Mov());
			statement.setFloat(3,mov.getMonto_Mov());
			statement.setInt(4,mov.getIdTipoMovimiento_Mov());
			statement.setString(5,mov.getFecha());
			
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

	public ResultSet ObtenerMovimientos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Statement st;
		
		try {
			st = conexion.createStatement();
			ResultSet result = st.executeQuery(Leer);
			return result;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
