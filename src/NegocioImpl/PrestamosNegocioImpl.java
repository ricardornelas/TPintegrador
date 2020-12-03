package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import DaoImpl.PrestamosDaoImpl;
import Entidad.Cuenta;
import Entidad.Movimiento;
import Entidad.Prestamos;

public class PrestamosNegocioImpl {

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
				aux.setCuil(RS.getString(1));
				aux.setCbu(RS.getString(2));
				aux.setImpPedido(RS.getFloat(3));
				aux.setImpAPagar(RS.getFloat(4));
				aux.setCuotas(RS.getInt(5));
				aux.setMontoXMes(RS.getFloat(6));
				aux.setIdPrestamo(RS.getInt(7));
				
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return lista;
	}

	public boolean AceptarPrestamo(int pre) {
		
		ResultSet rs = new PrestamosDaoImpl().TodosLosPrestamos();
		
		try {
			while(rs.next()) {

				Movimiento mov = new Movimiento();
				mov.setCBUDestinatario_Mov(rs.getString("CBU_Pre"));
				Calendar c = Calendar.getInstance();
				String Fecha = Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH))+"-"+Integer.toString(c.get(Calendar.DATE));
				mov.setFecha(Fecha);
				mov.setIdTipoMovimiento_Mov(2);
				mov.setMonto_Mov(rs.getFloat("ImportePedido_Pre"));
				
				if(!new MovimientoNegocioImpl().NuevoPrestamo(mov)) return false;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new PrestamosDaoImpl().AceptarPrestamo(pre); 
	}

	public boolean RechazarPrestamo(int pre) {
		
		return new PrestamosDaoImpl().RechazarPrestamo(pre); 
	}

	
	public ArrayList<Prestamos> TodosLosPrestamos(){
		ArrayList<Prestamos> lista = new ArrayList<Prestamos>();
		ResultSet RS = new PrestamosDaoImpl().TodosLosPrestamos();
		
		try {
			while (RS.next()) {
				Prestamos aux = new Prestamos();
				aux.setCuil(RS.getString(1));
				aux.setCbu(RS.getString(2));
				aux.setImpPedido(RS.getFloat(3));
				aux.setImpAPagar(RS.getFloat(4));
				aux.setCuotas(RS.getInt(5));
				aux.setMontoXMes(RS.getFloat(6));
				aux.setIdPrestamo(RS.getInt(7));
				
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return lista;
	}
	
	
	public ArrayList<Prestamos> PrestamosXCUIL(String Cuil)  {
		
		ArrayList<Cuenta> ListaCuentas = new CuentaNegocioImpl().CuentasXCUIL(Cuil);
		
		ResultSet Rs = new PrestamosDaoImpl().TodosLosPrestamos();
		ArrayList<Prestamos> ListaPrestamos= new ArrayList<Prestamos>();
		
		try {
			while (Rs.next()) {
				
				for(Cuenta cue: ListaCuentas) {
					
					if(Rs.getString("CBU_Pre").compareTo(cue.getCBU())==0) {
					
							Prestamos aux = new Prestamos();
							aux.setIdPrestamo(Rs.getInt("IdPrestamo_Pre"));
							aux.setCbu(Rs.getString("CBU_Pre"));
							aux.setImpAPagar(Rs.getFloat("ImporteAPagar_Pre"));
							aux.setImpPedido(Rs.getFloat("ImportePedido_Pre"));
							aux.setMontoXMes(Rs.getFloat("MontoXMes_Pre"));
							aux.setCuotas(Rs.getInt("Cuotas_Pre"));
							aux.setAutorizado(Rs.getBoolean("EstaAutorizado_Pre"));
							aux.setPagado(Rs.getBoolean("EstaPagado_Pre"));
			
							if(!aux.getPagado())	ListaPrestamos.add(aux);
			}
				
		  }
				
		}
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	return ListaPrestamos;
		
	}
	
	public float DevolverMonto(int id) {
		
		ResultSet Rs = new PrestamosDaoImpl().TodosLosPrestamos();
		
		try {
			while (Rs.next()) {
				if(Rs.getInt("IdPrestamo_Pre")==id) {
					return Rs.getFloat("Monto_Pre");
			}	
		}
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	return 0;
	}
	
}
