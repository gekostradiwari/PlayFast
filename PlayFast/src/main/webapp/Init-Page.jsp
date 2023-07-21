<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Init</title>
<link href="./CSS/FirstPage.min.css" rel="stylesheet">
<link href="./CSS/FirstPage.css" rel="stylesheet">
<link href="./CSS/InitPage.css" rel="stylesheet">

</head>
<body style="background-image: url('./IMG/Sfondo4.svg'); background-size: cover; background-repeat: no-repeat; background-attachment: fixed;">
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<div id="booking" class="section">
		<div class="section-center">
			<div class="container">
				<div class="row">
					<div class="booking-form">
						<div class="form-header">
							<h1>PRENOTA IL TUO SPORT PREFERITO</h1>
						</div>
						<form>
							<div class="form-group">
								<input class="form-control" type="text" placeholder="Citta'">
								<span class="form-label">Citta'</span>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<input class="form-control" type="date" required> <span
											class="form-label">DATA</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<select class="form-control" required>
											<option value="" selected hidden>SPORT</option>
											<option>Basket</option>
											<option>Calcio a 5</option>
											<option>Calcio a 7</option>
											<option>Calcio a 11</option>
											<option>Padel</option>
											<option>Pallavolo</option>
											<option>Tennis</option>
										</select> <span class="select-arrow"></span>
									</div>
								</div>
								 <div class="col-md-6">
									<div class="form-group">
										<select class="form-control" required>
											<option value="" selected hidden>ORA</option>
											<option>01:00</option>
											<option>02:00</option>
											<option>03:00</option>
											<option>04:00</option>
											<option>05:00</option>
											<option>06:00</option>
											<option>07:00</option>
											<option>08:00</option>
											<option>09:00</option>
											<option>10:00</option>
											<option>12:00</option>
											<option>13:00</option>
											<option>14:00</option>
											<option>15:00</option>
											<option>16:00</option>
											<option>17:00</option>
											<option>18:00</option>
											<option>19:00</option>
											<option>20:00</option>
											<option>21:00</option>
											<option>22:00</option>
											<option>23:00</option>
											<option>00:00</option>
										</select> <span class="select-arrow"></span>
									</div>
								</div> 
							</div>
							<div class="form-btn">
								<button class="submit-btn">CERCA</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
	<script src="./SCRIPT/FirstPage.bundle.min.js"></script>
</body>
</html>
