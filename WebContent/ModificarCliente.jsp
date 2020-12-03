<%@page import="Entidad.Localidad"%>
<%@page import="Entidad.Provincia"%>
<%@page import="Entidad.Usuario"%>
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
			<h1>Modificar Cliente</h1>
			<br>
					
			<%ArrayList<Cliente> listaClientes = new ServletCliente().ListadoClientes(); %>
			
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
			<th></th>
		</tr>
	</thead>
	<tbody>
		<% for(Cliente reg:listaClientes){ %>
		<tr> 
			<td><%=reg.getCuil() %><input type="hidden" name="Cuil" value=<%=reg.getCuil() %>></td>
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

			<td style="width: 1px"><input type="submit" name="btnModificar" value="Cambiar Contraseña" onclick="window.location.href='ServletCliente?btnModificar=1&Usuario=<%=reg.getUsuario()%>'"></td>
		
		</tr>
		<%} %>
	</tbody>

</table>
<p><p>		
<% if(request.getAttribute("ClienteSeleccionado")!=null&&request.getAttribute("UsuarioSeleccionado")!=null){
	Cliente aux = (Cliente)request.getAttribute("ClienteSeleccionado");
	Usuario usu = (Usuario)request.getAttribute("UsuarioSeleccionado");
%>
			<div class="form-group">
				<form action="ServletCliente" method="post">
					Nombre: <input type="text" name="txtNombre" value="<%=aux.getNombre()%>" required><br>
					Apellido: <input type="text" name="txtApellido" value="<%=aux.getApellido()%>" required><br>
					Sexo: 
					<div class="form-check form-check-inline">
		 				<input class="form-check-input" type="radio" name="RadioGenero" required value="0" <%if(aux.getSexo()==0){%>checked<%}%>>
		 				<label class="form-check-label" for="inlineRadio1" >Masculino</label>
		 				<input class="form-check-input" type="radio" name="RadioGenero" required value="1" <%if(aux.getSexo()==1){%>checked<%}%>>
		  				<label class="form-check-label" for="inlineRadio2">Femenino</label>
					</div><br>
					Nacionalidad: <input type="text" name="txtNacionalidad" value="<%=aux.getNacionalidad()%>" required><br>
					Fecha de Nacimiento: <input type="date" name="FechaNac" min="1901-01-01" max="2018-12-31" required value="<%=aux.getFecha()%>"><br>
					Provincia:
					<SELECT name="ddlProvincia">
						<% ServletCliente controlador = new ServletCliente();
        				
      						for(Provincia prov : controlador.Provincia()){
      					%>
      					   
	      					<option value=<%=prov.getIdprovincia()%> <%if(prov.getIdprovincia()==aux.getProvincia()){%>selected<%}%>><%=prov.getNombre()%></option>
      					<%
      						}
      					%>
					</SELECT><br>
					Localidad: 
					<SELECT name="ddlLocalidad">
						<% for(Localidad loc : controlador.Localidad()){ %>
      					   
	      					<option value=<%=loc.getIdLocalidad()%> <%if(loc.getIdLocalidad()==aux.getLocalidad()){%>selected<%}%>><%=loc.getNombre()%></option>
      					<%
      						}
      					%>
					</SELECT><br>
					Direccion: <input type="text" name="txtDireccion" value="<%=aux.getDireccion()%>" required><br>
					Correo: <input type="text" name="txtCorreo" value="<%=aux.getCorreo()%>" required><br>
					Telefono: <input type="number" name="txtTelefono" value="<%=aux.getTelefono()%>" required><br>
					Contraseña: <input type="password" name="txtPassword" value="<%=usu.getContraseña()%>" required><br>
					<input type="hidden" name="txtDni" value="<%=aux.getDni()%>">
					<input type="hidden" name="txtCuil" value="<%=aux.getCuil()%>">
					<input type="hidden" name="txtUsuario" value="<%=aux.getUsuario()%>">
					<input type="submit" name="btnConfirmarModificar" class="btn btn-info" value = "Confirmar">
					
				</form>
					
			</div>	
<%} %>

				<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel"><%if(request.getAttribute("Exito")!=null){ %>Exito<%} else { %>Error <%} %></h5>
				      </div>
				      <div class="modal-body">
				        <%if(request.getAttribute("Mensajes")!=null)for(String Mensaje: (ArrayList<String>)request.getAttribute("Mensajes")){%>
				        <%=Mensaje %>
				        <%} %>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				      </div>
				    </div>
				  </div>
				</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<%if(request.getAttribute("Mensajes")!=null){ %>
<script>
$(document).ready(function(){
  $("#myModal").modal("show");
});
</script>
<%} %>
</body>
</html>
