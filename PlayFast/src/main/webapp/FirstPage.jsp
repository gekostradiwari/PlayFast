<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.*"
    import = "model.beans.ProductBean"
    import = "model.beans.Carrello"
    
    %>
    
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FirstPage</title>
<link href="./CSS/FirstPage.min.css" rel="stylesheet">
<link href="./CSS/FirstPage.css" rel="stylesheet">
<link href="./CSS/InitPage.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<section class="py-5 text-center container">
			<div class="row py-lg-5">
				<div class="col-lg-6 col-md-8 mx-auto">
					<h1 class="fw-light">Il tuo sport preferito</h1>
					<p class="lead text-body-secondary">Sempre a portata di mano e
						vicino a te.</p>
				</div>
			</div>
		</section>
		<section id=ListCampi>
			<h1 id=campiListTitle>La tua lista campi</h1>
		<% ArrayList<ProductBean> campi = (ArrayList<ProductBean>)request.getAttribute("prodotti");
			  
				 if( campi.isEmpty()){ %>
			 	<p id=emptyCampiListTitle>Non ci sono campi disponibili secondo queste richieste.</p>
			 <%}else{ %>
			 
			 	<section id=campiSection>
			 	<%for (ProductBean campo:campi){ %>
			 	<form method="GET" action="/PlayFast/ProductControl">
			 		<div class="card shadow-sm">
							<svg class="bd-placeholder-img card-img-top" width="100%"
								height="225" xmlns="http://www.w3.org/2000/svg" role="img"
								aria-label="Placeholder: Thumbnail"
								preserveAspectRatio="xMidYMid slice" focusable="false">
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#55595c" />
                <image href="<%= campo.getUrlImmagine() %>" width="100%" height="100%"
									preserveAspectRatio="xMidYMid slice" />
              </svg>
							<div class="card-body">
							<h1><%=campo.getNome()%></h1>
							<div name="codice" value="<%=campo.getId()%>">
							<p class="card-text"><%=campo.getIndirizzo()%></p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<button type="button" class="btn btn-sm btn-outline-secondary" name="action" value="AddToCarrello">Add
											Cart</button>
									</div>
								</div>
							</div>
						</div>	
						</form>		 		
			 	<%} %>
			 	</section>
			 <%} %>
		</section>

		<div class="album py-5 bg-body-tertiary">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
					<div class="col">
						<!-- <div class="card shadow-sm">
							<svg class="bd-placeholder-img card-img-top" width="100%"
								height="225" xmlns="http://www.w3.org/2000/svg" role="img"
								aria-label="Placeholder: Thumbnail"
								preserveAspectRatio="xMidYMid slice" focusable="false">
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#55595c" />
                <image href="./IMG/ludi-15-1.jpg" width="100%" height="100%"
									preserveAspectRatio="xMidYMid slice" />
              </svg>
							<div class="card-body">
								<p class="card-text">Il centro sportivo Ludi nasce nel
									Giugno del 1997 da unidea di Ernesto che aveva il sogno di
									creare un centro di aggregazione, divertimento e
									socializzazione per la comunità Terzignese e non solo.</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<button type="button" class="btn btn-sm btn-outline-secondary">Add
											Cart</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- More card elements -->
					</div>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="footer.jsp"></jsp:include>

  	<script src="./SCRIPT/FirstPageUtente.js"></script>
	<script src="./SCRIPT/FirstPage.bundle.min.js"></script>
</body>
</html>