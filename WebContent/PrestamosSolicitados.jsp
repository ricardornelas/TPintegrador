<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Prestamos" %>
    <%@page import="java.util.ArrayList"%>
    <%@page import="Controlador.ServletPrestamos"%>
    <%@page import= "java.text.DecimalFormat"%>
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
		
		<title>Prestamos Solicitados</title>
		
	   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
       <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
	<script type="text/javascript">
	$(document).ready( function () {
	    $('#tabla').DataTable();
	} );
	</script>
		
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
<H1>Prestamos Solicitados</H1>

<%ArrayList<Prestamos> lista = new ServletPrestamos().ListadoPrestamos(); %>

<br>
<table id="tabla" class="display">
		<thead>
		<tr>
			<th>CUIL</th>
			<th>CBU de cuenta</th>
			<th>Importe Pedido</th>
			<th>Importe total a Pagar</th>
			<th>Cantidad de Cuotas</th>
			<th>Monto por Mes</th>
			<th>Acciones</th>
		</tr>
	</thead>
	<tbody>
	<%for(Prestamos reg:lista){ %>
	<tr>
	    
		<td> <%=reg.getCuil() %> <input type="hidden" name="Cuil" value=<%=reg.getCuil() %>> </td>
		<td> <%=reg.getCbu() %> <input type="hidden" name="Cbu" value=<%=reg.getCbu() %>> </td>
		<td> <%=new DecimalFormat("#.####################################").format(reg.getImpPedido()) %> <input type="hidden" name="ImpPedido" value=<%=reg.getImpPedido()%>> </td>
		<td> <%=new DecimalFormat("#.####################################").format(reg.getImpAPagar()) %> <input type="hidden" name="ImpAPagar" value=<%=reg.getImpAPagar() %>></td>
		<td> <%=reg.getCuotas() %> <input type="hidden" name="Cuotas" value=<%=reg.getCuotas() %>></td>
		<td> <%=new DecimalFormat("#.####################################").format(reg.getMontoXMes()) %> <input type="hidden" name="MontoXMes" value=<%=reg.getMontoXMes() %>> </td>
		<td style="width: 1px; text-align: center;"> 
		<input type="hidden" name="Id" value=<%=reg.getIdPrestamo() %>>
		<input type="submit" name="btnAceptar" value="Aceptar" style="width: 100%" onclick="window.location.href='ServletPrestamos?btnAceptar=1&Id=<%=reg.getIdPrestamo()%>&Cuotas=<%=reg.getCuotas()%>'">
		<input type="submit"  name="btnRechazar" value="Rechazar" style="width: 100%" onclick="window.location.href='ServletPrestamos?btnRechazar=1&Id=<%=reg.getIdPrestamo()%>'">
													 
		</td>
	</tr>
	<%} %>
		</tbody>
</table>

	<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"><%if(request.getAttribute("Exito")!=null){%>Exito<%} else { %>Error<%} %></h5>
	      </div>
	      <div class="modal-body">
	        <%=request.getAttribute("Mensaje")%>
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
	
	<%if(request.getAttribute("Mensaje")!=null){ %>
	<script>
	$(document).ready(function(){
	  $("#myModal").modal("show");
	});
	</script>
	<%} %>
	
	
</body>
</html>
