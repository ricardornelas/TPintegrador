package Dao;

import java.sql.ResultSet;

import Entidad.Movimiento;

public interface MovimientoDao {
	public boolean Agregar(Movimiento mov);
	public ResultSet ObtenerMovimientos();
}
