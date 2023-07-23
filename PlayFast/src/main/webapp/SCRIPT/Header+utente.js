// Definisci una funzione per gestire il click sul pulsante "Login"
    function goToProfiloPage() {
        location.reload();
        window.location.href = 'Profilo-Utente.jsp';
    }
    document.querySelector('.dropdown-item[data-bs-theme-value="dark"]').addEventListener('click', goToProfiloPage);