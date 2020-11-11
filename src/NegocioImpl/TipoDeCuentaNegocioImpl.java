package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entidad.TipoDeCuenta;
import Negocio.TipoDeCuentaNegocio;
import DaoImpl.TipoDeCuentaDaoImpl;

public class TipoDeCuentaNegocioImpl implements TipoDeCuentaNegocio{

	public ArrayList<TipoDeCuenta> CargarTiposDeCuenta() {
		ArrayList<TipoDeCuenta> lista = new ArrayList<TipoDeCuenta>();
		
		ResultSet RS = new TipoDeCuentaDaoImpl().LeerTiposDeCuenta();
		
		try {
			while(RS.next()) {
				TipoDeCuenta reg = new TipoDeCuenta(Integer.parseInt(RS.getString("IdTipoCuenta_TC")),RS.getString("Descripcion_TC"));
				lista.add(reg);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}

}
