package Controlador;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DaoImpl.ClienteDaoImpl;
import DaoImpl.UsuarioDaoImpl;
import Entidad.Cliente;
import Entidad.Usuario;

/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("btnAgregar")!=null) {
			
			Cliente cli = new Cliente();
			Usuario usu = new Usuario();
			
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
			usu.setContraseña(request.getParameter("txtPassword"));
			usu.setEsAdmin(false);
			 
			UsuarioDaoImpl usi = new UsuarioDaoImpl();
			usi.Agregar(usu);
	
			ClienteDaoImpl cdi = new ClienteDaoImpl();
			cdi.Agregar(cli);

		}

		RequestDispatcher rd = request.getRequestDispatcher("/AgregarCliente.jsp");
		rd.forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
