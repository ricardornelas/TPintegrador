<%@page import="NegocioImpl.ProvinciaNegocioImpl"%>
<%@page import="Entidad.Provincia"%>
<%@page import="NegocioImpl.LocalidadNegocioImpl"%>
<%@page import="Entidad.Localidad"%>
<%@page import="Controlador.ServletCliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">		
			<title>Registrar Cliente</title>
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
	<div class="card">
		<div class="card-body">
			<form action = "ServletCliente" method ="get">
		
		<h1>NUEVO CLIENTE</h1>
			<%
			HttpSession sessionAl = request.getSession();
		if (sessionAl.getAttribute("RespuestaSql") != null) {
			if ((boolean) sessionAl.getAttribute("RespuestaSql")) {
		%>
		<div class="alert alert-dismissible alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			Cliente agregado con exito
		</div>
		<%
			} else {
		%>
		<div class="alert alert-dismissible alert-primary">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			Error al agregar cliente
		</div>

		<%
			}

		sessionAl.removeAttribute("RespuestaSql");
		}
		%>
				<div class="form-group">
					<label>Nombre:</label>
					<input type="text" name="txtNombre" required class="form-control">
				</div>
				<div class="form-group">
					<label>Apellido:</label>
					<input type="text" name="txtApellido" required class="form-control">
				</div>
				<div class="form-group">
					<label>DNI:</label>
					<input type="number" name="txtDni" required class="form-control">
				</div>
								<div class="form-group">
					<label>Cuil:</label>
					<input type="text" name="txtCuil" required class="form-control">
				</div>
				<div class="form-check form-check-inline">
				<label>SEXO:     </label>
 				 <input class="form-check-input" type="radio" name="RadioGenero" required value="0">
 				 <label class="form-check-label" for="inlineRadio1">Masculino</label>
				</div>
				<div class="form-check form-check-inline">
			
 				 <input class="form-check-input" type="radio" name="RadioGenero"  required value="1">
  				<label class="form-check-label" for="inlineRadio2">Femenino</label>
				</div>
				<br>
				<label for="start">Fecha de nacimiento :</label>
				<br>
				<input type="date" name="FechaNac" min="1901-01-01" max="2018-12-31" required >
				
				<div class="form-group">
					<label>Nacionalidad:</label>
					<input type="text" name="txtNacionalidad" required class="form-control">
				</div>
				<div class="form-group">
      			<label for="inputState">Provincia</label>
    				 <select name="ddlProvincia" onchange="window.location.href='ServletCliente?ddlProvincia='+this.value" required class="form-control">
        				
        				<option selected disabled value =0 >Seleccionar provincia</option>
        				
       					<% ServletCliente controlador = new ServletCliente();
        				
      						for(Provincia aux : controlador.Provincia()){
      					%>
      					   
	      					<option value=<%=aux.getIdprovincia()%>><%=aux.getNombre()%></option>
      					<%
      						}
      					%>
      				</select>
      				</div>
      				
				<div class="form-group">
					<label>Direccion:</label>
					<input type="text" name="txtDireccion"  class="form-control">
				</div>
				
				<div class="form-group">
      			<label for="inputState">Localidad</label>
    				 <select name="ddlLocalidad"  required class="form-control">
        				<option selected disabled>Seleccionar localidad</option>
       					 
       					 <% if(request.getParameter("ddlProvincia")!=null){
       					 
       					   ServletCliente controlador2 = new ServletCliente();
        				
      						for(Localidad aux : controlador2.Localidad(Integer.parseInt(request.getParameter("ddlProvincia")))){
      					   %>
	      					<option value=<%=aux.getIdLocalidad()%>><%=aux.getNombre()%></option>
      					<%
      						}
       					 }
      					%>
      				</select>
				<div class="form-group">
					<label>Telefono:</label>
					<input type="number" name="txtTelefono" class="form-control">
				</div>
				<div class="form-group">
					<label>Nombre de usuario:</label>
					<input type="text" name="txtUsuario"  required  class="form-control" >
				</div>
				<div class="form-group">
					<label>Correo electronico:</label>
					<input type="email" name="txtEmail" required class="form-control">
				</div>
			<div class="form-group">
					<label>Contraseña:</label>
					<input type="password" name="txtPassword" required class="form-control">
				</div>
												<div class="form-group">
					<label>Repetir Contraseña:</label>
					<input type="password" name="txtRepPassword" required class="form-control">
				</div>
				
				<br><br>
				<input type = "submit" name="btnAgregar" value="Agregar" class="btn btn-info">
						
			</form>
	
		
		</div>
		
	</div>
	

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
