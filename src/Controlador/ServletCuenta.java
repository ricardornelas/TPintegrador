package Controlador;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidad.TipoDeCuenta;
import NegocioImpl.TipoDeCuentaNegocioImpl;

/**
 * Servlet implementation class ServletCuenta
 */
@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAgregar")!=null) {
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
			rd.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	public ArrayList<TipoDeCuenta> TiposDeCuentas() {
		ArrayList<TipoDeCuenta> lista = new TipoDeCuentaNegocioImpl().CargarTiposDeCuenta();
		return lista;
	}

}
