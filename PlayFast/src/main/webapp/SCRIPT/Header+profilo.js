// Definisci una funzione per gestire il click sul pulsante "Login"
    function goToProfiloPage() {
        location.reload();
        window.location.href = 'Profilo-Utente.jsp';
    }
    document.querySelector('.dropdown-item[data-bs-theme-value="dark"]').addEventListener('click', goToProfiloPage);
    
       // Definisci una funzione per gestire il click sul pulsante "cart"
    function goToCartPage() {
        location.reload();
        window.location.href = 'cart.jsp';
    }
    document.querySelector('.dropdown-item[data-bs-theme-value="dark2"]').addEventListener('click', goToCartPage);