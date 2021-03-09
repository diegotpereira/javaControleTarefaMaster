<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Tarefas Adicionadas</title>
<link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="resources/js/jquery-1.11.3.js"></script>
</head>
<body>

	<script type="text/javascript">
                  function finalizaAgora(id){
                	  $post("finalizaTarefa", {'id' : id}, function(){
                		  $("#tarefa_" + id).html("Finalizado");
                	  });
                  }
                  function removeTarefa(id){
                	  $.post("removeTarefa", {'id' : id}, function(){
                		  $("#tarefa_id" + id).closest("tr").hide();
                	  });
                  }
       </script>

	<a href="novaTarefa">Criar Nova Tarefa</a>

	<br />
	<br />

	<table>
		<tr>
			<td>Id</td>
			<td>Descrição</td>
			<td>Finalizado?</td>
			<td>Data Finalização</td>
		</tr>
		<c:forEach items="${tarefas}" var="tarefa">
			<tr>
				<td>${tarefa.id }</td>
				<td>${tarefa.descricao }</td>

				<c:if test="${ tarefa.finalizado eq false }">
					<td id="tarefa_ ${ tarefa.id }"><a href="#"
						onClick="finalizaAgora(${ tarefa.id})">Finalizar Agora!</a></td>
				</c:if>

				<c:if test="${tarefa.finalizado eq true }">
					<td>Finalizado</td>
				</c:if>

				<c:choose>
					<c:when test="${ empty tarefa.dataFinalizacao }">
						<td></td>
					</c:when>

					<c:otherwise>
						<td><fmt:formatDate value="${ tarefa.dataFinalizacao.time }"
								pattern="dd/MM/yyyy" /></td>
					</c:otherwise>
				</c:choose>

				<td><a href="#" onClick="removeTarefa(${tarefa.id})">Finalizar</a>
				<td><a href="alterarVisualizar?id=${tarefa.id }">Alterar</a>
			</tr>

		</c:forEach>
	</table>

</body>
</html>