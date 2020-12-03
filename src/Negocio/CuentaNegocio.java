package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import Entidad.Cuenta;
import Excepciones.LimiteCuentas;


public interface CuentaNegocio {
	public String Agregar(Cuenta cuenta);
	public ArrayList<Cuenta> CuentasXCUIL(String CUIL);
	public boolean EliminarCuenta(String CBU);
	public ArrayList<Cuenta> LeerCuentas();
	public boolean Modificar(Cuenta cuenta);
	public String ValidarCuenta(Cuenta cuenta) throws LimiteCuentas;
	public int CantidadDeCajasDeAhorro();
	public int CantidadDeCuentasCorrientes();
	
}
