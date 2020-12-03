package Dao;

import java.sql.ResultSet;

import Entidad.Cliente;

public interface ClienteDao {

	public boolean Agregar(Cliente cliente);
	public ResultSet LeerClientes();
	public ResultSet LeerClientesCUIL(String CUIL);
	public ResultSet DevolverCliente(String Usuario);
	public ResultSet LeerTodosLosClientes();
	public boolean Modificar(Cliente cliente);
}
