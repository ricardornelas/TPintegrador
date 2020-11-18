package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DaoImpl.Conexion;
import Entidad.Provincia;
import Dao.ProcinciaDao;

public class ProvinciaDaolmpl implements ProcinciaDao{
	
private static final String readall = "select * from Provincias;";
	
	public ArrayList<Provincia> readAll() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Provincia> prov = new ArrayList<Provincia>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prov.add(getProvincia(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return prov;
	}
	
	private Provincia getProvincia(ResultSet resultSet) throws SQLException
	{
		String id = resultSet.getString("IdProvincia_Pro");
		String nombre = resultSet.getString("Descripcion_Pro");

		Provincia prov = new Provincia();
		prov.setIdprovincia(id);
		prov.setNombre(nombre);
		
		return prov;
	}

}
