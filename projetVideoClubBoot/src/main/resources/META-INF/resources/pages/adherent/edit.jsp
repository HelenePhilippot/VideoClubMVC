<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier un adhérent</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<h1>Editer un Adherent</h1>
	<div class="container">
		<form:form method="post" action="save" modelAttribute="adherent">
			<form:hidden path="version" />
			<div class="form-group">
				<form:label path="civilite">civilite:</form:label>
				<form:select path="civilite" cssClass="form-control">
					<form:options/>
				</form:select>

			</div>
			<div class="form-group">
				<form:label path="prenom">prenom:</form:label>
				<form:input path="prenom" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="nom">nom:</form:label>
				<form:input path="nom" cssClass="form-control" />
				<form:errors path="nom" cssClass="alert alert-danger" element="div"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="numero">Numero adherent</form:label>
				<form:input type="number" path="numero" cssClass="form-control" />
			</div>

			<div class="form-group">
				<form:label path="dtNaiss">date de naissance:</form:label>
				<form:input type="date" path="dtNaiss" cssClass="form-control" />
				<form:errors path="dtNaiss"></form:errors>
			</div>

			<div class="form-group">
				<form:label path="adresse.numero">numero:</form:label>
				<form:input type="number" path="adresse.numero"
					cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.rue">rue:</form:label>
				<form:input path="adresse.rue" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.codePostal">code postal:</form:label>
				<form:input path="adresse.codePostal" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.ville">ville:</form:label>
				<form:input path="adresse.ville" cssClass="form-control" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">enregistrer</button>
				<a href="list" class="btn btn-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>