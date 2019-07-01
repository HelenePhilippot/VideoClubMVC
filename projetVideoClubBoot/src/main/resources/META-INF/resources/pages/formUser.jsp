<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvel Utilisateur</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<style>
.button {
	background-color: #1c87c9;
	border: none;
	color: white;
	padding: 10px 24px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	margin: 3px 2px;
	cursor: pointer;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
<h1>Nouvel Utilisateur</h1>
	<div class="container">
	<c:url var="action" value="/user/save"/>
		<form:form method="post" action="${action}" modelAttribute="user">
			
			<div class="form-group">
				<form:label path="username">Username:</form:label>
				<form:input path="username" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="password">Password:</form:label>
				<form:input type="password" path="password" cssClass="form-control" />
			</div>
				<c:url var="accueil" value="/"/>
			
			<div class="form-group">
				<button type="submit" class="btn btn-success">Enregistrer</button>
				<c:url var="home" value="/"></c:url>
				<a href="${home}" class="btn btn-warning">Annuler</a>
			</div>
		</form:form>

	</div>
</body>
</html>