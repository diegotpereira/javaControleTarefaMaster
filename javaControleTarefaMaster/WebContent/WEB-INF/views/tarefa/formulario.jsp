<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h3>Adicionar Tarefa</h3>
	  <form action="adicionaTarefa" method="post">
	  Descri��o: <br />
	  
	  <textarea name="descricao" rows="5" cols="100"></textarea>
	  <br/>
	  <form:errors path="tarefa.descricao" cssStyle="color:red"/>
	  <br/>
	  <input class="btn btn-success" type="submit" value="Adicionar">
	  </form>

</body>
</html>