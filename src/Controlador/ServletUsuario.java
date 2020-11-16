package Controlador;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import Entidad.Cliente;
import Entidad.Usuario;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.UsuarioNegocioImpl;


@WebServlet("/ServletLogin")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletUsuario() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		if(request.getParameter("btnLogin")!=null) {
			String Usuario = request.getParameter("txtUsuario");
			String Contraseña = request.getParameter("txtPassword");
			try{
				if(new UsuarioNegocioImpl().ValidarLogin(Usuario, Contraseña)) {
				Usuario Usu = new UsuarioNegocioImpl().DevolverUsuario(Usuario);
				request.setAttribute("UsuarioConectado", Usu);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
	}
	public ResultSet DevolverCliente(Usuario Usu) {
		return new ClienteNegocioImpl().DevolverCliente(Usu.getNombreU());
	}

}
