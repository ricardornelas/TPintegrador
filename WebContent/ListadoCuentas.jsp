<%@page import="Entidad.Cuenta"%>
<%@page import="Controlador.ServletCuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Cliente"%>
<%@page import="Controlador.ServletCliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style>
	.th,td,tr{
	text-align: center;
	};
	</style>
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
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	
	<title>Listado de Cuentas</title>
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
  				  <a class="dropdown-item" href="Login.jsp">Cerrar Sesi�n</a>
 			 </div>
		</div>
	</nav>
	
	<br>
	
	<% ArrayList<Cuenta> listaCuentas = new ServletCuenta().ListadoCuentas(); %>

	<table id="tabla" class="display">
		<thead>
			<tr> 
				<th>CBU</th>
				<th>CUIL</th>
				<th>Tipo de Cuenta</th>
				<th>Numero de Cuenta</th>
				<th>Fecha de Creacion</th>
				<th>Saldo</th>
			</tr>
		</thead>
		<tbody>
			<% for(Cuenta reg:listaCuentas){ %>
			<tr>
				<td><%=reg.getCBU()%></td>
				<td><%=reg.getCUIL()%></td>
				<td><%if(reg.getIdTipoCuenta()==1){%>
				Caja de Ahorro
				<%} if(reg.getIdTipoCuenta()==2){%>
				Cuenta Corriente
				<%}%>
				</td>
				<td><%=reg.getNroCuenta()%></td>
				<td><%=reg.getFechaCreacion()%></td>
				<td><%=reg.getSaldo()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>



</body>
</html>