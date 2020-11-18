package Dao;

import java.util.ArrayList;

import Entidad.Localidad;

public interface LocalidadDao {
	public ArrayList<Localidad> readAll(String param);
}
