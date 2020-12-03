package Controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DaoImpl.UsuarioDaoImpl;
import Entidad.Cliente;
import Entidad.Usuario;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.LocalidadNegocioImpl;
import NegocioImpl.ProvinciaNegocioImpl;
import NegocioImpl.UsuarioNegocioImpl;
import Entidad.Provincia;
import Entidad.Localidad;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnEliminar")!=null) {
			Cliente aux = new Cliente();
			aux.setCuil((String)request.getParameter("Cuil"));
			aux.setNombre((String)request.getParameter("Nombre"));
			aux.setApellido((String)request.getParameter("Apellido"));
			aux.setUsuario((String)request.getParameter("Usuario"));
			
			request.setAttribute("ClienteSeleccionado", aux);
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnModificar")!=null) {
			Cliente cli = new Cliente();
			Usuario usu = new Usuario();
			ResultSet RS = new ClienteNegocioImpl().DevolverClienteYUsuario((String)request.getParameter("Usuario"));
			
			try {
				cli.setCuil(RS.getString("CUIL_Cli"));
				cli.setDni(RS.getString("DNI_Cli"));
				cli.setNombre(RS.getString("Nombre_Cli"));
				cli.setApellido(RS.getString("Apellido_Cli"));
				cli.setSexo(RS.getByte("Sexo_Cli"));
				cli.setNacionalidad(RS.getString("Nacionalidad_Cli"));
				cli.setFecha(RS.getString("FechaNacimiento_Cli"));
				cli.setDireccion(RS.getString("Direccion_Cli"));
				cli.setLocalidad(RS.getInt("IdLocalidad_Cli"));
				cli.setProvincia(RS.getInt("IdProvincia_Cli"));
				cli.setCorreo(RS.getString("CorreoElectronico_Cli"));
				cli.setUsuario(RS.getString("Usuario_Cli"));
				cli.setTelefono(RS.getString("NroTelefono_Cli"));
				usu.setNombreU(RS.getString("Usuario_Usu"));
				usu.setContraseña(RS.getString("Contraseña_Usu")); 
				
				request.setAttribute("ClienteSeleccionado", cli);
				request.setAttribute("UsuarioSeleccionado", usu);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModificarCliente.jsp");
			rd.forward(request, response);
		}
		
		
		
		if(request.getParameter("btnAgregar")!=null) {
			
			Cliente cli = new Cliente();
			Usuario usu = new Usuario();
			cli.setNombre(request.getParameter("txtNombre"));
			cli.setApellido(request.getParameter("txtApellido"));
			cli.setDni(request.getParameter("txtDni"));
			cli.setCuil(request.getParameter("txtCuil"));
			if(request.getParameter("RadioGenero")!=null)
			cli.setSexo(Integer.parseInt(request.getParameter("RadioGenero")));
			cli.setFecha((request.getParameter("FechaNac")));
			cli.setNacionalidad(request.getParameter("txtNacionalidad"));
			if(request.getParameter("ddlProvincia")!=null)
			cli.setProvincia(Integer.parseInt(request.getParameter("ddlProvincia")));
			cli.setDireccion(request.getParameter("txtDireccion"));
			if(request.getParameter("ddlLocalidad")!=null)
			cli.setLocalidad(Integer.parseInt(request.getParameter("ddlLocalidad")));
			cli.setTelefono(request.getParameter("txtTelefono"));
			cli.setUsuario(request.getParameter("txtUsuario"));
			cli.setCorreo(request.getParameter("txtEmail"));
			
			ArrayList<String> Mensajes = new ArrayList<String>();
			Mensajes = new ClienteNegocioImpl().VerificarCliente(cli);
			
			
			if(request.getParameter("txtPassword").compareTo(request.getParameter("txtRepPassword"))!=0)
				Mensajes.add("Las contraseñas no son iguales");

			
			usu.setNombreU(request.getParameter("txtUsuario"));
			
			usu.setContraseña(request.getParameter("txtPassword"));
			
			usu.setEsAdmin(false);
			if(Mensajes.isEmpty())
				{
					new UsuarioDaoImpl().Agregar(usu);
					new ClienteNegocioImpl().Agregar(cli);
					Mensajes.add("Se agrego con exito el cliente");
					request.setAttribute("Exito",true);
				}
			
			request.setAttribute("Mensajes", Mensajes);
			
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCliente.jsp");
			rd.forward(request, response);
		}
		
		
		
		if(request.getParameter("FiltrarProvincia")!=null) {
	
			int IdProvincia = Integer.parseInt(request.getParameter("ddlFiltroProvincia"));
			ArrayList<Cliente> Lista = new ClienteNegocioImpl().ClientesXProvincia(IdProvincia);
			System.out.println(Lista.size());
			request.setAttribute("Listado",Lista);
			RequestDispatcher rd = request.getRequestDispatcher("/ListadoClientes.jsp");
			rd.forward(request, response);
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		if(request.getParameter("btnConfirmarEliminar")!=null) {

			try {
			String Usuario = (String)request.getParameter("Usu");
			if(new UsuarioNegocioImpl().EliminarUsuario(Usuario)) {
				request.setAttribute("Mensaje","Se elimino exitosamente");
				request.setAttribute("Exito",true);
			} else
				request.setAttribute("Mensaje","No se pudo eliminar");
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCliente.jsp");
			rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnCancelarEliminar")!=null) {
			
			request.setAttribute("ClienteSeleccionado", null);
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnConfirmarModificar")!=null) {
			try {
				Cliente cli = new Cliente();
				Usuario usu = new Usuario();
				
				
				cli.setNombre(request.getParameter("txtNombre"));
				cli.setApellido(request.getParameter("txtApellido"));
				
				cli.setDni(request.getParameter("txtDni"));
				cli.setCuil(request.getParameter("txtCuil"));
				
				cli.setSexo(Integer.parseInt(request.getParameter("RadioGenero")));
				cli.setFecha((request.getParameter("FechaNac")));
				cli.setNacionalidad(request.getParameter("txtNacionalidad"));
				cli.setProvincia(Integer.parseInt(request.getParameter("ddlProvincia")));
				cli.setDireccion(request.getParameter("txtDireccion"));
				cli.setLocalidad(Integer.parseInt(request.getParameter("ddlLocalidad")));
				cli.setTelefono(request.getParameter("txtTelefono"));
				cli.setUsuario(request.getParameter("txtUsuario"));
				usu.setNombreU(request.getParameter("txtUsuario"));
				cli.setCorreo(request.getParameter("txtCorreo"));
				usu.setContraseña(request.getParameter("txtPassword"));
				
				ArrayList<String> Mensajes = new ArrayList<String>(); 
				if(!new UsuarioNegocioImpl().CambiarContraseña(usu.getNombreU(), usu.getContraseña())){
					Mensajes.add("No se pudo modificar la contraseña.");
				}
				if(!new ClienteNegocioImpl().Modificar(cli)) {
					Mensajes.add("No se pudo modificar los datos del cliente.");
				}
				if(Mensajes.isEmpty()){
					Mensajes.add("Se modificaron los clientes exitosamente");
					request.setAttribute("Exito", true);
				}
				
				request.setAttribute("Mensajes", Mensajes);
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/ModificarCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarModificar")!=null) {
			
			
			request.setAttribute("listado", new ClienteNegocioImpl().CargarClientesFiltrados((String)request.getParameter("txtCuil")));
			RequestDispatcher rd = request.getRequestDispatcher("/ModificarCliente.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnFiltrarEliminar")!=null) {
			
			
			request.setAttribute("listado", new ClienteNegocioImpl().CargarClientesFiltrados((String)request.getParameter("txtCuil")));
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCliente.jsp");
			rd.forward(request, response);
		}
	}
	
	public ArrayList<Cliente> ListadoClientes(){
		ArrayList<Cliente> lista = new ClienteNegocioImpl().CargarClientes();
		
		return lista;
	}
	
	public void Prueba(Cliente reg) {
	
		Cliente aux = new Cliente();
		try{
			aux.setCuil(reg.getCuil());
		aux.setNombre(reg.getNombre());
		aux.setApellido(reg.getApellido());
		aux.setUsuario(reg.getUsuario());
		}
		catch(Exception e) {
			e.printStackTrace();
		}			
		System.out.println(reg.getCuil());
		}
	
	public ArrayList<Provincia> Provincia() {
		ArrayList<Provincia> lista = new ProvinciaNegocioImpl().LeerProvincias();
		return lista;
	}
	
	public ArrayList<Localidad> Localidad() {
		ArrayList<Localidad> lista = new LocalidadNegocioImpl().LeerLocalidades(); //Eliminar parametros 
		return lista;
	}
	
	public String NombreProvincia(int IdProvincia) {
		return new ProvinciaNegocioImpl().NombreProvincia(IdProvincia);
	}
	
	public String NombreLocalidad(int IdLocalidad) {
		return new LocalidadNegocioImpl().NombreLocalidad(IdLocalidad);
	}

		
	 public int ClientesPorProvincia(int IdProvincia) {
			return new ClienteNegocioImpl().CantidadDeClientes(IdProvincia);
		}
	
	 public int ClientesTotales() {
		 return new ClienteNegocioImpl().TotalDeClientes();
	 }
}

