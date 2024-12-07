<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>register</title>
</head>
<body>
	<jsp:useBean id="obj" class="com.sunbeam.bean.Register" />
	<jsp:setProperty name="obj" property="*" />
	${obj.iu()}
	<c:choose>
		<c:when test="${obj.inserted eq 1}">
			<h5>Registered Successfully...!</h5>
			<a href="index.jsp">Login</a>
		</c:when>
		<c:otherwise>
			<h5>Registered Failed Successfully...!</h5>
			<a href="signup.jsp">Register Again...!</a>
		</c:otherwise>
	</c:choose>
</body>
</html>