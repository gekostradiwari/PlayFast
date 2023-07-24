
$(document).ready(function() {
  var cartCountValue = 0;
  var cartCount = $('.cart .count');
  $(cartCount).text(cartCountValue);

  $('.random-btn').on('click', function() {
    $('.cart').offset({
      top: getRndInteger(0, window.innerHeight - 100),
      left: getRndInteger(0, window.innerWidth - 100)
    });
  });

  $('.cart-btn').on('click', function() {
    var cartBtn = this;
    var cartCountPosition = $(cartCount).offset();
    var btnPosition = $(this).offset();
    var leftPos =
      cartCountPosition.left < btnPosition.left
        ? btnPosition.left - (btnPosition.left - cartCountPosition.left)
        : cartCountPosition.left;
    var topPos =
      cartCountPosition.top < btnPosition.top
        ? cartCountPosition.top
        : cartCountPosition.top;
    $(cartBtn)
      .append("<span class='count'>1</span>");
    
    $(cartBtn).find(".count").each(function(i,count){
      $(count).offset({
        left: leftPos,
        top: topPos
      })
      .animate(
        {
          opacity: 0
        },
        800,
        function() {
          $(this).remove();
          cartCountValue++;
          $(cartCount).text(cartCountValue);
        }
      );
    }); 
  });

  function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }
});

function apriCarrello() {
        location.reload();
        window.location.href = 'checkout.jsp';
    }
    document.querySelector('#addCart').addEventListener('click', apriCarrello);
    
 
