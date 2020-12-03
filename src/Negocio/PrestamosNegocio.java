package Negocio;

import java.util.ArrayList;
import Entidad.Prestamos;
import Entidad.Cliente;

public interface PrestamosNegocio {

	public boolean SolicitarPrestamo(Prestamos prestamo);
	public ArrayList<Prestamos> CargarPrestamos();
	public ArrayList<Prestamos> listarPrestamosCliente(Cliente cliente);
	public boolean actualizarPrestamo(Prestamos prestamo);
	public boolean darBajaPrestamo(Prestamos prestamo);
}
