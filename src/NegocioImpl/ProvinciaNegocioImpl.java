package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DaoImpl.ProvinciaDaoImpl;
import Negocio.ProvinciaNegocio;
import Entidad.Provincia;

public class ProvinciaNegocioImpl implements ProvinciaNegocio{

	public ArrayList<Provincia> LeerProvincias() {
		ArrayList<Provincia> lista = new ArrayList<Provincia>();
		
		ResultSet RS = new ProvinciaDaoImpl().LeerProvincias();
		
		try {
			while(RS.next()) {
				Provincia reg = new Provincia(RS.getInt("IdProvincia_Pro"),RS.getString("Descripcion_Pro"));
				lista.add(reg);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
}
