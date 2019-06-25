<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edition d'un article</title>
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
<body>
	<div class="container">
		<h1>Editer un Film</h1>


		<form:form method="post" action="saveFilm" modelAttribute="film">

			<form:hidden path="version" value="${film.version}" />

			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input cssClass="form-control" path="id" readonly="true"
					value="${film.id}" placeholder="Renseigné Automatiquement" />
			</div>
			<div class="form-group">
				<form:label path="titre">Film:</form:label>
				<form:input cssClass="form-control" path="titre" value="${film.titre}"/>
			</div>
			<div class="form-group">
				<form:label path="dateSortie">Date de Sortie:</form:label>
				<form:input type = "date" cssClass="form-control" path="dateSortie" value="${film.dateSortie}"/>
			</div>
			<div class="form-group">
				<form:label path="realisateur">Realisateur:</form:label>
				<form:select path="realisateur" cssClass="form-control" items = "${listeReal}" itemLabel="nom"/>
			</div>
			
			
			<div class="form-group">
				<button class="btn btn-success" type="submit">Enregistrer</button>
				<a class="btn btn-warning" href="../article/list">Annuler </a>
			</div>



		</form:form>

	</div>
</body>
</html>