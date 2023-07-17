		// Funzione di inizializzazione per Facebook SDK
		function initFacebookSDK() {
			FB.init({
				appId : 'il_tuo_app_id_di_facebook',
				cookie : true,
				xfbml : true,
				version : 'v12.0'
			});
		}

		// Funzione per l'autenticazione tramite Facebook
		function registerWithFacebook() {
			FB
					.login(
							function(response) {
								if (response.authResponse) {
									// L'utente ha effettuato l'autenticazione con successo
									// Esegui la registrazione nel tuo sistema
									console
											.log('Utente autenticato con Facebook');
									console.log('Access Token:',
											response.authResponse.accessToken);
								} else {
									// L'utente ha annullato l'autenticazione o si è verificato un errore
									console
											.log('Autenticazione con Facebook annullata o errore');
								}
							}, {
								scope : 'email' // Puoi specificare i permessi richiesti per l'autenticazione
							});
		}

		// Funzione per l'autenticazione tramite Twitter
		function registerWithTwitter() {
			// Esegui l'autenticazione tramite l'API di Twitter
			// Quando l'autenticazione è completata con successo, esegui la registrazione nel tuo sistema
			console.log('Autenticazione con Twitter');
			twttr.widgets
					.createSignInButton(
							function(authenticationResult) {
								if (authenticationResult
										&& !authenticationResult.error) {
									// L'utente ha effettuato l'autenticazione con successo
									// Esegui la registrazione nel tuo sistema
									console
											.log('Utente autenticato con Twitter');
									console.log('Access Token:',
											authenticationResult.oauth_token);
								} else {
									// L'utente ha annullato l'autenticazione o si è verificato un errore
									console
											.log('Autenticazione con Twitter annullata o errore');
								}
							}, {
								size : 'large' // Puoi specificare le opzioni per il pulsante di accesso a Twitter
							});
		}

		// Inizializza il Facebook SDK all'avvio della pagina
		window.fbAsyncInit = function() {
			initFacebookSDK();
		};

		// Carica in modo asincrono il Facebook SDK
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v12.0&appId=263564636370939=1";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));


function openFacebookPopup() {
  const url = 'https://www.facebook.com/dialog/oauth?client_id=263564636370939&redirect_uri=URL_DI_REINDIRIZZO&scope=email';
  const options = 'width=600,height=400';
  window.open(url, 'Facebook Login', options);
}

function openTwitterPopup() {
  const url = 'https://api.twitter.com/oauth/authenticate?client_id=il_tuo_app_id_di_twitter&redirect_uri=URL_DI_REINDIRIZZO';
  const options = 'width=600,height=400';
  window.open(url, 'Twitter Login', options);
}
