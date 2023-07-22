<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<meta name="viewport" content="initial-scale=1, width=device-width">
<link href="./CSS/sing-in.min.css" rel="stylesheet">  
<link href="./CSS/sign-in.css" rel="stylesheet">


<title>Sing-in</title>
</head>
<body class="text-center">

<main class="form-signin w-100 m-auto">
  <form action="/PlayFast/LoginControl" method="POST">
    <img class="mb-4" src="./IMG/logo1.png" alt="" width="100" height="auto">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
      <input type="email" name="email" class="form-control" id="floatingInput" placeholder="name@example.com">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>
    <div class="checkbox mb-3">
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-body-secondary">&copy; 2022–2023</p>
  </form>
</main>
  </body>
</html>