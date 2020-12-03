package Dao;

import java.sql.ResultSet;
import Entidad.Cuotas;

public interface CuotasDao {

	public boolean AgregarCuota(Cuotas cuota);
	public ResultSet TodasLasCuotas();
	public boolean RegistrarPagoCuota(Cuotas cuota);
}
