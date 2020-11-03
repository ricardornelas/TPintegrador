<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">		
			<title>Transferencias</title>
	</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-info">
  		<div class="collapse navbar-collapse" id="navbarNav">
   			 <ul class="navbar-nav">
     			 <li class="nav-item active">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaPrincipal.jsp">Home </a>
     			 </li>
     			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaTransferencia.jsp">Transferencias</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaCuenta.jsp">Historial de Cuentas</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaPrestamo.jsp">Prestamos </a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="ventanaPago.jsp">Pagos</a>
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
			<form>
			
			<h1>TRANSFERENCIAS</h1>
			
			<div class="form-group">
					<label>Ingrese número de CBU de la cuenta destino:</label>
					<input type="text" name="txtCbu" class="form-control">
				</div>
					<div class="form-group">
					<label>Monto a transferir:</label>
					<input type="text" name="txtCbu" class="form-control">
				</div>
			
			<br><br>
				<input type = "submit" name="btnTransferir" value="Transferir" class="btn btn-info">
			</form>
			
	
</body>
</html>
