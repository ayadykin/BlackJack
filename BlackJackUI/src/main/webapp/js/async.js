var reload = true;
var xhrStatus;

window.onload = function() {

    window.onbeforeunload = function() {
	reload = false;
    };
};

function getCards() {
     $.ajax({
	type : 'GET',
	contentType : 'application/json',
	url : '/BlackJack/blackjack/getCards',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {

	    $.each(data.players, function(i, player) {		
		
		fillTable(player);

	    });
	    fillTable(data.dealer);

	}
    });

}

var currentPlayerStatus;
var gameStatus;
function getStatus() {
    xhrStatus = $.ajax({
	type : 'GET',
	contentType : 'application/json',
	url : '/BlackJack/blackjack/status',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {
	    $('#gameStatus').html(data.gameStatus);
	    $('#row_' + playerId + ' #status').html(data.playerStatus);
	    
	    // Player wait then step
	    if (currentPlayerStatus !== data.playerStatus || gameStatus !== data.gameStatus) {
		getCards();
	    } 

	    // End game
	    if (data.gameStatus === 'GAME_END') {
		countDown();
	    }
	    
	    //Update
	    gameStatus = data.gameStatus;
	    currentPlayerStatus = data.playerStatus;
	    
	},
	error : function() {
	    console.error('getStatus error');
	    reload = false;
	},
	complete : function() {
	    if (reload) {
		getStatus();
	    }
	}
    });

}
function stopGetStatus() {
    reload = false;
    xhrStatus.abort();
}