<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vote Now</title>
</head>
<body>
	<h2>Candidate List</h2>
	<h4>Hello ${o.u.getFirstName()}...!</h4>
	<form action="vote.jsp" method="post">
		<jsp:useBean id="ll" class="com.sunbeam.bean.CandidateBean" />
		${ll.list()}
		<c:forEach var="candidate" items="${ll.ll}">
			<hr>
			<input type="radio" name="vote" value="${candidate.getId()}" /> ${candidate.getName()}
		</c:forEach>
		<hr>
		<input type="submit">
		<hr>
		<a href="logout.jsp">Sign out</a>
		<hr>
	</form>
</body>
</html>