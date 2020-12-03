package Negocio;

import java.util.ArrayList;

import Entidad.TipoMovimiento;

public interface TipoMovimientoNegocio {
	public ArrayList<TipoMovimiento> ObtenerTiposMovimientos();
	public String ObtenerNombre (int IdTipoMovimiento);
}
