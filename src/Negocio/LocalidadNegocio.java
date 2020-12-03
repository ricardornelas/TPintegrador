package Negocio;

import java.util.ArrayList;
import Entidad.Localidad;

public interface LocalidadNegocio {

	public ArrayList<Localidad> LeerLocalidades();
	public String NombreLocalidad(int IdLocalidad);
}
