<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

</head>
<body>


	<form action="TestBase" method="get">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputEmail4">Email</label> <input type="email"
					class="form-control" name="email" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputPassword4">Mot de passe</label> <input
					type="password" class="form-control" name="motPasse" required>
			</div>

			<div class="form-group col-md-6">
				<label for="inputAddress">Pseudo</label> <input type="text"
					class="form-control" placeholder="Pseudo" name="pseudo" required>
				<c:if test="${compteCree==false}"> Le pseudo est déjà utilisé... </c:if>
			</div>
			<div class="form-group col-md-6">
				<label for="inputAdress">Nom</label> <input type="text"
					class="form-control" placeholder="Nom" name="nom" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputAdress">Prénom</label> <input type="text"
					class="form-control" placeholder="Prénom" name="prenom" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputAdress">Date de Naissance</label> <input
					type="text" class="form-control" name="dateNaissance" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputAdress">Pays</label> <input type="text"
					class="form-control" placeholder="France" name="pays" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputAdress">Numéro de Téléphone</label> <input
					type="text" class="form-control" name="numeroTel" required>
			</div>
		</div>
		<button type="submit" class="btn btn-primary" value="TestBase">S'inscrire</button>
	</form>



	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

</body>
</html>