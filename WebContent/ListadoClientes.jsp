<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Cliente"%>
<%@page import="Entidad.Provincia"%>
<%@page import="Controlador.ServletCliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	
	<title>Listado de Clientes</title>
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
	
	<%
	
	ArrayList<Cliente> listaClientes =new ArrayList<Cliente>();
	if(request.getAttribute("Listado")==null){
	listaClientes = new ServletCliente().ListadoClientes();
	} else{
		listaClientes = (ArrayList<Cliente>)request.getAttribute("Listado");
	}
%>

	


<select name="ddlFiltroProvincia" class="form-control">
<option selected disabled value => Filtrar Provincia</option>
<% ServletCliente controlador = new ServletCliente();
        				
      	for(Provincia aux : controlador.Provincia()){
      		System.out.println(aux.getIdprovincia());
     %>
      					   
    <option value="<%=aux.getIdprovincia()%>" onclick="window.location.href='ServletCliente?FiltrarProvincia=1&ddlFiltroProvincia=<%=aux.getIdprovincia()%>'" ><%=aux.getNombre()%> </option>
<%
 }
%>

</select>
	 
<br>

	<table id="tabla" class="display">
		<thead>
			<tr> 
				<th>Cuil</th>
				<th>Dni</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Sexo</th>
				<th>Nacionalidad</th>
				<th>Fecha de Nacimiento</th>
				<th>Provincia</th>
				<th>Localidad</th>
				<th>Direccion</th>
				<th>Correo</th>
				<th>Telefono</th>
				<th>Usuario</th>
			</tr>
		</thead>
				<tbody>
			<% 
			for(Cliente reg:listaClientes){
				
				%>
			<tr>
				<td><%=reg.getCuil() %></td>
				<td><%=reg.getDni() %></td>
				<td><%=reg.getNombre() %></td>
				<td><%=reg.getApellido() %></td>
				<td><%if(reg.getSexo()==0){%>Masculino<%} else {%>Femenino<%} %></td>
				<td><%=reg.getNacionalidad() %></td>
				<td><%=reg.getFecha() %></td>
				<td><%=new ServletCliente().NombreProvincia(reg.getProvincia())%></td>
				<td><%=new ServletCliente().NombreLocalidad(reg.getLocalidad()) %></td>
				<td><%=reg.getDireccion() %></td>
				<td><%=reg.getCorreo() %></td>
				<td><%=reg.getTelefono() %></td>
				<td><%=reg.getUsuario() %> </td>
			
			</tr>
			<%} %>
		</tbody>

	</table>



</body>
</html>