// Funzione per gestire l'invio dei dati della form al database
    function inviaDatiAlDatabase(event) {
        event.preventDefault(); // Previeni l'invio del form tradizionale

        const form = document.getElementById("campoForm");
        const formData = new FormData(form);

        // Invio dei dati al server utilizzando il metodo Fetch
        fetch("/api/salva-dati-campo", {
            method: "POST",
            body: formData,
        })
        .then(response => {
            if (response.ok) {
                alert("Dati inviati correttamente al database!");
                // Puoi effettuare altre azioni qui, come reindirizzamento a una pagina di conferma, ecc.
            } else {
                alert("Si è verificato un errore durante l'invio dei dati.");
            }
        })
        .catch(error => {
            console.error("Si è verificato un errore:", error);
            alert("Si è verificato un errore durante l'invio dei dati.");
        });
    }

    // Ascolta l'evento di submit della form e chiama la funzione di invio dei dati
    const formElement = document.getElementById("campoForm");
    formElement.addEventListener("submit", inviaDatiAlDatabase);