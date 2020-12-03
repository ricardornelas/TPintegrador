package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoImpl.CuentaDaoImpl;
import DaoImpl.MovimientoDaoImpl;
import Entidad.Cuenta;
import Entidad.Movimiento;
import Excepciones.CBUIguales;
import Excepciones.CBUInexistente;
import Excepciones.SaldoInsuficiente;
import Negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

	@Override
	public boolean NuevoPrestamo (Movimiento mov) {

		ResultSet rs = new CuentaDaoImpl().LeerCuentas();
		
		Cuenta Destinatario = null;
		
		try {
			while(rs.next()) {
				
				Cuenta cue = new Cuenta();
				
				cue.setCBU(rs.getString("CBU_Cue"));
				cue.setCUIL(rs.getString("CUIL_Cue"));
				cue.setEstado(true);
				cue.setFechaCreacion(rs.getString("FechaCreacion_Cue"));
				cue.setIdTipoCuenta(rs.getInt("IdTipoCuenta_Cue"));
				cue.setNroCuenta(rs.getInt("NroCuenta_Cue"));
				cue.setSaldo(rs.getFloat("Saldo_Cue"));
				
								
				if(mov.getCBUDestinatario_Mov().compareTo(cue.getCBU())==0) {
					float Saldo = cue.getSaldo();
					Saldo += mov.getMonto_Mov();
					cue.setSaldo(Saldo);
					Destinatario = cue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(Destinatario != null) {
			if(!new CuentaDaoImpl().Modificar(Destinatario)) return false;
		} else 	throw new CBUInexistente();
		
		return new MovimientoDaoImpl().Agregar(mov);
	}

	@Override
	public ArrayList<Movimiento> ObtenerMovimientos() {
		ResultSet RS = new MovimientoDaoImpl().ObtenerMovimientos();
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		try {
			while(RS.next()) {
				Movimiento aux = new Movimiento();
				aux.setCodigo_Mov(RS.getInt("Codigo_Mov"));
				aux.setCBURemitente_Mov(RS.getString("CBURemitente_Mov"));
				aux.setCBUDestinatario_Mov(RS.getString("CBUDestinatario_Mov"));
				aux.setMonto_Mov(RS.getFloat("Monto_Mov"));
				aux.setIdTipoMovimiento_Mov(RS.getInt("IdTipoMovimiento_Mov"));
				aux.setFecha(RS.getString("Fecha_Mov"));
				lista.add(aux);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean NuevaTransferencia(Movimiento mov) throws CBUIguales,SaldoInsuficiente,CBUInexistente{
		
		
		ResultSet rs = new CuentaDaoImpl().LeerCuentas();
		
		Cuenta Remitente = null,Destinatario = null;
		if(mov.getCBUDestinatario_Mov().compareTo(mov.getCBURemitente_Mov())==0) throw new CBUIguales();
		
		try {
			while(rs.next()) {
				Cuenta cue = new Cuenta();
				
				cue.setCBU(rs.getString("CBU_Cue"));
				cue.setCUIL(rs.getString("CUIL_Cue"));
				cue.setEstado(true);
				cue.setFechaCreacion(rs.getString("FechaCreacion_Cue"));
				cue.setIdTipoCuenta(rs.getInt("IdTipoCuenta_Cue"));
				cue.setNroCuenta(rs.getInt("NroCuenta_Cue"));
				cue.setSaldo(rs.getFloat("Saldo_Cue"));
				
								
				if(mov.getCBUDestinatario_Mov().compareTo(cue.getCBU())==0) {
					float Saldo = cue.getSaldo();
					Saldo += mov.getMonto_Mov();
					cue.setSaldo(Saldo);
					Destinatario = cue;
				}
				if(mov.getCBURemitente_Mov().compareTo(cue.getCBU())==0) {
					if(mov.getMonto_Mov()>= cue.getSaldo()) throw new SaldoInsuficiente();
					float Saldo = cue.getSaldo();
					Saldo -= mov.getMonto_Mov();
					cue.setSaldo(Saldo);
					Remitente = cue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(Remitente != null&&Destinatario != null) {
			if(!new CuentaDaoImpl().Modificar(Remitente)) return false;
			if(!new CuentaDaoImpl().Modificar(Destinatario)) return false;
		}
		if(Destinatario == null) {
			throw new CBUInexistente();
		}
		return new MovimientoDaoImpl().Agregar(mov);
	}

	@Override
	public ArrayList<Movimiento> ObtenerMovimientosXCBU(String CBU) {
		ResultSet RS = new MovimientoDaoImpl().ObtenerMovimientos();
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		try {
			while(RS.next()) {
				if(RS.getString("CBURemitente_Mov")!=null) {
					if(RS.getString("CBURemitente_Mov").compareTo(CBU)==0) {
						Movimiento aux = new Movimiento();
						aux.setCBURemitente_Mov(CBU);
						aux.setCBUDestinatario_Mov(RS.getString("CBUDestinatario_Mov"));
						aux.setCodigo_Mov(RS.getInt("Codigo_Mov"));
						aux.setFecha(RS.getString("Fecha_Mov"));
						aux.setIdTipoMovimiento_Mov(RS.getInt("IdTipoMovimiento_Mov"));
						int x = 1;
						if(aux.getIdTipoMovimiento_Mov()==3||aux.getIdTipoMovimiento_Mov()==4)x=-1;
						aux.setMonto_Mov(RS.getFloat("Monto_Mov")*x);
						lista.add(aux);
					}
				}
				if(RS.getString("CBUDestinatario_Mov")!=null) {
					if(RS.getString("CBUDestinatario_Mov").compareTo(CBU)==0) {
						Movimiento aux = new Movimiento();
						aux.setCBURemitente_Mov(RS.getString("CBURemitente_Mov"));
						aux.setCBUDestinatario_Mov(CBU);
						aux.setCodigo_Mov(RS.getInt("Codigo_Mov"));
						aux.setFecha(RS.getString("Fecha_Mov"));
						aux.setIdTipoMovimiento_Mov(RS.getInt("IdTipoMovimiento_Mov"));
						aux.setMonto_Mov(RS.getFloat("Monto_Mov"));
						lista.add(aux);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public boolean NuevoPagoCuota(Movimiento mov) {
		
		ResultSet rs = new CuentaDaoImpl().LeerCuentas();
		
		Cuenta Remitente = null;
		
		try {
			while(rs.next()) {
				
				Cuenta cue = new Cuenta();
				
				cue.setCBU(rs.getString("CBU_Cue"));
				cue.setCUIL(rs.getString("CUIL_Cue"));
				cue.setEstado(true);
				cue.setFechaCreacion(rs.getString("FechaCreacion_Cue"));
				cue.setIdTipoCuenta(rs.getInt("IdTipoCuenta_Cue"));
				cue.setNroCuenta(rs.getInt("NroCuenta_Cue"));
				cue.setSaldo(rs.getFloat("Saldo_Cue"));
				
								
				if(mov.getCBURemitente_Mov().compareTo(cue.getCBU())==0) {
					float Saldo = cue.getSaldo();
					Saldo -= mov.getMonto_Mov();
					cue.setSaldo(Saldo);
					Remitente = cue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(Remitente != null) {
			if(!new CuentaDaoImpl().Modificar(Remitente)) return false;
		} else 	throw new CBUInexistente();
		
		return new MovimientoDaoImpl().Agregar(mov);
		
	}
	
}
