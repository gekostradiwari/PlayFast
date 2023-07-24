<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.*"
    import = "model.beans.ProductBean"
    import = "model.beans.Carrello"%>
    <%
	Carrello carrello = (Carrello) session.getAttribute("carrello");
	ArrayList<ProductBean> products = null;
	if(carrello != null)	
		products = carrello.getProducts();
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="./CSS/FirstPage.min.css" rel="stylesheet">
<link href="./CSS/FirstPage.css" rel="stylesheet">
<link href="./CSS/InitPage.css" rel="stylesheet">
<link href="./CSS/cart.css" rel="stylesheet">
    <title>Verifica Carta di Credito</title>
    <script type="text/javascript">
        function isValidCreditCard(cardNumber) {
            // Inserisci qui la tua implementazione JavaScript della funzione di validazione della carta di credito
            // Puoi utilizzare la funzione isValidCreditCard() che ti ho fornito precedentemente
        }

        function checkCreditCard() {
            var cardNumber = document.getElementById("cardNumber").value;
            var isValid = isValidCreditCard(cardNumber);

            var resultMessage = isValid ? "La carta di credito è valida." : "La carta di credito non è valida.";
            document.getElementById("result").innerHTML = resultMessage;
        }
    </script>
</head>
<body>
    <h1>Verifica Carta di Credito</h1>
    <form>
        <label for="cardNumber">Numero della Carta di Credito:</label>
        <input type="text" id="cardNumber" name="cardNumber" required>

        <input type="button" value="Verifica" onclick="checkCreditCard()">
    </form>

    <div id="result"></div>
	style="background-image: url('./IMG/Sfondo4.svg'); background-size: cover; background-repeat: no-repeat; background-attachment: fixed;">
	<script src="./SCRIPT/cart.js"></script>
	<script src="./SCRIPT/FirstPage.bundle.min.js"></script>
	<script src="./SCRIPT/validitycheckcard.js"></script>
</body>
</html>