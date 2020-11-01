<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">		
			<title>Modificar Usuario</title>
	</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-info">
  		<div class="collapse navbar-collapse" id="navbarNav">
   			 <ul class="navbar-nav">
     			 <li class="nav-item active">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaEmpleado.jsp">Home </a>
     			 </li>
     			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="AgregarUsuario.jsp">Agregar Usuario</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="AgregarCuenta.jsp">Agregar Cuenta</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaSolicitudPrestamo.jsp">Prestamos Solicitados</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ModificarCliente.jsp">Modificiar Usuario</a>
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
			
<div class="form-group">
					<label>Filtrar por Cuil:</label>
					<input type="text" name="txtCuil" class="form-control">
				</div>		
			
			<table border="1" style="width: 393px; height: 47px">
<tr> <th><CENTER>Cuil</CENTER></th><th><CENTER>Nombre</CENTER></th><th><CENTER>Apellido</CENTER></th><th><CENTER>Usuario</CENTER></th><th></th>
<tr> <th></th><th></th><th></th><th></th><th> <CENTER> <input type="submit" name="btnEditar" value="Seleccionar"> </CENTER></th>

</table>
			
<p><p>		
<label>Usuario: (Se muestra el usuario seleccionado)</label>			

<div class="form-group">
					<label>Nueva Contraseña:</label>
					<input type="password" name="txtContraseña" class="form-control">
					
					<p>
					
					<label>Repetir Contraseña:</label>
					<input type="password" name="txtRepContraseña" class="form-control">
					
					
				</div>	
</form>
</body>
</html>tml>
