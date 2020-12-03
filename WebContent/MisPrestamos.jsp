<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Cuenta"%>
<%@page import="Entidad.Prestamos"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
table,th,td{
	border: 1px solid black;
}
</style>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">		
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
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="Transferencia.jsp">Transferencias</a>
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
<form action = "ServletPrestamos" method ="get">
<H1>Prestamos</H1>
<br>
<table style="width: 100%">
	<thead style="text-align: center;">
		<tr>
			<th>Codigo</th>
			<th>CBU de cuenta</th>
			<th>Importe total a Pagar</th>
			<th>Importe Pedido</th>
			<th>Monto por Mes</th>
			<th>Cantidad de Cuotas</th>
			<th>Cuotas Pagadas</th>
			<th>Cuotas Faltantes</th>
		</tr>
	</thead>
	<%
					ArrayList<Prestamos> listaPres = null;
					if (request.getAttribute("listaPrestamos") != null) {
						listaPres = (ArrayList<Prestamos>) request.getAttribute("listaPrestamos");
					}
				%>
				<%
							if (listaPres != null)
								for (Prestamos pres : listaPres) {
						 	%>
	<tr>
		<td name="nroPrestamo"><%= pres.getIdPrestamo()%> </th>
		<td name="CBU"><%=pres.getCbu() %></td>
		<td name="Totaldeprestamo">$<%= pres.getImpPedido() %> </td>
		<td name="ImportePagar">$<%= pres.getImpAPagar()%> </td>
		<td name="montoMensual">$<%=pres.getMontoXMes%>></td>
		<td name="Cuotas"><% pres.getCuotas() %> </td>
		<td name="CuotasPagadas"><% pres%> </td>
		<td namer="CuotasRestantes"><% %> </td>
		<td <div style="text-align: center; border: none"><a class="btn btn-info" href="PagoDeCuotas.jsp" role="button">Seleccionar</a></div>
></td>
		
	</tr>
		
</table>
<br>
<div style="text-align: center;"><a class="btn btn-info" href="SolicitarPrestamo.jsp" role="button">Solicitar Nuevo Prestamo</a></div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>