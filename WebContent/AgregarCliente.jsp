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
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaEmpleado.jsp">Home </a>
     			 </li>
     			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="AgregarCliente.jsp">Agregar Usuario</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="AgregarCuenta.jsp">Agregar Cuenta</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaSolicitudPrestamo.jsp">Prestamos Solicitados</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ModificarUsuario.jsp">Modificiar Usuario</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaInformes.jsp">Informes</a>
      			</li>
    		</ul>
  		</div>
  		 <div class="dropdown">
  			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  				  Iniciar Sesion
 			 </button>
  			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
  				  <a class="dropdown-item" href="#">user</a>
  				  <a class="dropdown-item" href="#">mail</a>
  				  <a class="dropdown-item" href="#">Something else here</a>
 			 </div>
		</div>
	</nav>
	<div class="card">
		<div class="card-body">
			<form >
		
		<h1>NUEVO CLIENTE</h1>
			
				<div class="form-group">
					<label>Nombre:</label>
					<input type="text" name="txtNombre" class="form-control">
				</div>
				<div class="form-group">
					<label>Apellido:</label>
					<input type="text" name="txtApellido" class="form-control">
				</div>
				<div class="form-group">
					<label>DNI:</label>
					<input type="text" name="txtDni" class="form-control">
				</div>
								<div class="form-group">
					<label>Cuil:</label>
					<input type="text" name="txtCuil" class="form-control">
				</div>
				<div class="form-check form-check-inline">
				<label>SEXO:     </label>
 				 <input class="form-check-input" type="radio" name="RadioGenero" value="Masculino">
 				 <label class="form-check-label" for="inlineRadio1">Masculino</label>
				</div>
				<div class="form-check form-check-inline">
			
 				 <input class="form-check-input" type="radio" name="RadioGenero"  value="Femenino">
  				<label class="form-check-label" for="inlineRadio2">Femenino</label>
				</div>
				<br>
				<label for="start">Fecha de nacimiento :</label>
				<br>
				<input type="date" id="start" name="trip-start" min="1901-01" max="2018-12-31">
				
				<div class="form-group">
					<label>Nacionalidad:</label>
					<input type="text" name="txtNacionalidad" class="form-control">
				</div>
				<div class="form-group">
      			<label for="inputState">Provincia</label>
    				 <select id="ddlProvincia" class="form-control">
        				<option selected>Seleccionar provincia</option>
       					 <option>...</option>
      				</select>
				<div class="form-group">
					<label>Direccion:</label>
					<input type="text" name="txtDireccion" class="form-control">
				</div>
				
				<div class="form-group">
      			<label for="inputState">Localidad</label>
    				 <select id="ddlLocalidad" class="form-control">
        				<option selected>Seleccionar localidad</option>
       					 <option>...</option>
      				</select>
				<div class="form-group">
					<label>Telefono:</label>
					<input type="text" name="txtTelefono" class="form-control">
				</div>
				<div class="form-group">
					<label>Nombre de usuario:</label>
					<input type="text" name="txtUsuario" class="form-control">
				</div>
				<div class="form-group">
					<label>Correo electronico:</label>
					<input type="text" name="txtEmail" class="form-control">
				</div>
			<div class="form-group">
					<label>Contraseña:</label>
					<input type="password" name="txtContraseña" class="form-control">
				</div>
												<div class="form-group">
					<label>Repetir Contraseña:</label>
					<input type="password" name="txtRepContraseña" class="form-control">
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