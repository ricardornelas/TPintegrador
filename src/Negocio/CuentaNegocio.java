package Negocio;

import java.util.ArrayList;
import Entidad.Cuenta;


public interface CuentaNegocio {
	public boolean Agregar(Cuenta cuenta);
	public ArrayList<Cuenta> CuentasXCUIL(String CUIL);
	public boolean EliminarCuenta(String CBU);
	public ArrayList<Cuenta> LeerCuentas();
	public boolean Modificar(Cuenta cuenta);
	
}
