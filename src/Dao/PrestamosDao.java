package Dao;


import java.sql.ResultSet;

import Entidad.Prestamos;
import Entidad.Cliente;
import java.util.ArrayList;



public interface PrestamosDao {

public boolean SolicitarPrestamo(Prestamos prestamo);
public ArrayList<Prestamos> ListarPrestamosAutorizar();
public ResultSet LeerMisPrestamos();	
public ArrayList<Prestamos> listarPrestamosCliente(Cliente cliente);
public boolean RechazarPrestamo(Prestamos prest);
public boolean AprobarPrestamo(Prestamos prest);
public boolean actualizarPrestamo(Prestamos prestamo);
public boolean darBajaPrestamo(Prestamos prestamo);

}
