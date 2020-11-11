package DaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Dao.TipoDeCuentaDao;

public class TipoDeCuentaDaoImpl implements TipoDeCuentaDao{
	
	private static final String Listar = "SELECT * FROM TIPOSCUENTAS";
	
	public ResultSet LeerTiposDeCuenta() {
		
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

}
