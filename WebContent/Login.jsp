<%@page import="java.sql.ResultSet"%>
<%@page import="Controlador.ServletUsuario"%>
<%@page import="Entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%session.setAttribute("UsuarioConectado", null); %>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">		
		<title>Insert title here</title>
	</head>
<body>
	<div class="container mt-4 col-lg-4">
		<div class="card">
			<div class="card-body col-sm-10">
				<form action="ServletLogin" method="post">
					<div class="form-group text center">
						<CENTER><img  src="Imagen/Banco.JPG" alt = "70" width="170"/>
						<label> Bienvenidos al sistema</label></CENTER>
					</div>
					<div class="form-group">
						<label>Usuario: </label>
						<input type = "text" name = "txtUsuario" class = "form-control">
					</div>
					<div class="form-group">
						<label>Contraseña: </label>
						<input type = "password" name = "txtPassword" class = "form-control">
					</div>
					<input type = "submit" name = "btnLogin" value = "Ingresar" class = "btn btn-primary btn-block">
					<% if(request.getAttribute("UsuarioConectado")!=null){
						Usuario aux = (Usuario)request.getAttribute("UsuarioConectado"); 
						session.setAttribute("UsuarioConectado", aux);
						if(aux.getEsAdmin()==true){
							RequestDispatcher rd = request.getRequestDispatcher("/AgregarCliente.jsp");
							rd.forward(request, response);
						} else{
							ResultSet rs = new ServletUsuario().DevolverCliente((Usuario)aux);
							rs.next();
							session.setAttribute("NombreClienteConectado",rs.getString("Nombre_Cli"));
							RequestDispatcher rd = request.getRequestDispatcher("/Perfil.jsp");
							rd.forward(request, response);
						}
					}
						%>
				</form>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
