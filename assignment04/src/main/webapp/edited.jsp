<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edited</title>
</head>
<body>
	<jsp:useBean id="obj" class="com.sunbeam.bean.Candidate" />
	<jsp:setProperty property="*" name="obj" />
	${obj.upd() }
	<c:choose>
		<c:when test="${obj.c }">
			<jsp:forward page="result.jsp">
				<jsp:param value="Candidate Updated: ${obj.id }" name="msg"/>
			</jsp:forward>
		</c:when>
		<c:otherwise>
			<jsp:forward page="result.jsp">
				<jsp:param value="Failed to update candidate: ${obj.id }" name="msg"/>
			</jsp:forward>
		</c:otherwise>
	</c:choose>
</body>
</html>