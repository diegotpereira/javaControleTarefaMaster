<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript" src="resources/js/jquery-1.11.3.js"></script>
</head>
<body>

    <h2>Página inicial da Lista de Tarefas</h2>
    
    <p>Bem vindo, ${usuarioLogado.login}!</p>
    <a href="listaTarefa" class="btn btn-success custom-width">Clique Aqui</a> para acessar a lista de tarefas<br/><br/>
    
    <a href="logout" class="btn btn-danger custom-width">Sair do Sistema</a>

</body>
</html>