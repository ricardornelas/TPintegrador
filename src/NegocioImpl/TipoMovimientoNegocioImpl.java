package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoImpl.TipoMovimientoDaoImpl;
import Entidad.TipoMovimiento;
import Negocio.TipoMovimientoNegocio;

public class TipoMovimientoNegocioImpl implements TipoMovimientoNegocio{


	public ArrayList<TipoMovimiento> ObtenerTiposMovimientos() {
		ResultSet RS = new TipoMovimientoDaoImpl().ObtenerTiposMovimientos();
		ArrayList<TipoMovimiento> lista = new ArrayList<TipoMovimiento>();
		try {
			while(RS.next()) {
				TipoMovimiento aux = new TipoMovimiento();
				aux.setIdTipoMovimiento_TM(RS.getInt("IdTipoMovimiento_TM"));
				aux.setDescripcion_TM(RS.getString("Descripcion_TM"));
				lista.add(aux);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String ObtenerNombre(int IdTipoMovimiento) {
		ResultSet RS = new TipoMovimientoDaoImpl().ObtenerTiposMovimientos();
		try {
			while(RS.next()) {
				if(RS.getInt("IdTipoMovimiento_TM")==IdTipoMovimiento) return RS.getString("Descripcion_TM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
