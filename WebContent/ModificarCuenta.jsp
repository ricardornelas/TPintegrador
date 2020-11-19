<%@page import="Controlador.ServletCuenta"%>
<%@page import="Entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				listaCuentas = null;
				listaCuentas = new ServletCuenta().ListadoCuentas();
			} %>
			</form>
			
	<table id="tabla" class="display">
		<thead>
			<tr> 
				<th>CBU</th>
				<th>Cuil</th>
				<th>IdTipoCuenta</th>
				<th>Nro de cuenta</th>
				<th>Saldo</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<% for(Cuenta reg:listaCuentas){ %>
			<tr> 
				<td><%=reg.getCBU() %></td>
				<td><%=reg.getCUIL() %></td>
				<td><%=reg.getIdTipoCuenta() %></td>
				<td><%=reg.getNroCuenta() %></td>
				<td><%=reg.getSaldo() %></td>
				<td><input type="submit" name="btnModificar" value="Modificar Cuenta" onclick="window.location.href='ServletCuenta?btnModificar=1&CBU=<%=reg.getCBU()%>&Cuil=<%=reg.getCUIL()%>&IdTipoCuenta=<%=reg.getIdTipoCuenta()%>&NroCuenta=<%=reg.getNroCuenta()%>&Saldo=<%=reg.getSaldo()%>'"></td>
			</tr>
			<%}%>
		</tbody>
</table>
<p><p>		
<% if(request.getAttribute("CuentaSeleccionada")!=null){

	Cuenta  aux = (Cuenta)request.getAttribute("CuentaSeleccionada");
%>	
	<div class="form-group">
					<h1>Datos Anteriores: </h1><br>
					<label>CBU: <%=aux.getCBU()%></label><br>
					<label>CUIL: <%=aux.getCUIL()%></label><br>
					<label>Id Tipo Cuenta: <%=aux.getIdTipoCuenta()%></label><br>
					<label>Numero de Cuenta: <%=aux.getNroCuenta()%></label><br>
					<label>Saldo: <%=aux.getSaldo()%></label><br><br>
					
					<form action="ServletCuenta" method="post">
					<h1>Datos Nuevos: </h1><br>
					<label>CBU: <%=aux.getCBU()%></label><input type="hidden" name="CBU" value="<%=aux.getCBU()%>"><br>
					<label>CUIL:</label> <input type="number" name="CUIL" required value="<%=aux.getCUIL()%>"><br>
					<label>Id Tipo Cuenta:</label><input type="number" name="IdTipoCuenta" required value="<%=aux.getIdTipoCuenta()%>"><br>
					<label>Numero de Cuenta: <%=aux.getNroCuenta()%></label><br>
					<label>Saldo:</label><input type="number" name="Saldo" required value="<%=aux.getSaldo()%>"><br><br>
					<br>
					<input type="submit" name="btnConfirmarModificar" class="btn btn-info" value = "Confirmar">
					</form>
					
				</div>	

<%
listaCuentas = null;} %>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
</body>
</html>
