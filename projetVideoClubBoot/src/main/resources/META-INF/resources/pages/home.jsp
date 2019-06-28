<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<header>
			<a href="soldat/list" class="btn btn-link">Liste des Soldats</a> <a
				href="arme/list" class="btn btn-link">Liste des Armes</a>
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name!=null}">
					<c:url var="action" value="/logout"></c:url>
					<form method="post" action="${action}">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}">
						<button type="submit" class="btn btn-link">Deconnexion</button>
					</form>
				</c:when>
				<c:otherwise>
					<a href="login" class="btn btn-link">Connexion</a>
					<a href="formUser" class="btn btn-link">Nouvel Utilisateur</a>
				</c:otherwise>
			</c:choose>
		</header>
		<div>
			<h1>Bienvenue sur mon premier site en Spring Boot !</h1>
		</div>

		<footer></footer>
	</div>
</body>
</html>