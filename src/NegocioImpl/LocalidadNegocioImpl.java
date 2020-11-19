package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DaoImpl.LocalidadDaoImpl;
import Entidad.Localidad;
import Negocio.LocalidadNegocio;


public class LocalidadNegocioImpl implements LocalidadNegocio {


	public ArrayList<Localidad> LeerLocalidades(int IdPro) {
		ArrayList<Localidad> lista = new ArrayList<Localidad>();
		
		ResultSet RS = new LocalidadDaoImpl().LeerLocalidades( IdPro);
		
		try {
			while(RS.next()) {
				Localidad reg = new Localidad(Integer.parseInt(RS.getString("IdProvincia_Loc")),Integer.parseInt(RS.getString("IdLocalidad_Loc")),RS.getString("Descripcion_Loc"));
				lista.add(reg);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
}
