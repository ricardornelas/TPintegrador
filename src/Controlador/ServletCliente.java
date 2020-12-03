package Controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DaoImpl.ClienteDaoImpl;
import DaoImpl.UsuarioDaoImpl;
import Entidad.Cliente;
import Entidad.Usuario;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.LocalidadNegocioImpl;
import NegocioImpl.ProvinciaNegocioImpl;
import NegocioImpl.UsuarioNegocioImpl;
import Entidad
.Provincia;
import Entidad.Localidad;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("btnEliminar")!=null) {
			Cliente aux = new Cliente();
			try{
				aux.setCuil((String)request.getParameter("Cuil"));
			aux.setNombre((String)request.getParameter("Nombre"));
			aux.setApellido((String)request.getParameter("Apellido"));
			aux.setUsuario((String)request.getParameter("Usuario"));
			
			request.setAttribute("ClienteSeleccionado", aux);
			}catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnModificar")!=null) {
			Cliente aux = new Cliente();
			try{
				aux.setCuil((String)request.getParameter("Cuil"));
				aux.setNombre((String)request.getParameter("Nombre"));
				aux.setApellido((String)request.getParameter("Apellido"));
				aux.setUsuario((String)request.getParameter("Usuario"));
			}
			catch(Exception e) {
				e.printStackTrace();
			}			
			request.setAttribute("ClienteSeleccionado", aux);
			RequestDispatcher rd = request.getRequestDispatcher("/ModificarCliente.jsp");
			rd.forward(request, response);
		}
		
		
		
		if(request.getParameter("btnAgregar")!=null) {
			
			Cliente cli = new Cliente();
			Usuario usu = new Usuario();
			
			try{
			cli.setNombre(request.getParameter("txtNombre"));
			cli.setApellido(request.getParameter("txtApellido"));
			cli.setDni(request.getParameter("txtDni"));
			cli.setCuil(request.getParameter("txtCuil"));
			cli.setSexo(Integer.parseInt(request.getParameter("RadioGenero")));
			cli.setFecha((request.getParameter("FechaNac")));
			cli.setNacionalidad(request.getParameter("txtNacionalidad"));
			cli.setProvincia(1);
			cli.setDireccion(request.getParameter("txtDireccion"));
			cli.setLocalidad(1);
			cli.setTelefono(request.getParameter("txtTelefono"));
			cli.setUsuario(request.getParameter("txtUsuario"));
			cli.setCorreo(request.getParameter("txtEmail"));
			
			
			usu.setNombreU(request.getParameter("txtUsuario"));
			usu.setContrase�a(request.getParameter("txtPassword"));
			usu.setEsAdmin(false);
			 
			UsuarioDaoImpl usi = new UsuarioDaoImpl();
			ClienteDaoImpl cdi = new ClienteDaoImpl();
				usi.Agregar(usu);
				session.setAttribute("RespuestaSql", cdi.Agregar(cli));
				cdi.Agregar(cli);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCliente.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		if(request.getParameter("btnConfirmarEliminar")!=null) {

			try {
			String Usuario = (String)request.getParameter("Usu");
			new UsuarioNegocioImpl().EliminarUsuario(Usuario);
			
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
		
		if(request.getParameter("btnModificar")!=null) {
			System.out.println("PASAMO");
			Cliente aux = new Cliente();
			try{
				aux.setCuil((String)request.getParameter("Cuil"));
			aux.setNombre((String)request.getParameter("Nombre"));
			aux.setApellido((String)request.getParameter("Apellido"));
			aux.setUsuario((String)request.getParameter("Usuario"));
			}
			catch(Exception e) {
				e.printStackTrace();
			}			
			request.setAttribute("ClienteSeleccionado", aux);
			RequestDispatcher rd = request.getRequestDispatcher("/ModificarCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnConfirmarModificar")!=null) {
			try {
				
			if(((String)request.getParameter("txtRepPassword")).compareTo((String)request.getParameter("txtPassword"))==0) {
				String Usuario = (String)request.getParameter("Usu");
				String Contrase�a = (String)request.getParameter("txtPassword");
				new UsuarioNegocioImpl().CambiarContrase�a(Usuario, Contrase�a);
				}
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
	
	public ArrayList<Localidad> Localidad(int aux) {
		ArrayList<Localidad> lista = new LocalidadNegocioImpl().LeerLocalidades(aux); //Eliminar parametros 
		return lista;
	}
	

}