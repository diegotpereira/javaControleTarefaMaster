<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript" src="resources/js/jquery-1.11.3.js"></script>
</head>
<body>
	<h2>Login</h2>

	<div class="container">
		<form action="efetuaLogin" method="post">
		    <div class="form-group">
		         Login: <input type="text" name="login" />
		    </div>
		    <div class="form-group">
		         Senha: <input type="password" name="senha" />
		    </div>
			  <input type="submit" value="Acessar Tarefas" />
		</form>
	</div>


</body>
</html>