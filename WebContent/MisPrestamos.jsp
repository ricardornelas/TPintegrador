<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
    <%@page import="Entidad.Prestamos"%>
    <%@page import="Entidad.Cuotas"%>
    <%@page import="java.sql.ResultSet" %>
    <%@page import="Controlador.ServletPrestamos"%>
    <%@page import="Controlador.ServletUsuario"%>
    <%@page import="Controlador.ServletCliente"%>
    <%@page import="java.util.ArrayList" %>
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
				
		<title>Mis Prestamos</title>
		
	</head>
	   
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
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="CuentasBancarias.jsp">Cuentas Bancarias</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="MisPrestamos.jsp">Mis Prestamos</a>
      			</li>
      			 <li class="nav-item">
       				 <a style ="margin-left: 10px; border: none" class="btn btn-outline-light" href="Transferencia.jsp">Transferencias</a>
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
	
	<form action = "ServletPrestamos" method ="post">
	
	<%
	Usuario Usu=(Usuario)session.getAttribute("UsuarioConectado");
	ResultSet Rs = new ServletUsuario().DevolverCliente(Usu);
	Rs.next();
	String CUIL = Rs.getString("CUIL_Cli");
	ArrayList<Prestamos>Lista=new ServletPrestamos().PrestamosXCUIL(CUIL);
	%>
	
<br>
<H1>Prestamos</H1>
<br>	
<table id="tabla" class="display">
	<thead>
		<tr>
			<th>Codigo</th>
			<th>CBU de cuenta</th>
			<th>Importe total a Pagar</th>
			<th>Importe Pedido</th>
			<th>Monto por Mes</th>
			<th>Cantidad de Cuotas</th>
			<th>Cuotas Pagadas</th>
			<th>Cuotas Faltantes</th>
			<th>Estado</th>
		</tr>
	</thead>
	<tbody>
	<%
	int i;
	for(Prestamos reg:Lista){ %>	
	
	<tr>
		<td><%=reg.getIdPrestamo()%> <input type="hidden" name="Id" value=<%=reg.getIdPrestamo() %>> </td>
		<td><%=reg.getCbu()%> <input type="hidden" name="Cbu" value=<%=reg.getCbu()%>>        </td>
		<td>$<%=new DecimalFormat("#.####################################").format(reg.getImpAPagar())%> <input type="hidden" name="ImpAPagar" value=<%=reg.getImpAPagar()%>> </td>
		<td>$<%=new DecimalFormat("#.####################################").format(reg.getImpPedido())%> <input type="hidden" name="ImpPedido" value=<%=reg.getImpPedido() %>> </td>
		<td>$<%=new DecimalFormat("#.####################################").format(reg.getMontoXMes())%> <input type="hidden" name="MontoXMes" value=<%=reg.getMontoXMes() %>> </td>
		<td><%=reg.getCuotas() %> <input type="hidden" name="Cuotas" value=<%=reg.getCuotas() %>> </td>
		<td><%=i=new ServletPrestamos().DevolverCuotasPagas(reg.getIdPrestamo()) %> <input type="hidden" name="CuotasPagas" value=<%=i%>> </td>
		<td><%=reg.getCuotas()-i%>  <input type="hidden" name="CuotasFaltantes" value=<%=i%>> </td>
		<td><%if(reg.getAutorizado()){%> Autorizado <%} else { %> En espera <%} %></td>
		<td style="width: 1px"> <input type="submit" name="btnSeleccionarPrestamo" value="Seleccionar" <%if(!reg.getAutorizado()){%>disabled<%} %>></td>
	</tr>
	<%}%>
	</tbody>
</table>
<br>
<div style="text-align: center;"><input type="submit" name="btnPrestamo" value="Solicitar Nuevo Prestamo"></div>

</form>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	
	
</body>
</html>