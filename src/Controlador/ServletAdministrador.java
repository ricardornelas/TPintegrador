package Controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.UsuarioDaoImpl;
import Entidad.Usuario;
import NegocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletAdministrador
 */
@WebServlet("/ServletAdministrador")
public class ServletAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdministrador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAgregar")!=null) {
		
		Usuario usu = new Usuario();
		
		usu.setNombreU(request.getParameter("txtUsuario"));
		usu.setContrase�a(request.getParameter("txtPassword"));
		usu.setEsAdmin(true);
		ArrayList<String> Mensajes = new UsuarioNegocioImpl().VerificarUsuario(usu);
		if(usu.getContrase�a().compareTo(request.getParameter("txtRepPassword"))!=0) {
			Mensajes.add("Las contrase�as no son iguales");
		}
		if(Mensajes.isEmpty()) {
			if(new UsuarioNegocioImpl().AgregarUsuario(usu)) {
				Mensajes.add("Se agrego exitosamente");
				request.setAttribute("Mensajes", Mensajes);
				request.setAttribute("Exito", true);
			} else {
				Mensajes.add("No se pudo agregar");
				request.setAttribute("Mensajes", Mensajes);				
			}
		} else {
			request.setAttribute("Mensajes", Mensajes);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/AgregarAdministrador.jsp");
		rd.forward(request, response);
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
