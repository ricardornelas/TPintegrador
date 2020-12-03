package DaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.TipoMovimientoDao;

public class TipoMovimientoDaoImpl implements TipoMovimientoDao {

	private static final String Leer = "SELECT * FROM TiposMovimientos";
	
	public ResultSet ObtenerTiposMovimientos() {
		
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
