package Dao;

import java.sql.ResultSet;

import Entidad.Cliente;

public interface ClienteDao {

	public boolean Agregar(Cliente cliente);
	public ResultSet LeerClientes();
}
