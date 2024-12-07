<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<jsp:useBean id="o" class="com.sunbeam.bean.Register" scope="session" />
	<jsp:setProperty property="email" name="o" param="email" />
	<jsp:setProperty property="password" name="o" param="password" />
	${o.li()}
	<c:choose>
		<c:when test="${o.inserted == 1}">
			<c:choose>
				<c:when test="${o.u.role == 'voter'}">
					<c:redirect url="candlist.jsp" />
				</c:when>
				<c:otherwise>
					<c:redirect url="result.jsp" />
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<h5>Login failed...!</h5>
			<a href="index.jsp">Try Again</a>
		</c:otherwise>
	</c:choose>
</body>
</html>
