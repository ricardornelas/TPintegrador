<%@page import="Controlador.ServletCuenta"%>
<%@page import="Entidad.TipoDeCuenta"%>
<%@page import="Entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Usuario"%>
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
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="CuentasBancarias.jsp">Cuentas Bancarias</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="MisPrestamos.jsp">Mis Prestamos</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="TransferenciaEntreCuentas.jsp">Transferir Entre Mis Cuentas</a>
      			</li>
      			<li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="TransferenciaOtraCuenta.jsp">Transferencia a Otra Cuenta</a>
      			</li>
    		</ul>
  		</div>
  		 <div class="dropdown">
  			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  				  <%=(String)session.getAttribute("NombreClienteConectado") %>
 			 </button>
  			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
  				  <a class="dropdown-item" href="Perfil.jsp">Perfil</a>
  				  <a class="dropdown-item" href="Login.jsp">Cerrar Sesion</a>
 			 </div>
		</div>
	</nav>
	<div class="card">
		<div class="card-body">
			<form action="ServletCuenta" method="post">
			
			<h1>TRANSFERENCIAS</h1>
			
				<% 
				Usuario Usu = (Usuario)session.getAttribute("UsuarioConectado");				
				ArrayList<Cuenta> lista = new ServletCuenta().CuentasXCUIL(Usu.getNombreU());
				ArrayList<TipoDeCuenta> TiposCuentas = new ServletCuenta().TiposDeCuentas();
				%>
			
			<div class="form-group">
				<div class="form-group">
					<label>Cuenta Origen:</label>
					 <select name="ddlCuentaOrigen" class="form-control" required>
        				<option selected disabled value="">Seleccionar Cuenta</option>
        				<%for(Cuenta reg : lista){ %>
        				<option value="<%=reg.getCBU()%>"><%=reg.getCBU()%>, <%for(TipoDeCuenta TC:TiposCuentas){ if(reg.getIdTipoCuenta()==TC.getId()){%><%=TC.getDescripcion()%><%}}%>, $<%=reg.getSaldo() %></option>
        				<%} %>
      				</select>
				</div>
					<label>Ingrese numero de CBU de la cuenta destino:</label>
					<input type="number" name="txtCbuDestino" class="form-control">
				</div>
					<div class="form-group">
					<label>Monto a transferir:</label>
					<input type="number" name="txtMonto" class="form-control">
				</div>
			
			<br><br>
				<input type = "submit" name="btnTransferirOtraCuenta" value="Transferir" class="btn btn-info">
			</form>
			
			<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel"><%if(request.getAttribute("Exito")!=null){ %>Exito<%} else { %>Error <%} %></h5>
			      </div>
			      <div class="modal-body">
			        <%if(request.getAttribute("Mensajes")!=null)
			        	for(String Mensaje: (ArrayList<String>)request.getAttribute("Mensajes")){%>
			        <%=Mensaje %>
			        <%} %>
			      </div>
			      <div class="modal-footer">
			        <form action="ServletCuenta" method="post">
			        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
			        </form>
			      </div>
			    </div>
			  </div>
			</div>
			
			
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	<%if(request.getAttribute("Mensajes")!=null){ %>
	<script>
	$(document).ready(function(){
	  $("#myModal").modal("show");
	});
	</script>
	<%} %>
</body>
</html>
