package Negocio;

import java.util.ArrayList;

import Entidad.Cuenta;

public interface CuentaNegocio {
	public boolean Agregar(Cuenta cuenta);
	public ArrayList<Cuenta> CuentasXCUIL(String CUIL);
}
