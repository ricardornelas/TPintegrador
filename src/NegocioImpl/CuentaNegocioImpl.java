package NegocioImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import DaoImpl.ClienteDaoImpl;
import DaoImpl.CuentaDaoImpl;
import DaoImpl.MovimientoDaoImpl;
import Entidad.Cliente;
import Entidad.Cuenta;
import Entidad.Movimiento;
import Excepciones.CUILInexistente;
import Excepciones.LimiteCuentas;
import Negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio{

	public boolean EliminarCuenta(String cbu) {
		return new CuentaDaoImpl().Eliminar(cbu); 
	}
	
	private String GenerarCBU(String NroCuenta) {
		
		String CBU;
		
		String CodigoDeBanco="111";
		String CodigoDeSucursal="2222";
		String DigitoDeControl= Integer.toString(new Random().nextInt(8)+1);
		
		for(int Largo = NroCuenta.length(); Largo<13; Largo++) {
			NroCuenta = "0" + NroCuenta;
		}
		
		String DigitoDeControl2 = Integer.toString(new Random().nextInt(8)+1);
		CBU= CodigoDeBanco + CodigoDeSucursal + DigitoDeControl + NroCuenta + DigitoDeControl2;
		
		return CBU;
	}
	
	public String ValidarCuenta(Cuenta cuenta) throws LimiteCuentas, CUILInexistente{
		if(!new ClienteDaoImpl().verificarCliente(cuenta.getCUIL()))
			throw new CUILInexistente();
		
		if(new CuentaDaoImpl().CantidadDeCuentas(cuenta.getCUIL())==3)
			throw new LimiteCuentas();
		
		return null;
	}

	
	@Override
	public String Agregar(Cuenta cuenta) {
		
		String Mensaje = null;
		try{
			ValidarCuenta(cuenta);
		} catch(LimiteCuentas e){
			Mensaje = e.getMessage();
			return Mensaje;
		} catch(CUILInexistente e) {
			Mensaje = e.getMessage();
			return Mensaje;
		}
		
		CuentaDaoImpl dao = new CuentaDaoImpl();
		
		
		
		cuenta.setSaldo(10000);
		
		
		int NroCuenta = new CuentaDaoImpl().CantidadDeCuentasTotales();
		
		cuenta.setEstado(true);
		cuenta.setCBU(GenerarCBU(Integer.toString(NroCuenta)));
		cuenta.setNroCuenta(NroCuenta);
		
		Movimiento mov = new Movimiento();
		mov.setCBUDestinatario_Mov(cuenta.getCBU());
		mov.setFecha(cuenta.getFechaCreacion());
		mov.setIdTipoMovimiento_Mov(1);
		mov.setMonto_Mov(10000);
		mov.setCBURemitente_Mov(null);
		
		if(!new MovimientoDaoImpl().Agregar(mov)) {
			return null;
		}
		if(!dao.Agregar(cuenta)) {
			Mensaje += "No se pudo agregar la cuenta"; 
		}
		return Mensaje;
	}

	@Override
	public ArrayList<Cuenta> CuentasXCUIL(String CUIL) {
		ResultSet RS = new CuentaDaoImpl().CuentasXCUIL(CUIL);
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		try {
			while(RS.next()) {
				Cuenta aux=new Cuenta();
				aux.setCBU(RS.getString(1));
				aux.setCUIL(RS.getString(2));
				aux.setFechaCreacion(RS.getString(3));
				aux.setIdTipoCuenta(RS.getInt(4));
				aux.setNroCuenta(RS.getInt(5));
				aux.setSaldo(RS.getFloat(6));
				lista.add(aux);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Cuenta> LeerCuentas() {
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		
		ResultSet RS = new CuentaDaoImpl().LeerCuentas();
		///String cBU, String cUIL, String fechaCreacion, int idTipoCuenta, int nroCuenta, float saldo
		try {
			while(RS.next()) {
				Cuenta reg = new Cuenta(RS.getString("CBU_Cue"),RS.getString("CUIL_Cue"),RS.getString("FechaCreacion_Cue"),RS.getInt("IdTipoCuenta_Cue"),RS.getInt("NroCuenta_Cue"),RS.getFloat("Saldo_Cue"));
				lista.add(reg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public boolean Modificar(Cuenta cuenta) {
		return new CuentaDaoImpl().Modificar(cuenta);
	}

	@Override
	public int CantidadDeCajasDeAhorro() {
		ResultSet rs = new CuentaDaoImpl().LeerCuentas();
		int c=0;
		try {
			while(rs.next()) {
				if(rs.getBoolean("Estado_Cue")&&rs.getInt("IdTipoCuenta_Cue")==1) c++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public int CantidadDeCuentasCorrientes() {
		ResultSet rs = new CuentaDaoImpl().LeerCuentas();
		int c=0;
		try {
			while(rs.next()) {
				if(rs.getBoolean("Estado_Cue")&&rs.getInt("IdTipoCuenta_Cue")==2) c++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public boolean SaldoSuficiente(float Monto, String CBU) {
		ResultSet rs = new CuentaDaoImpl().LeerCuentas();
		try {
			while(rs.next()) {
				if(rs.getString("CBU_Cue").compareTo(CBU)==0) {
					if(rs.getFloat("Saldo_Cue")>=Monto) return true; else return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
