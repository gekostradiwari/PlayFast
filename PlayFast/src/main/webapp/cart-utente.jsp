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
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Cart</title>

<link href="./CSS/FirstPage.min.css" rel="stylesheet">
<link href="./CSS/FirstPage.css" rel="stylesheet">
<link href="./CSS/InitPage.css" rel="stylesheet">
<link href="./CSS/cart.css" rel="stylesheet">
</head>
<body
	style="background-image: url('./IMG/Sfondo4.svg'); background-size: cover; background-repeat: no-repeat; background-attachment: fixed;">

	<jsp:include page="header.jsp"></jsp:include>
	<!-- <jsp:include page=""></jsp:include> -->

	<div class="container">
		<h1>Il tuo Carrello</h1>
		<div class="cart">
			<%for(ProductBean product : products){%>
			<div class="cart-item">
				<h1 class="cart-item-title"><%=product.getNome()%></h1>
				<div class="cart-item-content">
					<P class="cart-item-title"><%=product.getIndirizzo()%></p>
					<p class="cart-item-price"><%=product.getPrezzo() %>$</p>
					<div class="cart-item-remove">
					<form method="GET" action="/PlayFast/ProductControl">
					<input type="text" hidden="hidden" name="codice" value="<%=product.getId()%>">
					<button name="action" value="RemoveToCarrello" class="btn btn-sm btn-outline-secondary" >
							<i class="fas fa-trash"></i> Rimuovi
						</button>
						</form>
					</div>
				</div>
			</div>
			<%}%>
			
			<!-- Add more cart items here -->
		</div>
	</div>
	<div class="btn-container">
		<a href="Sign-in.jsp">Prosegui al pagamento</a>
		<!--<button class="btn">Prosegui al pagamento</button>-->
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
		
	<script src="./SCRIPT/cart-Utente.js"></script>
	<script src="./SCRIPT/FirstPage.bundle.min.js"></script>
</body>
</html>