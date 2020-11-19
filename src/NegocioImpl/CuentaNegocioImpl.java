package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import DaoImpl.ClienteDaoImpl;
import DaoImpl.CuentaDaoImpl;
import DaoImpl.UsuarioDaoImpl;
import Entidad.Cuenta;
import Negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio{

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
	
	@Override
	public boolean Agregar(Cuenta cuenta) {
		
		CuentaDaoImpl dao = new CuentaDaoImpl();
		
		if (dao.CantidadDeCuentas(cuenta.getCUIL())>=3) return false;
		
		///TODO: MOVIMIENTO DE $10000
		cuenta.setSaldo(10000);
		
		
		int NroCuenta = new CuentaDaoImpl().CantidadDeCuentasTotales();
		
		cuenta.setEstado(true);
		cuenta.setCBU(GenerarCBU(Integer.toString(NroCuenta)));
		
		
		return dao.Agregar(cuenta);
	}

	@Override
	public ArrayList<Cuenta> CuentasXCUIL(String CUIL) {
		ResultSet RS = new CuentaDaoImpl().CuentasXCUIL(CUIL);
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		Cuenta aux=new Cuenta();
		try {
			while(RS.next()) {
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

	public boolean EliminarCuenta(String Cuenta) {
		return new CuentaDaoImpl().Eliminar(Cuenta); 
	}
public ArrayList<Cuenta> CargarCuenta() {
		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		ResultSet RS = new CuentaDaoImpl().LeerCuentas();
		
		try {
			while (RS.next()) {
				Cuenta aux = new Cuenta();
				aux.setCUIL(RS.getString(1));
				aux.setCBU(RS.getString(2));
				aux.setNroCuenta(RS.getInt(3));
				aux.setSaldo(RS.getFloat(4));
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return lista;
	}
public ArrayList<Cuenta> CargarCuentasFiltradass(String CUIL) {
		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		ResultSet RS = new CuentaDaoImpl().CuentasXCUIL(CUIL);
		Cuenta aux = new Cuenta();
		try {
			while(RS.next()) {
				aux.setCUIL(RS.getString(1));
				aux.setCBU(RS.getString(2));
				aux.setNroCuenta(RS.getInt(3));
				aux.setSaldo(RS.getFloat(4));
				lista.add(aux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}
