<%@page import="Entidad.Cliente"%>
<%@page import="Controlador.ServletCliente"%>
<%@page import="Entidad.TipoDeCuenta"%>
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
 		<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />


<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 
	<script type="text/javascript">
	$(document).ready( function () {
	    $('#tabla').DataTable();
	} );
	</script>	
	<script type="text/javascript">
$(document).ready(function() {
    $('.ddlCuil').select2();
});
</script>	
		<title>Modificar Cuenta</title>
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
	
			<%ArrayList<Cuenta>	listaCuentas = new ServletCuenta().ListadoCuentas();%>

			
	<table id="tabla" class="display">
		<thead>
			<tr> 
				<th>CBU</th>
				<th>Cuil</th>
				<th>Tipo de Cuenta</th>
				<th>Numero de cuenta</th>
				<th>Fecha de Creacion</th>
				<th>Saldo</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<% for(Cuenta reg:listaCuentas){ %>
			<tr> 
				<td><%=reg.getCBU() %></td>
				<td><%=reg.getCUIL() %></td>
				<td>
				<%if(reg.getIdTipoCuenta()==1){ %>
				Caja de Ahorro
				<%} else {%>
				Cuenta Corriente
				<%} %>
				</td>
				<td><%=reg.getNroCuenta() %></td>
				<td><%=reg.getFechaCreacion() %></td>
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
					<label>Cliente (CUIL,Nombre,Apellido):</label> 
					<select class="ddlCuil" name="ddlCuil" name="state">
					<%ArrayList<Cliente> listaClientes = new ServletCliente().ListadoClientes(); 
						for(Cliente reg:listaClientes){
					%>
					<option value="<%=reg.getCuil()%>" <% if(reg.getCuil().compareTo(aux.getCUIL())==0){%>selected<%} %>><%=reg.getCuil()%>, <%=reg.getNombre()%>, <%=reg.getApellido() %></option>
					
					<%	} %>
					</select><br>

					<label>Id Tipo Cuenta:</label><select name="ddlTipoCuenta" required>
        					<option value="" selected disabled>Seleccionar Tipo de Cuenta</option>

        				<% ServletCuenta controlador = new ServletCuenta();
        				
      						for(TipoDeCuenta reg : controlador.TiposDeCuentas()){
      					%>
	      					<option value=<%=reg.getId()%> <% if(reg.getId()==aux.getIdTipoCuenta()){%>selected<%} %>><%=reg.getDescripcion()%></option>
      					<%
      						}
      					%>
      				</select>
      				<br>
					<label>Numero de Cuenta: <%=aux.getNroCuenta()%></label><br>
					<label>Saldo:</label><input type="number" name="Saldo" required value="<%=aux.getSaldo()%>"><br><br>
					<br>
					<input type="submit" name="btnConfirmarModificar" class="btn btn-info" value = "Confirmar">
					</form>
					
				</div>	

<%
listaCuentas = null;} %>

<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel"><%if(request.getAttribute("Exito")!=null){ %>Exito<%} else { %>Error <%} %></h5>
				      </div>
				      <div class="modal-body">
				        <%=request.getAttribute("Mensaje") %>
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
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<%if(request.getAttribute("Mensaje")!=null){ %>
<script>
$(document).ready(function(){
  $("#myModal").modal("show");
});
</script>
<%} %>

</body>
</html>
