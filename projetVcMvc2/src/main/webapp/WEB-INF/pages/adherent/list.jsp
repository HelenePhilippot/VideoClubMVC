<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des Adherents</title>
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
<h1>Liste des Adhérents</h1>
	<div class="container">
		<a href="add" class="btn btn-link">ajouter</a>
		<table class="table">
			<tr>
				<th>civilite</th>
				<th>prenom</th>
				<th>nom</th>
				<th>noAdherent</th>
				<th>Date de naissance</th>
				<th>rue</th>
				<th>code postal</th>
				<th>ville</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="a" items="${listeAdherent}">
				<tr>
					<td>${a.civilite}</td>
					<td>${a.prenom}</td>
					<td>${a.nom}</td>
					<td>${a.numero}</td>
					<td>${a.dtNaiss}</td>
					<td>${a.adresse.numero}&nbsp;${a.adresse.rue}</td>
					<td>${a.adresse.codePostal}</td>
					<td>${a.adresse.ville}</td>
					<td><a class="btn btn-info" href="edit?numero=${a.numero}">editer</a></td>
					<td><a class="btn btn-danger" href="delete?numero=${a.numero}">supprimer</a></td>
					<td><a class="btn btn-warning" href="listeArticl0e?numero=${a.numero}">liste des articles</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>