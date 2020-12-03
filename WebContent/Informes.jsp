<%@page import="Controlador.ServletCuenta"%>
    <%@page import="Entidad.Provincia" %>
    <%@page import="java.util.ArrayList"%>
    <%@page import="Controlador.ServletCliente"%>
    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
table{
	width: 60%;
}
tr{
text-align: center;
}
	table,th,td{
		border: 1px solid black;
	}
</style>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">		
		<title>Informes y Estadisticas</title>
	</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-info">
  		<div class="collapse navbar-collapse" id="navbarNav">
   			 <ul class="navbar-nav">
     		 <li class="nav-item active">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="MenuClientes.jsp">Clientes </a>
     			 </li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="AgregarAdministrador.jsp">Crear Nuevo Administrador</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="PrestamosSolicitados.jsp">Prestamos Solicitados</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="Informes.jsp">Informes</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="MenuCuentas.jsp">Cuentas</a>
      			</li>
    		</ul>
  		</div>
  		 <div class="dropdown">
  			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  				  Administrador
 			 </button>
  			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
  				  <a class="dropdown-item" href="Login.jsp">Cerrar Sesión</a>
 			 </div>
		</div>
	</nav>
			<form>
			
			<br><br>

			<H1><b>Informes y Estadisticas</b></H1>
			
			<br><br>
			
			<h3>Cantidad de Cajas de Ahorro y Cuentas Corrientes</h3>
			<%
				int CajasAhorro = new ServletCuenta().CantidadCajasDeAhorro(), CuentasCorriente = new ServletCuenta().CantidadCuentasCorrientes();
				int total = CajasAhorro + CuentasCorriente;
				
			%>
			<table>
				<thead>
					<tr>
						<th style="width:90px;"></th>
						<th>Cajas de Ahorro</th>
						<th>Cuentas Corrientes</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><b>Cantidad</b></td>
						<td><%=CajasAhorro%></td>
						<td><%=CuentasCorriente%></td>
					</tr>
					<tr>
						<td><b>Porcentaje</b></td>
						<td><%=((float)CajasAhorro/total)*100%> %</td>
						<td><%=((float)CuentasCorriente/total)*100%> %</td>
					</tr>
				</tbody>
			</table>
			
			<br><br><br>
			
			<%ArrayList<Provincia> lista = new ServletCliente().Provincia(); 
			int Total= new ServletCliente().ClientesTotales();
			int i;
			%>
	
			
			<h3>Cantidad de Clientes por Provincia</h3>
			<table>
			<thead>
				<tr>
					<th>Provincia</th>
					<th>Cantidad de Clientes</th>
					<th>Porcentaje</th>
				</tr>
			</thead>
			<%for(Provincia reg:lista){ %>
				<tr>
					<td><%=reg.getNombre() %></td>
					<td><%= i= new ServletCliente().ClientesPorProvincia(reg.getIdprovincia()) %></td>
					<td><%=((float)i/Total)*100%> %</td>
				</tr>
				<%}%>
			</table>
		
</form>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
