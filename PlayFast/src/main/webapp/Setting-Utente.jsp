<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestione Profilo Utente</title>
<link href="./CSS/setting-Utente.css" rel="stylesheet">

</head>

<body>
	<h1>Gestione Profilo Utente</h1>
	<form id="profileForm" method="post" action="salva_dati.php">
		<label for="nome">Nome:</label> <input type="text" id="nome"
			name="nome" required> <label for="cognome">Cognome:</label> <input
			type="text" id="cognome" name="cognome" required> <label
			for="email">Email:</label> <input type="email" id="email"
			name="email" required> <label for="email">Password:</label> <input
			type="password" id="password" name="password" required>

		<button type="submit">Salva</button>
	</form>

	<script src="./SCRIPT/setting-Utente.js"></script>

</body>
</html>
