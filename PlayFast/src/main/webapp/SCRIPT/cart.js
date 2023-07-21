// Definisci una funzione per gestire il click sul pulsante "Prosegui al pagamento"
    function goToCheckoutPage() {
        location.reload();
        window.location.href = 'checkout.jsp';
    }
    document.querySelector('.btn').addEventListener('click', goToCheckoutPage);

// Definisci una funzione per gestire il click sul pulsante "Login"
    function goToSignInPage() {
        location.reload();
        window.location.href = 'sing-in.html';
    }
    document.querySelector('.dropdown-item[data-bs-theme-value="dark"]').addEventListener('click', goToSignInPage);

// Definisci una funzione per gestire il click sul pulsante "Sign up"
    function goToSignUpPage() {
        location.reload();
        window.location.href = 'sign-up.html';
    }
    document.querySelector('.dropdown-item[data-bs-theme-value="dark"]').addEventListener('click', goToSignUpPage);

