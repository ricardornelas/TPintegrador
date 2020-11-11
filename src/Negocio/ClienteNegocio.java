package Negocio;

import java.util.ArrayList;

import Entidad.Cliente;

public interface ClienteNegocio {

public boolean Agregar(Cliente cliente);
public ArrayList<Cliente> CargarClientes();
}
