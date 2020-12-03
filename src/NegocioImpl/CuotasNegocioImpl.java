package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import DaoImpl.CuotasDaoImpl;
import DaoImpl.PrestamosDaoImpl;
import Entidad.Cliente;
import Entidad.Cuenta;
import Entidad.Cuotas;
import Entidad.Movimiento;
import Excepciones.SaldoInsuficiente;
import Negocio.CuotasNegocio;

public class CuotasNegocioImpl implements CuotasNegocio{


public boolean AgregarCuota(Cuotas cuota) {
	
	CuotasDaoImpl dao = new CuotasDaoImpl();
	
	cuota.setPagado(false);
	
	return dao.AgregarCuota(cuota);
 }

public ArrayList<Cuotas> TodasLasCuotas() {
	
	ArrayList<Cuotas> lista = new ArrayList<Cuotas>();
	ResultSet RS = new CuotasDaoImpl().TodasLasCuotas();
	
	try {
		while (RS.next()) {
			Cuotas aux = new Cuotas();
			aux.setIdPrestamo(RS.getInt("IdPrestamo_Cuo"));
			aux.setNroCuota(RS.getInt("NroCuota_Cuo"));
			aux.setPagado(RS.getBoolean("EstaPagado_Cuo"));
			aux.setFechaDePago(RS.getString("FechaDePago_Cuo"));
			aux.setFechaPagado(RS.getString("FechaPagado_Cuo"));
			
			lista.add(aux);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	

	return lista;
}

public ArrayList<Cuotas> TodasLasCuotas(int IdPrestamo) {
	ArrayList<Cuotas> lista = new ArrayList<Cuotas>();
	ResultSet RS = new CuotasDaoImpl().TodasLasCuotas();
	
	try {
		while (RS.next()) {
			Cuotas aux = new Cuotas();
			aux.setIdPrestamo(RS.getInt("IdPrestamo_Cuo"));
			if(aux.getIdPrestamo()==IdPrestamo){
				aux.setNroCuota(RS.getInt("NroCuota_Cuo"));
				aux.setPagado(RS.getBoolean("EstaPagado_Cuo"));
				aux.setFechaDePago(RS.getString("FechaDePago_Cuo"));
				aux.setFechaPagado(RS.getString("FechaPagado_Cuo"));
				
				lista.add(aux);
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	

	return lista;
}

public int CuotasPagadas(int Id) {
	
	ArrayList<Cuotas>Lista=new CuotasNegocioImpl().TodasLasCuotas();
	
	int i=0;
	
	for(Cuotas reg:Lista) {
		
		if(reg.getIdPrestamo()==Id && reg.getPagado()==true) {
			 
			i++;
		}
	}
	
	return i;
	
}

@Override
public boolean RegistrarPago(int IdPrestamo, int NroCuota, float Monto ,String CBU) throws SaldoInsuficiente {
	Calendar c = Calendar.getInstance();
	String Fecha = Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH))+"-"+Integer.toString(c.get(Calendar.DATE));
	
	Cuotas cuo = new Cuotas();
	cuo.setFechaDePago(Fecha);
	cuo.setIdPrestamo(IdPrestamo);
	cuo.setNroCuota(NroCuota);
	
	ResultSet rs = new PrestamosDaoImpl().TodosLosPrestamos();
	Movimiento mov = new Movimiento();
	
	
	
	try {
		while(rs.next()) {
			
			
			if(rs.getInt("IdPrestamo_Pre")==IdPrestamo) {
				
				
				mov.setCBURemitente_Mov(CBU);
				if(!new CuentaNegocioImpl().SaldoSuficiente(Monto,CBU)) {
					throw new SaldoInsuficiente();
				}
				mov.setFecha(Fecha);
				mov.setIdTipoMovimiento_Mov(3);
				mov.setMonto_Mov(Monto);
				
				if(!new MovimientoNegocioImpl().NuevoPagoCuota(mov)) return false;
				if(!new CuotasDaoImpl().RegistrarPagoCuota(cuo)) return false;
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return true;
}


}