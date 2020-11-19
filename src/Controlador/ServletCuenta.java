package Controlador;

import java.awt.Label;
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

import Entidad.Cliente;
import Entidad.Cuenta;
import Entidad.TipoDeCuenta;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.CuentaNegocioImpl;
import NegocioImpl.TipoDeCuentaNegocioImpl;
import NegocioImpl.UsuarioNegocioImpl;


@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAgregar")!=null) {
			
				try{CuentaNegocioImpl Neg = new CuentaNegocioImpl();
				
				Cuenta cuenta = new Cuenta();
				cuenta.setCUIL((String)request.getParameter("txtCuil"));
				cuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("ddlTipoCuenta")));
				cuenta.setFechaCreacion("2020-11-11");
				Neg.Agregar(cuenta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
			rd.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnEliminar")!=null) {
			Cuenta aux = new Cuenta();
			try{
				aux.setCBU((String)request.getParameter("CBU"));
			aux.setNroCuenta((Integer.parseInt(request.getParameter("NroCuenta"))));
			aux.setSaldo((Float.parseFloat(request.getParameter("Saldo"))));
			
			
			request.setAttribute("CuentaSeleccionada", aux);
			}catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnConfirmarEliminar")!=null) {

			try {
			String Cuenta = (String)request.getParameter("Usu");
			new CuentaNegocioImpl().EliminarCuenta(Cuenta);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnCancelarEliminar")!=null) {
			
			request.setAttribute("CuentaSeleccionada", null);
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCuenta.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarEliminar")!=null) {
			
			request.setAttribute("listado", new CuentaNegocioImpl().CuentasXCUIL((String)request.getParameter("txtCuil")));
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCuenta.jsp");
			rd.forward(request, response);
		}
		
	}

	public ArrayList<TipoDeCuenta> TiposDeCuentas() {
		ArrayList<TipoDeCuenta> lista = new TipoDeCuentaNegocioImpl().CargarTiposDeCuenta();
		return lista;
	}
	
	public ArrayList<Cuenta> CuentasXCUIL(String Usuario){
		ResultSet RS = new ClienteNegocioImpl().DevolverCliente(Usuario);
		try {
			RS.next();
		String CUIL = RS.getString("CUIL_Cli"); 
		return new CuentaNegocioImpl().CuentasXCUIL(CUIL);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
