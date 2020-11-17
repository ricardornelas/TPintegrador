package Negocio;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.Cliente;

public interface ClienteNegocio {

public boolean Agregar(Cliente cliente);
public ArrayList<Cliente> CargarClientes();
public ArrayList<Cliente> CargarClientesFiltrados(String CUIL);
public ResultSet DevolverCliente(String Usuario);
}
