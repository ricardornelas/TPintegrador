<%@page import="Entidad.TipoMovimiento"%>
<%@page import="Controlador.ServletCuenta"%>
<%@page import="Entidad.Movimiento"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
table,th,td{
	text-align: center;
}
</style>
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
		<title>Insert title here</title>
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
	<H1>Historial de movimientos</H1>
<br>
<%
String CBU = (String)request.getAttribute("CBU");
ArrayList<Movimiento> Lista = new ServletCuenta().ListadoMovimientosXCBU(CBU);
ArrayList<TipoMovimiento> TM = new ServletCuenta().ListadoTiposMovimiento();
%>
<table id="tabla" class="display">
	<thead>
		<tr style="text-align: center;">
			<th>Codigo</th>
			<th>Tipo Transferencia</th>
			<th>CBU Remitente</th>
			<th>CBU Destinatario</th>
			<th>Fecha</th>
			<th>Monto</th>
		</tr>
	</thead>
	<tbody>
	<%for(Movimiento mov : Lista){ %>
		<tr>	
			<td><%=mov.getCodigo_Mov() %></td>
			<td><% for(TipoMovimiento reg : TM){ if(reg.getIdTipoMovimiento_TM()==mov.getIdTipoMovimiento_Mov()){%><%=reg.getDescripcion_TM() %> <%}} %></td>
			<td><%if(mov.getCBURemitente_Mov()==null){%> - <%} else {%><%=mov.getCBURemitente_Mov() %><%} %> </td>
			<td><%if(mov.getCBUDestinatario_Mov()==null){%> - <%} else {%><%=mov.getCBUDestinatario_Mov() %><%} %></td>
			<td><%=mov.getFecha() %> </td>
			<td> $ <%=mov.getMonto_Mov() %></td>
		</tr>
	<%} %>
	</tbody> 
</table>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
</body>
</html>