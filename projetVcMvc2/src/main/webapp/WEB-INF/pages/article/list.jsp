<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste d'Articles </title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

	<!-- ${listeSoldat}//permet de recup notre obj, donne son chemin -->
	<div class="container"><br><br>
		<a class = "btn btn-link" href="addDvd">ajouter un DVD</a>
		<a class = "btn btn-link" href="addBluRay">ajouter un Bluray</a><br><br>
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
			<c:forEach var="a" items="${listeArticle}"><!--permet de faire boucle for each de listeSoldat avec balises au lieu de java et de metre dans variable s ce quil extratit -->
			<tr>
				<td>${a.id}</td>
				<td>${a.film.titre}</td>
				<td><fmt:formatDate value="${a.film.dateSortie}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
				<td>${a.film.realisateurs}</td>
				<td>${a.numeroArticle}</td>
				<td>${a.nbDisques}</td>
				<td>${a.getClass().simpleName}</td>
				
				<td>
					<c:if test="${a.getClass().simpleName=='Dvd'}">${a.bonus}</c:if>
				</td>
				<td>
					<c:if test="${a.getClass().simpleName=='BluRay'}">${a.troisD}</c:if>
				</td>
				
				<td><a class = "btn btn-info" href="edit?id=${a.id}">editer</a></td>
				<td><a class = "btn btn-danger" href="delete?id=${a.id}">supprimer</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>