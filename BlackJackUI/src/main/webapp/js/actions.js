function get() {
    $.ajax({
	type : 'GET',
	dataType : 'json',
	url : '/BlackJack/blackjack',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	}
    });
}

var playerId;

function signin1() {
    $.ajax({
	type : 'GET',
	dataType : 'json',
	url : '/BlackJack/signin',
	headers : {
	    'Authorization' : "Basic " + btoa("user1:user1"),
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {
	    playerId = data.id;
	}
    });
}

function signin2() {
    $.ajax({
	type : 'GET',
	dataType : 'json',
	url : '/BlackJack/signin',
	headers : {
	    'Authorization' : "Basic " + btoa("user2:user2"),
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {
	    playerId = data.id;
	}
    });
}

function action(action) {
    $.ajax({
	type : 'POST',
	contentType : 'application/json',
	url : '/BlackJack/blackjack',
	data : JSON.stringify({
	    blackJackAction : action
	}),
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {
	    $('#gameStatus').html(data.gameStatus);
	    $.each(data.players, function(i, player) {
		if (player.id === playerId && player.playerStatus === 'WAIT') {
		    //getStatus();
		}
		
		fillTable(player);
	    });
	    fillTable(data.dealer);
	}
    });
}
function fillTable(player) {

    var $row = $('#row').clone();
    $row.attr('id', 'row_' + player.id);
    if ($('#row_' + player.id).html() === undefined) {
	$row.appendTo('#body').show();
    }
    $('#row_' + player.id + ' #name').html(player.name);
    $('#row_' + player.id + ' #points').html(player.points);
    $('#row_' + player.id + ' #cash').html(player.cash);
    $('#row_' + player.id + ' #bet').html(player.bet);
    $('#row_' + player.id + ' #status').html(player.playerStatus);
    $('#row_' + player.id + ' #result').html(player.playerResult);

    var cards = '';
    $.each(player.cards, function(i, card) {
	cards += '<div>' + card.nominal + '</div>';
    });
    $('#row_' + player.id + ' #card').html(cards);
}

function logout() {
    stopGetStatus();

    $.ajax({
	type : 'POST',
	dataType : 'json',
	url : '/BlackJack/logout',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	}
    });
}

function leave() {
    stopGetStatus();

    $.ajax({
	type : 'GET',
	dataType : 'json',
	url : '/BlackJack/blackjack/leave',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	}
    });
}

function countDown() {

    var i = 10;
    var myVar = setInterval(function() {
	$('#counter').text(--i);
	if (i < 1) {
	    clearInterval(myVar);
	}
    }, 1000);

}