<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
</head>
<body>
	<h2>Edit Candidate</h2>
	<form action="edited.jsp" method="post">
		<hr>
		<jsp:useBean id="obj" class="com.sunbeam.bean.Candidate"></jsp:useBean>
		<jsp:setProperty property="id" name="obj" param="id" />
		${obj.ret() } <input type="hidden" name="id" value="${obj.id}">
		Name: <input type="text" name="name" value="${obj.name}">
		<hr>
		Party: <input type="text" name="party" value="${obj.party}">
		<hr>
		Votes: <input readonly="readonly" type="text" name="votes"
			value="${obj.votes}">
		<hr>
		<input type="submit">
		<hr>
	</form>
</body>
</html>