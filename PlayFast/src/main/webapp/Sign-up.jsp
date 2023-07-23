<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<head>
<title>Sign-up</title>

<link href="./CSS/Singup.min.css" rel="stylesheet">
<link href="./CSS/Singup.css" rel="stylesheet">

</head>
<body>

	<div class="container">
		<div
			class="modal modal-sheet position-static d-block bg-body-secondary p-4 py-md-5"
			tabindex="-1" role="dialog" id="modalSignin">
			<div class="modal-dialog" role="document">
				<div class="modal-content rounded-4 shadow">
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<h1 class="fw-bold mb-0 fs-2">Sign up for free</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>

					<div class="modal-body p-5 pt-0">
						<form class="row g-3" method="POST" action="/PlayFast/RegistrazioneControl">
							<div class="col-12">
								<label for="floatingInput" class="form-label">Email
									address</label> 
									<input type="email" name="email" class="form-control rounded-3"
									id="floatingInput" placeholder="name@example.com">
							</div>
							<div class="col-12">
								<label for="floatingPassword" class="form-label">Password</label>
								<input type="password" name="password" class="form-control rounded-3"
									id="floatingPassword" placeholder="Password">
							</div>
							<div class="col-12">
								<label for="floatingPassword" class="form-label">Nome</label>
								<input type="text" name="nome" class="form-control rounded-3"
									id="floatingPassword" placeholder="nome">
							</div>
							<div class="col-12">
								<label for="floatingPassword" class="form-label">Cognome</label>
								<input type="text" name="cognome" class="form-control rounded-3"
									id="floatingPassword" placeholder="cognome">
							</div>
							<div class="form-group">
							<span
									class="form-label">Data nascita</span>
								<input class="form-control" name="DataNascita" type="date" required>
							</div>
							<div class="col-12">
								<button class="w-100 mb-2 btn btn-lg rounded-3 btn-primary"
									type="submit">Sign up</button>
							</div>
							 <div class="col-12">
								<small class="text-body-secondary">By clicking Sign up,
									you agree to the terms of use.</small>
							</div>
							 <!-- <div class="col-12">
								<button
									class="w-100 py-2 mb-2 btn btn-outline-secondary rounded-3"
									type="button" onclick="openFacebookPopup()">
									<svg class="bi me-1" width="16" height="16">
      <use xlink:href="#twitter" />
    </svg>
									Sign up with Facebook
								</button>
							</div> -->
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>

	<script src="./SCRIPT/Singup.bundle.min.js"></script>
	<script src="./SCRIPT/Sing-up.js"></script>
	<script src="./SCRIPT/https://connect.facebook.net/en_US/sdk.js" crossorigin="anonymous"></script>
	
</body>
</html>
