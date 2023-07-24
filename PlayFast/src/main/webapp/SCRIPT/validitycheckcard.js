function isValidCreditCard(cardNumber) {
  // Rimuovi gli spazi bianchi e i caratteri non numerici dalla stringa
  cardNumber = cardNumber.replace(/\s/g, '');

  // Verifica se il numero della carta contiene solo cifre
  if (!/^\d+$/.test(cardNumber)) {
    return false; // La carta contiene caratteri non numerici
  }

  // Applica l'algoritmo di Luhn per verificare la validità del numero della carta
  let nDigits = cardNumber.length;
  let sum = 0;
  let isEven = false;

  for (let i = nDigits - 1; i >= 0; i--) {
    let digit = parseInt(cardNumber.charAt(i));

    if (isEven) {
      digit *= 2;
      if (digit > 9) {
        digit -= 9;
      }
    }

    sum += digit;
    isEven = !isEven;
  }

  return sum % 10 === 0;
}

//const cardNumber = "4539 1488 0343 6467"; 
// Inserisci il numero della carta da verificare

return isValidCreditCard(cardNumber)
