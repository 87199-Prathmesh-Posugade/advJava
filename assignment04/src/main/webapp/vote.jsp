<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>vote status</title>
</head>
<body>
	<c:choose>
		<c:when test="${o.u.status == 0 }">
			<jsp:useBean id="ooo" class="com.sunbeam.bean.Candidate"></jsp:useBean>
			<jsp:setProperty property="userid" name="ooo" value="${o.u.getId() }" />
			<jsp:setProperty property="id" name="ooo" param="vote" />			
			${ooo.vot() }
			<c:choose>
				<c:when test="${ooo.c }">
					<h5>Voted successfully...!</h5>
					${o.u.setStatus(1) }
				</c:when>
				<c:otherwise>
					<h5>Voted Failed...!</h5>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<h5>Already voted</h5>
		</c:otherwise>
	</c:choose>
</body>
</html>