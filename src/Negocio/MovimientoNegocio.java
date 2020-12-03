package Negocio;

import java.util.ArrayList;

import Entidad.Movimiento;

public interface MovimientoNegocio {
	public boolean NuevoPrestamo(Movimiento mov);
	public ArrayList<Movimiento> ObtenerMovimientos();
	public ArrayList<Movimiento> ObtenerMovimientosXCBU(String CBU);
	public boolean NuevaTransferencia(Movimiento mov);
	public boolean NuevoPagoCuota(Movimiento mov);
}
