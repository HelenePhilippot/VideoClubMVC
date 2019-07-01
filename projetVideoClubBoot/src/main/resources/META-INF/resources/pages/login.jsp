<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<h1>Authentification</h1>
	<div class="container">
		<div style="align-content: center">
		<c:if test="${param.error!=null}">
		<div id="error" class="alert alert-danger">Erreur d'authentification</div>
		</c:if>
			<form method="post" action="">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">
				<div class="form-group">
					<label for="username">Login:</label> <input id="username"
						class="form-control" name=username>
				</div>
				<div class="form-group">
					<label for="password">Password:</label> <input type="password"
						id="password" name=password class="form-control">
				</div>
				<div class="form-group">
					<input type="submit" value="envoyer" class="btn btn-success">
					<a href="/boot" class="btn btn-warning" >Annuler</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>