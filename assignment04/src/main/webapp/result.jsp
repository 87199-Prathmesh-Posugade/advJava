<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<h1>Candidate List</h1>
	<h4>Hello ${o.u.getFirstName()}...!</h4>
	<jsp:useBean id="obj" class="com.sunbeam.bean.CandidateBean" />
	${obj.list()}
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Party</th>
				<th>Votes</th>				
				<th>Action</th>								
			</tr>
		</thead>
		<tbody>
			<c:forEach var="cand" items="${obj.ll}">
				<tr>
					<td>${cand.id }</td>
					<td>${cand.name}</td>
					<td>${cand.party}</td>
					<td>${cand.votes}</td>
					<td>
						<a href="editCand.jsp?id=${cand.id}">Edit   </a>
						<a href="delCand.jsp?id=${cand.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h5>${param.msg }</h5>
</body>
</html>