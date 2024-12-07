<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>delete</title>
</head>
<body>
	<jsp:useBean id="obj" class="com.sunbeam.bean.Candidate"></jsp:useBean>
	<jsp:setProperty property="id" name="obj" param="id" />
	${obj.del() }
	<c:choose>
		<c:when test="${obj.c }">
			<jsp:forward page="result.jsp">
				<jsp:param value="Deleted successfully for id ${obj.id }" name="msg" />
			</jsp:forward>
		</c:when>
		<c:otherwise>
			<jsp:forward page="result.jsp">
				<jsp:param value="Delete failed for id ${obj.id }" name="msg" />
			</jsp:forward>
		</c:otherwise>
	</c:choose>
	<hr>
	<a href="logout.jsp">Sign out</a>
	<hr>
</body>
</html>