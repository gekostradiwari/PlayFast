// Definisci una funzione per gestire il click sul pulsante "Login"
    function goToSignInPage() {
        location.reload();
        window.location.href = 'Sign-in.jsp';
    }
    document.querySelector('.dropdown-item[data-bs-theme-value="dark1"]').addEventListener('click', goToSignInPage);
    
    // Definisci una funzione per gestire il click sul pulsante "Sign up"
    function goToSignUpPage() {
        location.reload();
        window.location.href = 'Sign-up.jsp';
    }
    document.querySelector('.dropdown-item[data-bs-theme-value="dark"]').addEventListener('click', goToSignUpPage);