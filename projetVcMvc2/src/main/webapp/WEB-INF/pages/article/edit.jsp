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
		<h1>Editer un Article</h1>
		<c:choose>
			<c:when test="${article.getClass().simpleName=='Dvd'}">
				<c:url value="saveA" var="action"></c:url>
			</c:when>
			<c:when test="${article.getClass().simpleName=='Bluray'}">
				<c:url value="saveB" var="action"></c:url>
			</c:when>
		</c:choose>


		<form:form method="post" action="${action}" modelAttribute="article">

			<form:hidden path="version" value="${arme.version}" />

			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input cssClass="form-control" path="id" readonly="readonly"
					value="${arme.id}" placeholder="Renseigné Automatiquement" />
			</div>
			<div class="form-group">
				<form:label path="libelle">libelle:</form:label>
				<form:input cssClass="form-control" path="libelle"
					value="${arme.libelle}" />
			</div>
			<div class="form-group">
				<form:label path="dps">dps:</form:label>
				<form:input type="number" step="0.1" cssClass="form-control"
					path="dps" value="${arme.dps}" />
			</div>
			<!-- epée seulement -->
			<c:if test="${arme.getClass().simpleName=='Epee'}">
				<div class="form-group">
					<form:label path="deuxMains">Deux Mains:</form:label>
					<form:checkbox path="deuxMains" />
					<form:errors path="deuxMains" cssClass="alert alert-danger"
						element="div"></form:errors>

				</div>
			</c:if>

			<!-- gun seulement -->
			<c:if test="${arme.getClass().simpleName=='Pistolet'}">
				<div class="form-group">
					<form:label path="cadence">Cadence:</form:label>
					<form:input type="number" step="0.1" cssClass="form-control"
						path="cadence" value="${arme.cadence}" />
					<form:errors path="cadence" cssClass="alert alert-danger"
						element="div"></form:errors>

				</div>
			</c:if>


			<div class="form-group">
				<button class="btn btn-success" type="submit">Enregistrer</button>
				<a class="btn btn-warning" href="list">Annuler </a>
			</div>



		</form:form>

	</div>
</body>
</html>