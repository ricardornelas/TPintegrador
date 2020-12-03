package Controlador;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cuenta;
import Entidad.Cliente;
import Entidad.Prestamos;

import Negocio.ClienteNegocio;
import Negocio.CuentaNegocio;
import Negocio.PrestamosNegocio;

import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.CuentaNegocioImpl;
import NegocioImpl.PrestamosNegocioImpl;

@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletPrestamos() {
        super();
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//SOLICIUTD DE PRESTAMO//
		if(request.getParameter("btnSolicitud")!=null) {
			Prestamos pres = new Prestamos();
			PrestamosNegocioImpl prsimp = new PrestamosNegocioImpl();
			pres.setCbu(request.getParameter("txtCBU"));
			pres.setImpPedido(Float.parseFloat(request.getParameter("txtMonto")));
			pres.setFecha(request.getParameter("FechaPrestamo"));
			int cuotas = Integer.parseInt(request.getParameter("ddlCuotas"));
			pres.setCuotas(cuotas);
			float monto = Float.parseFloat(request.getParameter("txtMonto"));
			float montoxmes = monto/cuotas;
			pres.setMontoXMes(montoxmes);
			
			prsimp.SolicitarPrestamo(pres);	
		}
		
		if(request.getParameter("listar")!= null) {
		
			Cliente cli = new Cliente();
			ClienteNegocioImpl cl = new ClienteNegocioImpl();
			Cliente CLISession = (Cliente) request.getSession().getAttribute("userconect");
			cli = cl.BuscarUsuario(CLISession);
			PrestamosNegocioImpl np = new PrestamosNegocioImpl();
			ArrayList<Prestamos> listaDePrestamos = np.listarPrestamosPorUsuario(cli);
			request.setAttribute("listaPrestamos", listaDePrestamos); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
