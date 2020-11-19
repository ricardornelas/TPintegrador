package Dao;

import java.sql.ResultSet;

import Entidad.Cuenta;

public interface CuentaDao {
	public boolean Agregar(Cuenta cuenta);
	public int CantidadDeCuentas(String CUIL);
	public int CantidadDeCuentasTotales();
	public ResultSet CuentasXCUIL(String CUIL);
	public boolean Eliminar(String CBU);
	public ResultSet LeerCuentas();
	public boolean Modificar(Cuenta cuenta);
}
//