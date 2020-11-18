package NegocioImpl;

import java.util.ArrayList;

import Dao.LocalidadDao;
import DaoImpl.LocalidadDaoImpl;
import Entidad.Localidad;
import Negocio.LocalidadNegocio;


public class LocalidadNegocioImpl implements LocalidadNegocio{
	
	LocalidadDao dao = new LocalidadDaoImpl();
	
	@Override
	public ArrayList<Localidad> readAll(String param) {
		return dao.readAll(param);
	}

}
