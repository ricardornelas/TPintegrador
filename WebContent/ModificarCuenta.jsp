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
			<br>
			<h1>Modificar Cuenta</h1>
			<br>
<div class="form-group">
					<form action="ServletCuenta" method="Post">
					<label>Filtrar por Cuil:</label>
					<input type="text" name="txtCuil" class="form-control">
					<input type="submit" name="btnFiltrarModificar" value="Filtrar" class="btn btn-info">
				</div>		
			<%!ArrayList <Cuenta> listaCuentas;%>
			<%if(request.getAttribute("lista")!=null){
				listaCuentas = (ArrayList)request.getAttribute("lista"); 
			} else if(listaCuentas==null){
				listaCuentas = new ServletCuenta().ListadoCuentas();
			} %>
			
			<table border="1" style="width: 393px; height: 47px">
<tr> <th><CENTER>Cuil</CENTER></th><th><CENTER>CBU</CENTER></th><th><CENTER>Nro de cuenta</CENTER></th><th><CENTER>Saldo</CENTER></th><th></th>
	<% for(Cuenta reg:listaCuentas){ %>
<tr> 
	<form action="ServletCliente" method="post">
		<td><%=reg.getCUIL() %><input type="hidden" name="CUIL" value=<%=reg.getCUIL() %>></td>
		<td><%=reg.getCBU() %><input type="hidden" name="CBU" value=<%=reg.getCBU() %>></td>
		<td><%=reg.getNroCuenta() %><input type="hidden" name="NroCuenta" value=<%=reg.getNroCuenta() %>></td>
		<td><%=reg.getSaldo() %><input type="hidden" name="Saldo" value=<%=reg.getSaldo() %>> </td>
		<td> <CENTER> <input type="submit" name="btnModificar" value="Modificar Cuenta"> </CENTER></td>
</form>
</tr>
<%} %>

</table>
		</form>	
<p><p>		
<% if(request.getAttribute("ClienteSeleccionado")!=null){
	Cuenta  aux = (Cuenta)request.getAttribute("CuentaSeleccionado");
%>
<label>Cuenta: <%=aux.getCUIL() + ", " + aux.getCBU() +"  "+ aux.getNroCuenta() + ", " + aux.getSaldo()%></label>				

<div class="form-group">
					<label>Nueva CBU:</label>
					<form action="ServletCliente" method="post">
					<input type="text" name="txtCBU" class="form-control">
					<input type="hidden" name="Usu" value=<%=aux.getCUIL() %>>
					<p>
					
					<label>Nro de cuenta:</label>
					<input type="text" name="txtNroCuenta" class="form-control">
					<br>
					<input type="submit" name="btnConfirmarModificar" class="btn btn-info" value = "Confirmar">
					</form>
					
				</div>	
<%} %>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
