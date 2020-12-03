package Negocio;

import java.util.ArrayList;
import Entidad.Prestamos;

public interface PrestamosNegocio {

	public boolean SolicitarPrestamo(Prestamos prestamo);
	public ArrayList<Prestamos> CargarPrestamos();
	public boolean AceptaPrestamo(int pre);
	public boolean RechazarPrestamo(int pre);
	public ArrayList<Prestamos> PrestamosXCUIL(String Cuil);;

}
