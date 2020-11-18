package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.LocalidadDao;
import Entidad.Localidad;
import Entidad.Provincia;

public class LocalidadDaoImpl implements LocalidadDao{

	private static final String readall = "Select * from localidades INNER JOIN provincias ON idprovincia_loc = idprovincia_prov WHERE idprovincia_loc = ?;";
	
	@Override
	public ArrayList<Localidad> readAll(String param) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> loc = new ArrayList<Localidad>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setString(1, param);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				loc.add(getLocalidad(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return loc;
	}
	
	private Localidad getLocalidad(ResultSet resultSet) throws SQLException
	{
		String idprov = resultSet.getString("IdProvincia_Prov");
		String nombreprov = resultSet.getString("Descripcion_Prov");
		String idloc = resultSet.getString("IdLocalidad_Loc");
		String nombreloc = resultSet.getString("Descripcion_Loc");
			
		Provincia prov = new Provincia(idprov, nombreprov);
		
		Localidad loc = new Localidad();
		loc.setProvincia(prov);
		loc.setNombre(nombreloc);
		loc.setIdlocalidad(idloc);
		
		return loc;
	}
	

}
