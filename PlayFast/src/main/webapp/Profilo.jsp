<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>

<title>profilo</title>

<link href="./CSS/profilo.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./CSS/profilo.css" rel="stylesheet">
</head>
<body>
  <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
      <symbol id="check2" viewBox="0 0 16 16">
        <path
      d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z" />
      </symbol>
      <symbol id="circle-half" viewBox="0 0 16 16">
        <path
      d="M8 15A7 7 0 1 0 8 1v14zm0 1A8 8 0 1 1 8 0a8 8 0 0 1 0 16z" />
      </symbol>
      <symbol id="moon-stars-fill" viewBox="0 0 16 16">
        <path
      d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z" />
        <path
      d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z" />
      </symbol>
      <symbol id="sun-fill" viewBox="0 0 16 16">
        <path
      d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
      </symbol>
    </svg>

  <header
    class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="Init-Page.jsp">PLAY
      FAST</a>

    <button class="navbar-toggler position-absolute d-md-none collapsed"
      type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu"
      aria-controls="sidebarMenu" aria-expanded="false"
      aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <!-- <input class="form-control form-control-dark w-100 rounded-0 border-0"
      type="text" placeholder="" aria-label=""> -->
    <div class="navbar-nav">
      <div class="nav-item text-nowrap">
        <a class="nav-link px-3" href="/PlayFast/servletLogout">Log-out</a>
      </div>
    </div>
  </header>


<div class="container-fluid">
    <div class="row">
      <nav id="sidebarMenu"
        class="col-md-3 col-lg-2 d-md-block bg-body-tertiary sidebar collapse">
        <div class="position-sticky pt-3 sidebar-sticky">
          <ul class="nav flex-column">
            <li class="nav-item"><a class="nav-link active"
              aria-current="page" href="#"> <span data-feather="home"
                class="align-text-bottom"></span> Home
            </a></li>
            <li class="nav-item"><a class="nav-link" href="Profilo.jsp"> <span
                data-feather="file" class="align-text-bottom"></span>
                Prenotazioni
            </a></li>
            <li class="nav-item"><a class="nav-link" href="Catalogo.jsp"> <span
                data-feather="shopping-cart" class="align-text-bottom"></span>
                Campi
            </a></li>
            <!--<li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="users" class="align-text-bottom"></span>
              Clienti
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="bar-chart-2" class="align-text-bottom"></span>
              Reports
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="layers" class="align-text-bottom"></span>
              Integrations
            </a>
          </li>
        </ul>-->

            <h6
              class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-body-secondary text-uppercase">
              <span>Profilo</span> <a class="link-secondary" href="#"
                aria-label="Add a new report"> <span
                data-feather="plus-circle" class="align-text-bottom"></span>
              </a>
            </h6>
            <ul class="nav flex-column mb-2">
             <!-- <li class="nav-item"><a class="nav-link" href="#"> <span
                  data-feather="file-text" class="align-text-bottom"></span>
                  Impostazioni
              </a></li> --> 
              <li class="nav-item"><a class="nav-link" href="Inserisci-Campo.jsp"> <span
                  data-feather="file-text" class="align-text-bottom"></span>
                  Inserisci campo
              </a></li>


            </ul>
        </div>
      </nav>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div
          class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 class="h2"></h1>
          <div class="btn-toolbar mb-2 mb-md-0">
            <div class="btn-group me-2">
              <!--<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>-->
              <!--  <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>-->
            </div>
            <!-- <button type="button"
              class="btn btn-sm btn-outline-secondary dropdown-toggle">
              <span data-feather="calendar" class="align-text-bottom"></span>
              This week
            </button>-->
          </div>
        </div>


        </head>
        <body>
          <h1>Storico Prenotazioni Attive</h1>

          <table>
            <thead>
              <tr>
                <th>Numero Prenotazione</th>
                <th>Cliente</th>
                <th>Data</th>
                <th>Ora</th>
              </tr>
            </thead>
            <tbody id="prenotazioni-body">
            </tbody>
          </table>
        </head>
        <body>
          <h1>Storico Prenotazioni Scadute</h1>


<table>
            <thead>
              <tr>
                <th>Numero Prenotazione</th>
                <th>Cliente</th>
                <th>Data</th>
                <th>Ora</th>
              </tr>
            </thead>
            <tbody id="prenotazioni-body">
            </tbody>
          </table>

          <script src="./SCRIPT/profilo.bundle.min.js"></script>
          <script src="./SCRIPT/profilo.js"></script>
          <script src="./SCRIPT/profilo.min.js"
            integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE"
            crossorigin="anonymous"></script>
          <script src="./SCRIPT/profilo2.min.js"
            integrity="sha384-gdQErvCNWvHQZj6XZM0dNsAoY4v+j5P1XDpNkcM3HJG1Yx04ecqIHk7+4VBOCHOG"
            crossorigin="anonymous"></script>

        </body>
</html>
