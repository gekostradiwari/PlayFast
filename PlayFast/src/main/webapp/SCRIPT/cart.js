// Definisci una funzione per gestire il click sul pulsante "Prosegui al pagamento"
    function goToCheckoutPage() {
        location.reload();
        window.location.href = 'Sign-in.jsp';
    }
    document.querySelector('.btn').addEventListener('click', goToCheckoutPage);


