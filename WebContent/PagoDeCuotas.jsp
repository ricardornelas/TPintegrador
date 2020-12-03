<%@page import="Entidad.TipoDeCuenta"%>
<%@page import="Entidad.Usuario"%>
<%@page import="Controlador.ServletCuenta"%>
<%@page import="Entidad.Cuenta"%>
<%@page import="Entidad.Cuotas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controlador.ServletPrestamos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 
	<script type="text/javascript">
	$(document).ready( function () {
	    $('#tabla').DataTable();
	} );
	</script>		
		<title>Pago de Cuotas</title>
	</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-info">
  		<div class="collapse navbar-collapse" id="navbarNav">
   			 <ul class="navbar-nav">
     			<li class="nav-item active">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="CuentasBancarias.jsp">Cuentas Bancarias</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="MisPrestamos.jsp">Mis Prestamos</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="TransferenciaEntreCuentas.jsp">Transferir Entre Mis Cuentas</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="TransferenciaOtraCuenta.jsp">Transferencia a Otra Cuenta</a>
      			</li>
    		</ul>
  		</div>
  		 <div class="dropdown">
  			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  				  <%=(String)session.getAttribute("NombreClienteConectado") %>
 			 </button>
  			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
  				  <a class="dropdown-item" href="Perfil.jsp">Perfil</a>
  				  <a class="dropdown-item" href="Login.jsp">Cerrar Sesion</a>
 			 </div>
		</div>
	</nav>
	
	<br>
	<H1>Pago de Cuotas</H1>
	<br>
	
	
	<%
	if(request.getAttribute("IdPrestamo")!=null){
		session.setAttribute("CBU", (String)request.getAttribute("CBU"));
		session.setAttribute("IdPrestamo", Integer.parseInt((String)request.getAttribute("IdPrestamo")));
		session.setAttribute("MontoXMes", Float.parseFloat((String)request.getAttribute("MontoXMes")));
	}
	String CBU = (String)session.getAttribute("CBU");
	int IdPrestamo = (int)session.getAttribute("IdPrestamo");
	float MontoXMes = (float)session.getAttribute("MontoXMes");
	ArrayList<Cuotas> lista = new ServletPrestamos().CuotasXIdPrestamo(IdPrestamo);
	%>
		<table id="tabla" class="display">
			<thead style="text-align: center;">
				<tr>
					<th>Numero cuota</th>
					<th>Importe a pagar</th>
					<th>Fecha limite de pago</th>
					<th>Fecha de pago</th>
					<th>Estado</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<%for(Cuotas reg:lista){ %>
				<tr>
						<td><%=reg.getNroCuota() %><input type="hidden" name="NroCuota" value="<%=reg.getNroCuota()%>"></td>
						<td>$<%=MontoXMes %><input type="hidden" name="Importe" value="<%=MontoXMes%>"></td>
						<td><%=reg.getFechaDePago() %></td>
						<td><%if(reg.getFechaPagado()!=null){%><%=reg.getFechaPagado()%><%} else {%>-<%}%></td>
						<td><%if(reg.getPagado()){ %>PAGADO<%} else {%>NO PAGADO<%} %><input type="hidden" name="CBU" value="<%=request.getAttribute("CBU")%>"></td>
						<td style="width: 1px;"><input type="submit" <%if(reg.getPagado()){%>disabled<%} %>  name="btnPagar" value="Pagar Cuota" onclick="window.location.href='ServletPrestamos?btnSeleccionarCuota=1&IdPrestamo=<%=IdPrestamo%>&NroCuota=<%=reg.getNroCuota()%>&Monto=<%=MontoXMes%>'" ></td>
				</tr>
			<% } %>
			</tbody>
		</table>
		
		
		<%if(request.getAttribute("CuotaElegida")!=null){ %>
		<form action="ServletPrestamos" method="post" required>
			<h1>Completar Datos</h1><br>
			Numero de Cuota: <%=request.getAttribute("NroCuotaElegido") %><br><br>
			Elegir cuenta con la cual pagar:
			
			<% 
					Usuario Usu = (Usuario)session.getAttribute("UsuarioConectado");				
					ArrayList<Cuenta> Lista = new ServletCuenta().CuentasXCUIL(Usu.getNombreU());
					ArrayList<TipoDeCuenta> TiposCuentas = new ServletCuenta().TiposDeCuentas();
					%>
			<select name="ddlCuenta" class="form-control" required>
				<option selected disabled value="">Seleccionar Cuenta</option>
				<%for(Cuenta reg : Lista){ %>
				<option value="<%=reg.getCBU()%>"><%=reg.getCBU()%>, <%for(TipoDeCuenta TC:TiposCuentas){ if(reg.getIdTipoCuenta()==TC.getId()){%><%=TC.getDescripcion()%><%}}%>, $<%=reg.getSaldo() %></option>
				<%} %>
			</select>
			<input type="hidden" value="<%=request.getAttribute("NroCuotaElegido")%>" name="NroCuota">
			<input type="hidden" value="<%=request.getAttribute("IdPrestamoElegido")%>" name="IdPrestamo">
			<input type="hidden" value="<%=request.getAttribute("MontoElegido")%>" name="Monto">
			<br>
			<input type="submit" name="ConfirmarPagoCuota" value="Confirmar" class="btn btn-info">
		</form>
		<%} %>
		
		
	<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"><%if(request.getAttribute("Exito")!=null) {%> Exito <%} else {%> Error <%} %></h5>
	      </div>
	      <div class="modal-body">
	        <%=request.getAttribute("Mensaje")%>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	
	<%if(request.getAttribute("Mensaje")!=null){ %>
	<script>
	$(document).ready(function(){
	  $("#myModal").modal("show");
	});
	</script>
	<%} %>
	
</body>
</html>
