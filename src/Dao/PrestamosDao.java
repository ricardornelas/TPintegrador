package Dao;


import java.sql.ResultSet;

import Entidad.Prestamos;

public interface PrestamosDao {

public boolean SolicitarPrestamo(Prestamos prestamo);
public ResultSet LeerMisPrestamos();	
public boolean AceptarPrestamo(int pre);
public boolean RechazarPrestamo(int pre);
}
