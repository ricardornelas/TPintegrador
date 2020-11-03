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
		<title>Insert title here</title>
	</head>
<body>

		<nav class="navbar navbar-expand-lg navbar-light bg-info">
  		<div class="collapse navbar-collapse" id="navbarNav">
   			 <ul class="navbar-nav">
     			 <li class="nav-item active">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaPrestamo.jsp">Cuentas Bancarias</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaTransferencia.jsp">Mis Préstamos</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaPagos.jsp">Transferencias</a>
      			</li>
    		</ul>
  		</div>
  		 <div class="dropdown">
  			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  				  Nombre
 			 </button>
  			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
  				  <a class="dropdown-item" href="#">Perfil</a>
  				  <a class="dropdown-item" href="#">Cerrar Sesión</a>
 			 </div>
		</div>
	</nav>
			<form>
			<h1>
			<br><b>DATOS DEL PERFIL</b><br><br>
			</h1>
			
			<table style="line-height: normal; width:80%">
			<tr>
				<td>CUIL: <label name="lblCUIL"></label></td>
				<td>Usuario: </td>
			</tr>
			<tr>
				<td>Nombre: </td>
				<td>Apellido: </td>
				<td>Sexo: </td>
			</tr>
			<tr>
				<td>Nacionalidad: </td>
				<td>Fecha de Nacimiento:</td>
			</tr>
			<tr>
				<td>Provincia: </td>
				<td>Localidad: </td>
				<td>Direccion: </td>
			</tr>
			<tr>
				<td>Correo: </td>
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
