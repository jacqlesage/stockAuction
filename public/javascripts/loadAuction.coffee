a = "hi"

alert a
$ ->
  alert "test"
  $.get "/showCurrentAuction", (currentAuction) ->
    $.firstElementChild(currentAuction) ->
      $('#test').append $("<li>").text currentAuction.name