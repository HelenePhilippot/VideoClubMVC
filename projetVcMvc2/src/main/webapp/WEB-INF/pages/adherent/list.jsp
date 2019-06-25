<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<a href="add" class="btn btn-link">ajouter</a>
		<table class="table">
			<tr>
				<th>civilite</th>
				<th>prenom</th>
				<th>nom</th>
				<th>noAdherent</th>
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
					<td>${a.noAdherent}</td>
					<td>${s.adresse.numero}&nbsp;${s.adresse.rue}</td>
					<td>${s.adresse.cp}</td>
					<td>${s.adresse.ville}</td>
					<td><a class="btn btn-info" href="edit?id=${s.id}">editer</a></td>
					<td><a class="btn btn-danger" href="delete?id=${s.id}">supprimer</a></td>
					<td><a class="btn btn-danger" href="listeArticle?id=${s.id}">liste des articles</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>