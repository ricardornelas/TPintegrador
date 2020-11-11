package Controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.UsuarioDaoImpl;
import Entidad.Usuario;

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
		usu.setContraseña(request.getParameter("txtPassword"));
		usu.setEsAdmin(true);
		 
		UsuarioDaoImpl usi = new UsuarioDaoImpl();
		usi.Agregar(usu);
	}
		RequestDispatcher rd = request.getRequestDispatcher("/AgregarAdministrador.jsp");
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
