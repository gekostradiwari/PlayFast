// Definisci una funzione per gestire il click sul pulsante "Prosegui al pagamento"
    function goToCheckoutUtentePage() {
        location.reload();
        window.location.href = 'checkout.jsp';
    }
    document.querySelector('.btn').addEventListener('click', goToCheckoutUtentePage);


