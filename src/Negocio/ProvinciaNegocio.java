package Negocio;

import java.util.ArrayList;

import Entidad.Provincia;


public interface ProvinciaNegocio {
	
	public ArrayList<Provincia> LeerProvincias();
	public String NombreProvincia(int IdProvincia);
}
