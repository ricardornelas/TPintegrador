<%@page import="Controlador.ServletCuenta"%>
<%@page import="Entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
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
		<title>Insert title here</title>
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
			<%!ArrayList <Cuenta> listaCuentas;%>
			<% listaCuentas = new ServletCuenta().ListadoCuentas(); %>
			<br>
			<H1>Eliminar Cuenta</H1>
			<br>
			<table id="tabla" class="display">
				<thead>
					<tr>
						<th>CBU</th>
						<th>CUIL</th>
						<th>Id Tipo Cuenta</th>
						<th>Numero Cuenta</th>
						<th>Fecha de Creacion</th>
						<th>Saldo</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<form action="ServletCuenta" method="Post">
					<%for(Cuenta reg:listaCuentas){ %>
					<tr>
						<td><%= reg.getCBU()%></td>
						<td><%= reg.getCUIL()%></td>
						<td><%= reg.getIdTipoCuenta()%></td>
						<td><%= reg.getNroCuenta()%></td>
						<td><%= reg.getFechaCreacion()%></td>
						<td><%= reg.getSaldo()%></td>
						<td><input type="submit" name="btnEliminar" value="Eliminar" onclick="window.location.href='ServletCuenta?btnEliminar=1&CBU=<%= reg.getCBU()%>'"></td>
					</tr>
					<%} %>
					</form>
				</tbody>
			</table>			
			
<p>	
<%if(request.getAttribute("CuentaSeleccionada")!=null){%>
	<h1>Estas seguro que deseas eliminar la cuenta con el CBU: <%=(String)request.getAttribute("CuentaSeleccionada")%></h1><br>
	<form action="ServletCuenta" method="post">
	<input type="hidden" name="CBU" value="<%=(String)request.getAttribute("CuentaSeleccionada")%>">
	<div style="text-align: center"><input style="width: 20%" type="submit" name="btnAceptarEliminar" value="Aceptar"> <input style="width: 20%"type="submit" name="btnCancelarEliminar" value="Cancelar"></div>
	</form>
<%} listaCuentas=null;%>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
</body>
</html>