
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<title>Accueil</title>
</head>
<body>
	<h1>Projet Connexion Au Site Web</h1>

	<div class="accueil">
		<form action="Inscription" method="post">

			<input class="button" type="submit" value="inscription">
		</form>

		<form action="Connexion" method="post">
		
			<div class="connexion">
				<label for="pseudo">Pseudo:</label> 
				<input type="text" name="pseudo" required>
			</div>
			<div class="connexion">
				<label for="motDePasse">Mot De Passe:</label> 
				<input	type="text" name="motDePasse" required>
			</div>

			<div class="connexion">
				<input class="button" type="submit" value="connexion">
			</div>

		</form>

	</div>



	
</body>
</html>








