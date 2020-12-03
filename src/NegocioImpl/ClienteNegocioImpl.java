package NegocioImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoImpl.ClienteDaoImpl;
import Entidad.Cliente;
import Negocio.ClienteNegocio;
import Dao.ClienteDao;

public class ClienteNegocioImpl implements ClienteNegocio{

	private ClienteDao pdao = new ClienteDaoImpl();
	
	public boolean Agregar(Cliente cliente) {
		
		return false;
	}


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

	public Cliente BuscarUsuario(Cliente cli) {
		   if(cli.getUsuario().trim().length()> 0)
	        {
	            return pdao.BuscarUsuario(cli);
	        }

	        return null;
	}
	
}
