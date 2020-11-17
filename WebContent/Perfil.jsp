<%@page import="java.sql.ResultSet"%>
<%@page import="Entidad.Cliente"%>
<%@page import="Entidad.Usuario"%>
<%@page import="Controlador.ServletUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<style>
td{
	padding-bottom: 25px;
}
</style>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">		
		<title>Perfil</title>
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
			<form action="ServletUsuario" method="post">
			
			<% 
			Usuario Usu = (Usuario)session.getAttribute("UsuarioConectado");
			ResultSet rs = new ServletUsuario().DevolverCliente(Usu);
			rs.next();%>
			
			<h1>
			<br><b>DATOS DEL PERFIL</b><br><br>
			</h1>
			
			<table style="line-height: normal; width:80%">
			<tr>
				<td>CUIL: <%=rs.getString(1)%></td>
				<td>Usuario: <%=rs.getString(12)%> </td>
			</tr>
			<tr>
				<td>Nombre: <%=rs.getString(3)%></td>
				<td>Apellido: <%=rs.getString(4)%></td>
				<td>Sexo: <%if(rs.getInt(5)==0){%>
				Masculino
				<%} else {%>
				Femenino
				<%} %>
				 </td>
			</tr>
			<tr>
				<td>Nacionalidad: <%=rs.getString(6)%></td>
				<td>Fecha de Nacimiento: <%=rs.getString(7) %></td>
			</tr>
			<tr>
				<td>Provincia: <%=rs.getString(10)%></td>
				<td>Localidad: <%=rs.getString(9)%></td>
				<td>Direccion: <%=rs.getString(8)%></td>
			</tr>
			<tr>
				<td>Correo: <%=rs.getString(11)%></td>
				<td>Telefono: <%=rs.getString(13) %></td>
			</tr>
		</table>
		<br>	
				<br>
				<br>
				<br>
			</form>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
