package NegocioImpl;

import java.util.ArrayList;
import java.util.List;

import Dao.ProcinciaDao;
import DaoImpl.ProvinciaDaolmpl;
import Entidad.Provincia;
import Negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio{

	ProcinciaDao dao = new ProvinciaDaolmpl ();
	
	@Override
	public ArrayList<Provincia> readAll() {
		ArrayList<Provincia> lista = new ArrayList<Provincia>();
		lista = new ProvinciaDaolmpl().readAll();
		return lista;
	}

}