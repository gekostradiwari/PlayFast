<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.beans.Carrello"
    import="model.beans.*"
    import="java.util.*"%>
    
<%
	Carrello carrello = (Carrello) session.getAttribute("carrello");
    ArrayList<ProductBean> prodotti = carrello.getProducts();
%>

<!doctype html>
<html>
<head>
    <title>Checkout</title>
    <link href="./CSS/Checkout.min.css" rel="stylesheet">
    <link href="./CSS/checkout.css" rel="stylesheet">
    <link href="./CSS/FirstPage.min.css" rel="stylesheet">
    <link href="./CSS/FirstPage.css" rel="stylesheet">
  
</head>
<body style="background-image: url('./IMG/Sfondo4.svg'); background-size: cover; background-repeat: no-repeat; background-attachment: fixed;">
    <div class="container">
        <main>
            <div class="py-5 text-center">
                <!--<img class="d-block mx-auto mb-4" src="logo1.png" alt="" max-width="20%" height="168">-->
                <h2>Checkout</h2>
            </div>

            <div class="row g-5">
                <div class="col-md-5 col-lg-4 order-md-last">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-primary">La tua carta</span>
                        <span class="badge bg-primary rounded-pill">3</span>
                    </h4>
                     <%
                     int sum = 0;
                     for(ProductBean product : prodotti){ 
                     	sum += product.getPrezzo();
                     %>
                    <ul class="list-group mb-3">
                        <li class="list-group-item d-flex justify-content-between lh-sm">
                            <div>
                                <h6 class="my-0"><%= product.getNome() %></h6>
                                <small class="text-body-secondary"><%= product.getIndirizzo() %></small>
                            </div>
                            <span class="text-body-secondary"><%= product.getPrezzo() %>$</span>
                        </li>
                         <li class="list-group-item d-flex justify-content-between">
                            <span>Total (EUR)</span>
                            <strong><%= sum%></strong>
                        </li>
                   
                    </ul>
                    <% } %>
                   
                    </ul>
                    
                </div>
                 <div class="col-md-7 col-lg-8">
                    <h4 class="mb-3">Indirizzo di fatturazione</h4>
                    <form class="needs-validation" novalidate>
                        <div class="row g-3">
                            <div class="col-sm-6">
                                <label for="firstName" class="form-label">Nome</label>
                                <input type="text" class="form-control" id="firstName" placeholder="Mario" value="" required>
                                <div class="invalid-feedback">Valid first name is required.</div>
                            </div>
                            <div class="col-sm-6">
                                <label for="lastName" class="form-label">Cognome</label>
                                <input type="text" class="form-control" id="lastName" placeholder="Rossi" value="" required>
                                <div class="invalid-feedback">Valid last name is required.</div>
                            </div>
                           
                            <div class="col-12">
                                <label for="email" class="form-label">Email <span class="text-body-secondary">(Opzionale)</span></label>
                                <input type="email" class="form-control" id="email" placeholder="mario@rossi.com">
                                <div class="invalid-feedback">Inserire un indirizzo e-mail valido per ricevere aggiornamenti sulla spedizione.</div>
                            </div>
                            <div class="col-12">
                                <label for="address" class="form-label">Indirizzo</label>
                                <input type="text" class="form-control" id="address" placeholder="Via Roma 123" required>
                                <div class="invalid-feedback">Inserire indirizzo di spedizione.</div>
                            </div>
                            <div class="col-12">
                                <label for="address2" class="form-label">Indirizzo secondario <span class="text-body-secondary">(Opzionale)</span></label>
                                <input type="text" class="form-control" id="address2" placeholder="Via Roma 123">
                            </div>
                            <div class="col-md-5">
                                <label for="country" class="form-label">Paese</label>
                                <select class="form-select" id="country" required>
                                    <option value="">Scegli...</option>
                                    <option>Italy</option>
                                </select>
                                <div class="invalid-feedback">Please select a valid country.</div>
                            </div>
                            <div class="col-md-4">
                                <label for="state" class="form-label">Province</label>
                                <select class="form-select" id="state" required>
                                    <option value="">Scegli...</option>
                                    <option>Napoli</option>
                                </select>
                                <div class="invalid-feedback">Please provide a valid state.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="zip" class="form-label">Cap</label>
                                <input type="text" class="form-control" id="zip" placeholder="" required>
                                <div class="invalid-feedback">Zip code required.</div>
                            </div>
                        </div>
                        <hr class="my-4">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="same-address">
                            <label class="form-check-label" for="same-address">la partita deve essere albritrata</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="save-info">
                            <label class="form-check-label" for="save-info">La partita deve essere registrata</label>
                        </div>
                        <hr class="my-4">
                        <h4 class="mb-3">Pagamento</h4>
                        <div class="my-3">
                            <div class="form-check">
                                <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked required>
                                <label class="form-check-label" for="credit">Carta di Credito</label>
                            </div>
                            <div class="form-check">
                                <input id="debit" name="paymentMethod" type="radio" class="form-check-input" required>
                                <label class="form-check-label" for="debit">Carta di debito</label>
                            </div>
                            <div class="form-check">
                                <input id="paypal" name="paymentMethod" type="radio" class="form-check-input" required>
                                <label class="form-check-label" for="paypal">PayPal</label>
                            </div>
                        </div>
                        <div class="row gy-3">
                            <div class="col-md-6">
                                <label for="cc-name" class="form-label">Nome sulla carta</label>
                                <input type="text" class="form-control" id="cc-name" placeholder="Mario Rossi" required>
                                <small class="text-body-secondary">Nome completo sulla carta</small>
                                <div class="invalid-feedback">Name on card is required</div>
                            </div>
                            <div class="col-md-6">
                                <label for="cc-number" class="form-label">Numero carta di credito</label>
                                <input type="text" class="form-control" id="cc-number" placeholder="1111 2222 3333 4444 " required>
                                <div class="invalid-feedback">Credit card number is required</div>
                            </div>
                            <div class="col-md-3">
                                <label for="cc-expiration" class="form-label">Scadenza</label>
                                <input type="text" class="form-control" id="cc-expiration" placeholder="MM/AA" required>
                                <div class="invalid-feedback">Expiration date required</div>
                            </div>
                            <div class="col-md-3">
                                <label for="cc-cvv" class="form-label">CVV</label>
                                <input type="text" class="form-control" id="cc-cvv" placeholder="111" required>
                                <div class="invalid-feedback">Security code required</div>
                            </div>
                        </div>
                        <hr class="my-4">
                      <a href="Successo.jsp">Concludi Ordine</a>  
                        <!-- <button class="w-100 btn btn-primary btn-lg" type="submit">Conferma Pagamento</button> -->
                    </form>
                </div>
            </div>
        </main>
    </div>
    
   <jsp:include page="footer.jsp"></jsp:include>
   
</body>
</html>
