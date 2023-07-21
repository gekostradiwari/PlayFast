
    // Funzione di validazione del form
    function validaForm() {
      var nomeInput = document.getElementById("nome");
      var cognomeInput = document.getElementById("cognome");
      var emailInput = document.getElementById("email");
	  var passwordInput = document.getElementById("password");
	  
      // Controlla se il nome è vuoto
      if (nomeInput.value.trim() === "") {
        alert("Inserisci un nome valido.");
        return false;
      }

      // Controlla se il cognome è vuoto
      if (cognomeInput.value.trim() === "") {
        alert("Inserisci un cognome valido.");
        return false;
      }

      // Controlla se l'email è vuota e valida il formato email
      var emailFormat = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (emailInput.value.trim() === "" || !emailFormat.test(emailInput.value)) {
        alert("Inserisci un indirizzo email valido.");
        return false;
      }
      
       if (passwordInput.value.trim() === "") {
        alert("Inserisci un password valida.");
        return false;
      }

      // Se tutti i campi sono validi, il form verrà inviato al server
      return true;
    }

    // Assegna la funzione di validazione al form quando viene inviato
    document.getElementById("profileForm").onsubmit = validaForm;



    function salvaDati() {
      var nome = document.getElementById("nome").value;
      var cognome = document.getElementById("cognome").value;
      var email = document.getElementById("email").value;
	  var password = document.getElementById("password").value;
      // Crea l'oggetto dati da inviare al server
      var dati = {
        nome: nome,
        cognome: cognome,
        email: email,
        password: password
      };

      // Effettua la richiesta al server tramite Fetch API
      fetch('/salva_dati.php', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(dati)
      })
      .then(response => response.json())
      .then(data => {
        // Ricevi la risposta dal server
        console.log(data);
        // Puoi fare qualcosa con la risposta, ad esempio mostrare un messaggio all'utente
        alert(data.message);
      })
      .catch(error => {
        // In caso di errore nella richiesta
        console.error('Errore:', error);
      });
    }