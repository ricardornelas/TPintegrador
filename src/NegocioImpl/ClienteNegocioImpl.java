package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DaoImpl.ClienteDaoImpl;
import DaoImpl.UsuarioDaoImpl;
import Entidad.Cliente;
import Excepciones.CUILExistente;
import Excepciones.DNIExistente;
import Excepciones.UsuarioExistente;
import Negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio{


	public ArrayList<Cliente> CargarClientes() {
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		ResultSet RS = new ClienteDaoImpl().LeerClientes();
		
		try {
			while (RS.next()) {
				Cliente aux = new Cliente();
				aux.setCuil(RS.getString(1));
				aux.setDni(RS.getString(2));
				aux.setNombre(RS.getString(3));
				aux.setApellido(RS.getString(4));
				aux.setSexo(RS.getInt(5));
				aux.setNacionalidad(RS.getString(6));
				aux.setFecha(RS.getString(7));
				aux.setDireccion(RS.getString(8));
				aux.setLocalidad(RS.getInt(9));
				aux.setProvincia(RS.getInt(10));
				aux.setCorreo(RS.getString(11));
				aux.setUsuario(RS.getString(12));
				aux.setTelefono(RS.getString(13));
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return lista;
	}

public ArrayList<Cliente> ClientesXProvincia(int id) {
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		ResultSet RS = new ClienteDaoImpl().FiltrarXProvincia(id);
		
		try {
			while (RS.next()) {
				Cliente aux = new Cliente();
				aux.setCuil(RS.getString(1));
				aux.setDni(RS.getString(2));
				aux.setNombre(RS.getString(3));
				aux.setApellido(RS.getString(4));
				aux.setSexo(RS.getInt(5));
				aux.setNacionalidad(RS.getString(6));
				aux.setFecha(RS.getString(7));
				aux.setDireccion(RS.getString(8));
				aux.setLocalidad(RS.getInt(9));
				aux.setProvincia(RS.getInt(10));
				aux.setCorreo(RS.getString(11));
				aux.setUsuario(RS.getString(12));
				aux.setTelefono(RS.getString(13));
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return lista;
	}

	@Override
	public ArrayList<Cliente> CargarClientesFiltrados(String CUIL) {
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		ResultSet RS = new ClienteDaoImpl().LeerClientesCUIL(CUIL);
		Cliente aux = new Cliente();
		try {
			while(RS.next()) {
				aux.setCuil(RS.getString(1));
				aux.setDni(RS.getString(2));
				aux.setNombre(RS.getString(3));
				aux.setApellido(RS.getString(4));
				aux.setSexo(RS.getInt(5));
				aux.setNacionalidad(RS.getString(6));
				aux.setFecha(RS.getString(7));
				aux.setDireccion(RS.getString(8));
				aux.setLocalidad(RS.getInt(9));
				aux.setProvincia(RS.getInt(10));
				aux.setCorreo(RS.getString(11));
				aux.setUsuario(RS.getString(12));
				aux.setTelefono(RS.getString(13));
				lista.add(aux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}


	@Override
	public ResultSet DevolverCliente(String Usuario) {
		
		return new ClienteDaoImpl().DevolverCliente(Usuario);
		
	}

	
	public int TotalDeClientes() {
		
		ArrayList<Cliente>Lista=new ClienteNegocioImpl().CargarClientes();
		
		int i=0;
		
		for(Cliente reg: Lista ) {
			
				i++;				
		}
		
		return i;
	}
		
	
public int CantidadDeClientes(int IdProvincia) {
	
	ArrayList<Cliente>Lista=new ClienteNegocioImpl().CargarClientes();
	
	int i=0;
	
	for(Cliente reg: Lista ) {
		
		if(reg.getProvincia() == IdProvincia) {
			
			i++;
			
		}
		
	}
	
	return i;
}
	
	@Override
	public ArrayList<String> VerificarCliente(Cliente cliente){
		ResultSet RS = new ClienteDaoImpl().LeerTodosLosClientes();
		boolean DNIExistente=false, CUILExistente = false, UsuarioExistente = false;
		try {
			while(RS.next()) {
				if(RS.getString("DNI_Cli").compareTo(cliente.getDni())==0) DNIExistente = true;
				if(RS.getString("CUIL_Cli").compareTo(cliente.getCuil())==0) CUILExistente = true;
				System.out.println(CUILExistente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RS = new UsuarioDaoImpl().LeerUsuarios();
		
		try {
			while(RS.next()) {
				if(RS.getString("Usuario_Usu").compareTo(cliente.getUsuario())==0) UsuarioExistente = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayList <String> Lista = new ArrayList<String>();
		if(DNIExistente)
			Lista.add(new DNIExistente().getMessage());
		if(CUILExistente)
			Lista.add(new CUILExistente().getMessage());
		if(UsuarioExistente)
			Lista.add(new UsuarioExistente().getMessage());
		
		return Lista;
	}

	public boolean Agregar(Cliente cliente) {
		
		return new ClienteDaoImpl().Agregar(cliente);
	}
	
	public ResultSet DevolverClienteYUsuario(String Usuario) {
		ResultSet RS = new ClienteDaoImpl().LeerClientes();
		
		try {
			while(RS.next()) {
				if(RS.getString("Usuario_Cli").compareTo(Usuario)==0) {
					return RS;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean Modificar(Cliente cliente) {
		
		return new ClienteDaoImpl().Modificar(cliente);
	}
	
}
