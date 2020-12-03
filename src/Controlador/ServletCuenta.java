package Controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidad.Cuenta;
import Entidad.Movimiento;
import Entidad.TipoDeCuenta;
import Entidad.TipoMovimiento;
import Excepciones.CBUIguales;
import Excepciones.CBUInexistente;
import Excepciones.SaldoInsuficiente;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.CuentaNegocioImpl;
import NegocioImpl.MovimientoNegocioImpl;
import NegocioImpl.TipoDeCuentaNegocioImpl;
import NegocioImpl.TipoMovimientoNegocioImpl;


@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCuenta() {
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		if(request.getParameter("btnEliminar")!=null) {
			try{
			request.setAttribute("CuentaSeleccionada", request.getParameter("CBU"));
			} catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCuenta.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnAgregar")!=null) {
			
			Cuenta cuenta = new Cuenta();
			cuenta.setCUIL((String)request.getParameter("txtCuil"));
			cuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("ddlTipoCuenta")));
			Calendar c = Calendar.getInstance();
			String Fecha = Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH))+"-"+Integer.toString(c.get(Calendar.DATE));
			cuenta.setFechaCreacion(Fecha);

			
			String Mensaje = new CuentaNegocioImpl().Agregar(cuenta);
			if(Mensaje ==null) {
				Mensaje = "Se agrego con exito";
				request.setAttribute("Exito", true);
			}
			request.setAttribute("Mensaje", Mensaje);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnModificar")!=null) {
			
			Cuenta aux = new Cuenta();
			try{
				aux.setCBU(request.getParameter("CBU"));
				aux.setCUIL(request.getParameter("Cuil"));
				aux.setIdTipoCuenta(Integer.parseInt(request.getParameter("IdTipoCuenta")));
				aux.setNroCuenta(Integer.parseInt(request.getParameter("NroCuenta")));
				aux.setSaldo(Float.parseFloat(request.getParameter("Saldo")));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("CuentaSeleccionada", aux);
			RequestDispatcher rd = request.getRequestDispatcher("/ModificarCuenta.jsp");
			rd.forward(request, response);
		}

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Historial")!=null) {
			
			request.setAttribute("CBU",request.getParameter("CBU"));
			
			RequestDispatcher rd = request.getRequestDispatcher("/HistorialCuenta.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnAceptarEliminar")!=null) {
			new CuentaNegocioImpl().EliminarCuenta(request.getParameter("CBU"));
			request.setAttribute("Mensaje", "Se elimino exitosamente");
			request.setAttribute("Exito",true);
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCuenta.jsp");
			rd.forward(request, response);
			
		} else if(request.getParameter("btnCancelarEliminar")!=null) {
			request.setAttribute("Mensaje", "No se elimino la cuenta seleccionada");
			request.setAttribute("Exito",true);
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCuenta.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnConfirmarModificar")!=null) {
			Cuenta aux = new Cuenta();
			try{
				aux.setCBU((String)request.getParameter("CBU"));
				aux.setCUIL((String)request.getParameter("ddlCuil"));
				aux.setIdTipoCuenta((Integer.parseInt(request.getParameter("ddlTipoCuenta"))));
				aux.setSaldo((Float.parseFloat(request.getParameter("Saldo"))));

				request.setAttribute("CuentaSeleccionada", null);
				}catch(Exception e) {
					e.printStackTrace();
				}
			
			new CuentaNegocioImpl().Modificar(aux);
			request.setAttribute("Mensaje", "Datos modificados exitosamente");
			request.setAttribute("Exito",true);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModificarCuenta.jsp");
			rd.forward(request, response);
		}
		
		
		
		if(request.getParameter("btnConfirmarEliminar")!=null) {

			try {
			String CBU = request.getParameter("CBU");
			new CuentaNegocioImpl().EliminarCuenta(CBU);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCuenta.jsp");
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
		if(request.getParameter("btnTransferirEntreCuentas")!=null) {
			
			Movimiento mov = new Movimiento();
			ArrayList<String> Mensajes = new ArrayList<String>();
			
			try{
				mov.setCBURemitente_Mov(request.getParameter("ddlCuentaOrigen"));
				mov.setCBUDestinatario_Mov(request.getParameter("ddlCuentaDestino"));
				mov.setIdTipoMovimiento_Mov(4);
				Calendar c = Calendar.getInstance();
				String Fecha = Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH))+"-"+Integer.toString(c.get(Calendar.DATE));
				mov.setFecha(Fecha);
				mov.setMonto_Mov(Integer.parseInt(request.getParameter("txtMonto")));
				
				try {
					if(new MovimientoNegocioImpl().NuevaTransferencia(mov)) {
						Mensajes.add("Se realizo la operacion exitosamente");
						request.setAttribute("Exito", true);
					} else {
						Mensajes.add("No se pudo realizar la operacion");
					}
				} catch (CBUIguales e) {
					Mensajes.add(e.getMessage());
				} catch (SaldoInsuficiente e) {
					Mensajes.add(e.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("Mensajes", Mensajes);
			RequestDispatcher rd = request.getRequestDispatcher("/TransferenciaEntreCuentas.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnTransferirOtraCuenta")!=null) {
			
			Movimiento mov = new Movimiento();
			ArrayList<String> Mensajes = new ArrayList<String>();
			
			try{
				mov.setCBURemitente_Mov(request.getParameter("ddlCuentaOrigen"));
				mov.setCBUDestinatario_Mov(request.getParameter("txtCbuDestino"));
				mov.setIdTipoMovimiento_Mov(4);
				Calendar c = Calendar.getInstance();
				String Fecha = Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH))+"-"+Integer.toString(c.get(Calendar.DATE));
				mov.setFecha(Fecha);
				mov.setMonto_Mov(Integer.parseInt(request.getParameter("txtMonto")));
				
				try {
					if(new MovimientoNegocioImpl().NuevaTransferencia(mov)) {
						Mensajes.add("Se realizo la operacion exitosamente");
						request.setAttribute("Exito", true);
					} else {
						Mensajes.add("No se pudo realizar la operacion");
					}
				} catch (CBUIguales e) {
					Mensajes.add(e.getMessage());
				} catch (SaldoInsuficiente e) {
					Mensajes.add(e.getMessage());
				} catch (CBUInexistente e) {
					Mensajes.add(e.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("Mensajes", Mensajes);
			RequestDispatcher rd = request.getRequestDispatcher("/TransferenciaOtraCuenta.jsp");
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

	public ArrayList<Cuenta> ListadoCuentas(){
		ArrayList<Cuenta> lista = new CuentaNegocioImpl().LeerCuentas();
		
		return lista;
	}
	
	public ArrayList<Movimiento> ListadoMovimientosXCBU(String CBU){
		return new MovimientoNegocioImpl().ObtenerMovimientosXCBU(CBU);
	}
	
	public ArrayList<TipoMovimiento> ListadoTiposMovimiento(){
		return new TipoMovimientoNegocioImpl().ObtenerTiposMovimientos();
	}
	
	public int CantidadCajasDeAhorro() {
		return new CuentaNegocioImpl().CantidadDeCajasDeAhorro();
	}
	public int CantidadCuentasCorrientes() {
		return new CuentaNegocioImpl().CantidadDeCuentasCorrientes();
	}
	
}
