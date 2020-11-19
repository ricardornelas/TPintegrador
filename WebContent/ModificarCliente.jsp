<%@page import="Controlador.ServletCliente"%>
<%@page import="Entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
td,th,tr{
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
			<h1>Modificar Cliente</h1>
			<br>
<div class="form-group">
					<form action="ServletCliente" method="Post">
					<label>Filtrar por Cuil:</label>
					<input type="text" name="txtCuil" class="form-control">
					<input type="submit" name="btnFiltrarModificar" value="Filtrar" class="btn btn-info">
				</div>		
			<%!ArrayList <Cliente> listaClientes;%>
			<%if(request.getAttribute("listado")!=null){
				listaClientes = (ArrayList)request.getAttribute("listado"); 
			} else if(listaClientes==null){
				listaClientes = new ServletCliente().ListadoClientes();
			} %>
			
<table id="tabla" class="display">
	<thead>
		<tr> 
			<th>Cuil</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Usuario</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<% for(Cliente reg:listaClientes){ %>
		<tr> 
			<td><%=reg.getCuil() %><input type="hidden" name="Cuil" value=<%=reg.getCuil() %>></td>
			<td><%=reg.getNombre() %><input type="hidden" name="Nombre" value=<%=reg.getNombre() %>></td>
			<td><%=reg.getApellido() %><input type="hidden" name="Apellido" value=<%=reg.getApellido() %>></td>
			<td><%=reg.getUsuario() %><input type="hidden" name="Usuario" value=<%=reg.getUsuario() %>> </td>
			<td style="width: 1px"><input type="submit" name="btnModificar" value="Cambiar Contraseña" onclick="window.location.href='ServletCliente?btnModificar=1&Cuil=<%=reg.getCuil()%>&Nombre=<%=reg.getNombre()%>&Apellido=<%=reg.getApellido()%>&Usuario=<%=reg.getUsuario()%>'"></td>
		
		</tr>
		<%} %>
	</tbody>

</table>
		</form>	
<p><p>		
<% if(request.getAttribute("ClienteSeleccionado")!=null){
	Cliente aux = (Cliente)request.getAttribute("ClienteSeleccionado");
%>
<label>Cliente: <%=aux.getCuil() + ", " + aux.getNombre() +"  "+ aux.getApellido() + ", " + aux.getUsuario()%></label>				

<div class="form-group">
					<label>Nueva Contraseña:</label>
					<form action="ServletCliente" method="post">
					<input type="password" name="txtPassword" class="form-control">
					<input type="hidden" name="Usu" value=<%=aux.getUsuario() %>>
					<p>
					
					<label>Repetir Contraseña:</label>
					<input type="password" name="txtRepPassword" class="form-control">
					<br>
					<input type="submit" name="btnConfirmarModificar" class="btn btn-info" value = "Confirmar">
					
					</form>
					
				</div>	
<%} %>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
</body>
</html>
