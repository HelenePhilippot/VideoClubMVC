<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Panier</title>
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
	<h1>Panier</h1>
	<div class="container">
		<a class="btn btn-link" href="list">Retour</a> <br />
		<form:form method="post" action="saveArticle"
			modelAttribute="ajoutArticle">
			<div class="form-group">
				<form:select path="">
					<c:forEach var="a" items="${ajoutArticle}">
						<form:option value="${a.id}" label="${a.film.titre}" ></form:option>
					</c:forEach>
				</form:select>
				<button class="btn btn-success" type="submit">Enregistrer</button>
			</div>
		</form:form>
		<div>
			<table class="table">
				<tr>
					<th>ID</th>
					<th>Film</th>
					<th>Date de sortie</th>
					<th>Realisateur</th>
					<th>Numero d'article</th>
					<th>Nombre de disques</th>
					<th>Type d'Article</th>
					<th>Bonus</th>
					<th>3D</th>

					<th></th>
					<th></th>
				</tr>
				<c:forEach var="a" items="${listeArticles}">
					<!--permet de faire boucle for each de listeSoldat avec balises au lieu de java et de metre dans variable s ce quil extratit -->
					<tr>
						<td>${a.id}</td>
						<td>${a.film.titre}</td>
						<td><fmt:formatDate value="${a.film.dateSortie}"
								pattern="dd/MM/yyyy"></fmt:formatDate></td>
						<td>${a.film.realisateur.nom}&nbsp;${a.film.realisateur.prenom}</td>
						<td>${a.numeroArticle}</td>
						<td>${a.nbDisques}</td>
						<td>${a.getClass().simpleName}</td>

						<td><c:if test="${a.getClass().simpleName=='Dvd'}">${a.bonus}</c:if>
						</td>
						<td><c:if test="${a.getClass().simpleName=='BluRay'}">${a.troisD}</c:if>
						</td>

						<td><a class="btn btn-info" href="edit?id=${a.id}">editer</a></td>
						<td><a class="btn btn-danger" href="delete?id=${a.id}">supprimer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>