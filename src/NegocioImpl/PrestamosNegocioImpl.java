package NegocioImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Dao.PrestamosDao;
import DaoImpl.PrestamosDaoImpl;
import Entidad.Prestamos;
import Entidad.Cliente;

public class PrestamosNegocioImpl {
	
	private PrestamosDao rdao = new PrestamosDaoImpl();
	
	public boolean SolicitarPrestamo(Prestamos prestamo) {
		
		PrestamosDaoImpl dao = new PrestamosDaoImpl();
		
		prestamo.setAutorizado(false);
		prestamo.setPagado(false);
		
		
		return dao.SolicitarPrestamo(prestamo);
	}
	
	public ArrayList<Prestamos> CargarPrestamos() {
		
		ArrayList<Prestamos> lista = new ArrayList<Prestamos>();
		ResultSet RS = new PrestamosDaoImpl().LeerMisPrestamos();
		
		try {
			while (RS.next()) {
				Prestamos aux = new Prestamos();
				aux.setIdPrestamo(RS.getInt(1));
				aux.setCbu(RS.getString(2));
				aux.setImpAPagar(RS.getFloat(3));
				aux.setImpPedido(RS.getFloat(4));
				aux.setMontoXMes(RS.getFloat(5));
				aux.setCuotas(RS.getInt(6));
			


				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return lista;
	}

	public ArrayList<Prestamos> listarPrestamosPorUsuario(Cliente cliente) {
		
		   return rdao.listarPrestamosCliente(cliente);
		}
	
	public boolean RechazarPestamo(Prestamos prest) {
        boolean estado=false;
        if(prest.getIdPrestamo() > 0 ) 
        {
            estado = rdao.RechazarPrestamo(prest);
        }
        return estado;
	}

	public boolean AprobarPestamo(Prestamos prest) {
        boolean estado=false;
        if(prest.getIdPrestamo() > 0 ) 
        {
            estado = rdao.AprobarPrestamo(prest);
        }
        return estado;
	}

	public boolean actualizarPrestamo(Prestamos prest) {
		  return rdao.actualizarPrestamo(prest);
	}
	
	public boolean darBajaPrestamo(Prestamos prest) {
        return rdao.darBajaPrestamo(prest);
	}
	
}
