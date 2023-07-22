<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Form Campo Sportivo</title>
<link href="./CSS/Inserisci-Campo.css" rel="stylesheet">
</head>
<body>
	<h1>Inserisci dati Campo Sportivo</h1>
	<form id="campoForm" action="/PlayFast/AdminControl?action=aggiungiProdotto" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
	
		<label for="nome_campo">Nome del Campo:</label> 
		<input type="text" id="nome_campo" name="nome_campo" required>
		<label for="struttura">Struttura:</label> 
		<input type="text" id="struttura" name="struttura" required>
		 <label	for="tipologia">Tipologia:</label> 
		 <select id="tipologia"	name="tipologia" required>
			<option>Basket</option>
			<option>Calcio a 5</option>
			<option>Calcio a 7</option>
			<option>Calcio a 11</option>
			<option>Padel</option>
			<option>Pallavolo</option>
			<option>Tennis</option>
			<!-- Aggiungi altre opzioni se necessario -->
		</select> 
		<label for="telefono">Numero di Telefono:</label> 
		<input type="tel" id="telefono" name="telefono" pattern="[0-9]+" required> 
		<label for="email">Indirizzo Email:</label> 
		<input type="email" id="email" name="email" required> 
		<label for="indirizzo">Indirizzo:</label> 
		<input type="text" id="indirizzo" name="indirizzo" required>
		<label for="citta">Citta':</label> 
		<input type="text" id="citta'" name="citta" required>
		<label for="costo">Costo:</label> 
		<input type="text" id="costo" name="costo" required> 
		<input class="form-control" type="date" name="Data" required> 
		<span class="form-label">DATA</span>
		
		<label for="foto">Foto del Campo:</label> 
		<input type="file" id="foto" name="foto" accept="image/*">
		<button type="submit" >Invia Dati</button>
	</form>


	 <!--  <script src="./SCRIPT/Inserisci-Campo.js"></script> -->
 </body>
</html>
