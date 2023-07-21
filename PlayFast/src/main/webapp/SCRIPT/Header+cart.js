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
    
      // Definisci una funzione per gestire il click sul pulsante "cart"
    function goToCartPage() {
        location.reload();
        window.location.href = 'cart.jsp';
    }
    document.querySelector('.dropdown-item[data-bs-theme-value="dark2"]').addEventListener('click', goToCartPage);