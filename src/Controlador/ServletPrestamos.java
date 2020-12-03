package Controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidad.Cuotas;
import Entidad.Prestamos;
import Excepciones.SaldoInsuficiente;
import NegocioImpl.CuotasNegocioImpl;
import NegocioImpl.PrestamosNegocioImpl;



/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String Mensaje;
		
		
		if(request.getParameter("btnSeleccionarCuota")!= null) {
			
			request.setAttribute("IdPrestamoElegido",Integer.parseInt(request.getParameter("IdPrestamo")));
			request.setAttribute("NroCuotaElegido",Integer.parseInt(request.getParameter("NroCuota")));
			request.setAttribute("MontoElegido",Float.parseFloat(request.getParameter("Monto")));
			request.setAttribute("CuotaElegida", true);;

			RequestDispatcher rd = request.getRequestDispatcher("/PagoDeCuotas.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnAceptar")!=null) {
			
		
			Cuotas cuo = new Cuotas();
			int pre = Integer.parseInt(request.getParameter("Id"));
			
			
			new PrestamosNegocioImpl().AceptarPrestamo(pre);
			
			for(int i=1; i<=Integer.parseInt(request.getParameter("Cuotas")) ;i++){
				
				cuo.setIdPrestamo(Integer.parseInt(request.getParameter("Id")));
				cuo.setNroCuota(i);
				
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int mes = c.get(Calendar.MONTH)+1;
				int dia = c.get(Calendar.DATE); 
				
				mes+=2+i;
				while(mes>12){
					mes-=12;
					year++;
				}
				String Fecha = Integer.toString(year) + "-" + Integer.toString(mes) + "-" + Integer.toString(dia);
				cuo.setFechaDePago(Fecha);
				
				if(new CuotasNegocioImpl().AgregarCuota(cuo)) {
					Mensaje = "Solicitud de Prestamo Aceptada";
					request.setAttribute("Mensaje", Mensaje);
					request.setAttribute("Exito", true);
				} else {
					Mensaje = "Error al Aceptar la Solicitud";
					request.setAttribute("Mensaje", Mensaje);
				}
			}
			
		    RequestDispatcher rd = request.getRequestDispatcher("/PrestamosSolicitados.jsp");
		    rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnRechazar")!=null) {
			
			
			int pre = Integer.parseInt(request.getParameter("Id"));
			
			
		
			boolean prestamo=new PrestamosNegocioImpl().RechazarPrestamo(pre);
			
			if(prestamo == true) {
				Mensaje = "Se Rechazo la solicitud";
				request.setAttribute("Exito", true);
	        } else {
	        	Mensaje = "Error al Rechazar la solicutud";
	        }
			
			request.setAttribute("Mensaje", Mensaje);
			
		    RequestDispatcher rd = request.getRequestDispatcher("/PrestamosSolicitados.jsp");
		    rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnSolicitud")!=null) {
			
	        float Importe = (float) (Float.parseFloat(request.getParameter("txtMonto"))*1.10);
			
			Prestamos pre = new Prestamos();

			Calendar c = Calendar.getInstance();
			String Fecha = Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH))+"-"+Integer.toString(c.get(Calendar.DATE));
			
			pre.setCbu(request.getParameter("ddlCuenta"));
			pre.setImpAPagar(Importe);
			pre.setImpPedido(Float.parseFloat(request.getParameter("txtMonto")));
			pre.setFecha(Fecha);
			pre.setMontoXMes((float)Importe/ Integer.parseInt(request.getParameter("ddlCuotas")));
			pre.setCuotas(Integer.parseInt(request.getParameter("ddlCuotas")));

			
			
			
			boolean prestamo = new PrestamosNegocioImpl().SolicitarPrestamo(pre);
			
			if(prestamo == true) {
				Mensaje = "Se envio con exito la solicitud";
				request.setAttribute("Exito", true);
	        }
			
			else {
				
				Mensaje = "No pudo enviarse la solicitud";
			    request.setAttribute("Fracaso", true);
                 
			}
			request.setAttribute("Mensaje", Mensaje);
		    RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
		    rd.forward(request, response);
	
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("ConfirmarPagoCuota")!=null) {
			try{
				if(new CuotasNegocioImpl().RegistrarPago(Integer.parseInt(request.getParameter("IdPrestamo")),Integer.parseInt(request.getParameter("NroCuota")),Float.parseFloat(request.getParameter("Monto")),request.getParameter("ddlCuenta"))) {
					request.setAttribute("Exito", true);
					request.setAttribute("Mensaje", "Se realizó el pago con exito");
				} else {
					request.setAttribute("Mensaje", "No se pudo realizar el pago");
				}
			} catch(SaldoInsuficiente e) {
				request.setAttribute("Mensaje", e.getMessage());
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/PagoDeCuotas.jsp");
			rd.forward(request, response);
		}

		
		if(request.getParameter("btnSeleccionarPrestamo") != null){
			
			//TODO: PREGUNTAS SI YA SE ENCUENTRA APROBADO EL PRESTAMO
			
			request.setAttribute("CBU", request.getParameter("Cbu"));
			request.setAttribute("IdPrestamo", request.getParameter("Id"));
			request.setAttribute("MontoXMes", request.getParameter("MontoXMes"));
			
			System.out.println(request.getAttribute("CBU"));
			
			RequestDispatcher rd = request.getRequestDispatcher("/PagoDeCuotas.jsp");

			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnPrestamo")!=null) {
		
		RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
		rd.forward(request, response);
		}
		

		
		if(request.getParameter("btnPagarCuota")!= null) {
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/PagoDeCuotas.jsp");
			rd.forward(request, response);
		}

		
	}

	public ArrayList<Prestamos> PrestamosXCUIL(String Cuil){
		return new PrestamosNegocioImpl().PrestamosXCUIL(Cuil);
		
		
	}
	
	public ArrayList<Prestamos> ListadoPrestamos(){
		ArrayList<Prestamos> lista = new PrestamosNegocioImpl().CargarPrestamos();
		
		return lista;
	}
	
public int DevolverCuotasPagas(int id) {
	return new CuotasNegocioImpl().CuotasPagadas(id);
}

public ArrayList<Cuotas> CuotasXIdPrestamo(int IdPrestamo) {
	return new CuotasNegocioImpl().TodasLasCuotas(IdPrestamo);
}

public ArrayList<Prestamos> DevolverTodosLosPrestamos(){
	return new PrestamosNegocioImpl().CargarPrestamos();
}

}
