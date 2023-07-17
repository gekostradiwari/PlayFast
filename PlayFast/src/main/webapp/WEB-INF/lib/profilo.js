(() => {
  'use strict'

  feather.replace({ 'aria-hidden': 'true' })

  // Graphs
  const ctx = document.getElementById('myChart')
  // eslint-disable-next-line no-unused-vars
  const myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [
        'Sunday',
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday'
      ],
      datasets: [{
        data: [
          15339,
          21345,
          18483,
          24003,
          23489,
          24092,
          12034
        ],
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      plugins: {
        legend: {
          display: false
        },
        tooltip: {
          boxPadding: 3
        }
      }
    }
  })
})()

 // Funzione per generare le righe della tabella
    function generaStoricoPrenotazioni(prenotazioni) {
      var tbody = document.getElementById("prenotazioni-body");
      tbody.innerHTML = ""; // Svuota il corpo della tabella

      // Genera le righe basate sulle prenotazioni
      prenotazioni.forEach(function(prenotazione) {
        var row = document.createElement("tr");
        var numeroPrenotazioneCell = document.createElement("td");
        var clienteCell = document.createElement("td");
        var dataCell = document.createElement("td");
        var oraCell = document.createElement("td");

        numeroPrenotazioneCell.textContent = prenotazione.numeroPrenotazione;
        clienteCell.textContent = prenotazione.cliente;
        dataCell.textContent = prenotazione.data;
        oraCell.textContent = prenotazione.ora;

        row.appendChild(numeroPrenotazioneCell);
        row.appendChild(clienteCell);
        row.appendChild(dataCell);
        row.appendChild(oraCell);
        tbody.appendChild(row);
      });
    }

    // Effettua una richiesta al backend per ottenere le prenotazioni attive
    fetch('/api/prenotazioni/attive')
      .then(response => response.json())
      .then(data => {
        // Genera lo storico delle prenotazioni attive
        generaStoricoPrenotazioni(data);
      })
      .catch(error => {
        console.error('Si è verificato un errore:', error);
      });
      
 // Funzione per generare le righe della tabella
    function generaStoricoPrenotazioni(prenotazioni) {
      var tbody = document.getElementById("prenotazioni-body");
      tbody.innerHTML = ""; // Svuota il corpo della tabella

      // Genera le righe basate sulle prenotazioni
      prenotazioni.forEach(function(prenotazione) {
        var row = document.createElement("tr");
        var numeroPrenotazioneCell = document.createElement("td");
        var clienteCell = document.createElement("td");
        var dataCell = document.createElement("td");
        var oraCell = document.createElement("td");

        numeroPrenotazioneCell.textContent = prenotazione.numeroPrenotazione;
        clienteCell.textContent = prenotazione.cliente;
        dataCell.textContent = prenotazione.data;
        oraCell.textContent = prenotazione.ora;

        row.appendChild(numeroPrenotazioneCell);
        row.appendChild(clienteCell);
        row.appendChild(dataCell);
        row.appendChild(oraCell);
        tbody.appendChild(row);
      });
    }

    // Effettua una richiesta al backend per ottenere le prenotazioni scadute
    fetch('/api/prenotazioni/scadute')
      .then(response => response.json())
      .then(data => {
        // Genera lo storico delle prenotazioni scadute
        generaStoricoPrenotazioni(data);
      })
      .catch(error => {
        console.error('Si è verificato un errore:', error);
      });
