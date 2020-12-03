<%@page import="Entidad.Usuario"%>
<%@page import="Entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controlador.ServletCuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
table,td,th{
	border: 1px solid black;
}
tr{
text-align: center;
}
</style>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">		
		<title>Cuentas Bancarias</title>
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
		<h1>Cuentas bancarias</h1>
		<br>
		<%ArrayList<Cuenta> Lista = new ServletCuenta().CuentasXCUIL(((Usuario)session.getAttribute("UsuarioConectado")).getNombreU()); %>
		<table style="width:90%">
		<thead>
			<tr>
				<th>Cuenta Nro</th>
				<th>CBU</th>
				<th>Tipo de Cuenta</th>
				<th>Fecha de Creacion</th>
				<th>Saldo</th>
			</tr>
		</thead>
		<%for(Cuenta reg:Lista){int i=1; %>
			<form action="ServletCuenta" method="post">
		<tr>
					<td><%=i%></td>
					<td><%=reg.getCBU() %><input type="hidden" name="CBU" value="<%=reg.getCBU()%>"></td>
					<td>
					<%if(reg.getIdTipoCuenta()==1){ %>
					Caja de Ahorro
					<%} else {%>
					Cuenta Corriente
					<%} %>
					</td>
					<td><%=reg.getFechaCreacion() %></td>
					<td>$<%=reg.getSaldo() %></td>
					<td style="width:1px"><input type="submit" name="Historial" value="Ver Historial de Movimientos"></td>
			</tr>
			</form>
			<%i++;} %>
		</table>
		
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
