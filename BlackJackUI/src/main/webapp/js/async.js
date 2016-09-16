var reload = true;
var getCardsReload = true;
var xhrStatus;
var xhrCards;

window.onload = function() {

    window.onbeforeunload = function() {
	reload = false;
    };
};
function getCards() {
    xhrCards = $.ajax({
	type : 'GET',
	contentType : 'application/json',
	url : '/BlackJack/blackjack/getCards',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {

	    $.each(data.players, function(i, player) {
		if (player.id === playerId && player.playerStatus === 'STEP') {
		    // getStatus();
		    getCardsReload = false;
		}
		if(data.blackJackResponce === 'GAME_START' && player.id === playerId && player.playerStatus === 'WAIT'){
		    getStatus();
		    getCardsReload = false;
		}
		if(data.blackJackResponce === 'GAME_END'){
		    getCardsReload = false;
		}else{
		    fillTable(player);
		}
		$('#row_' + player.id + ' #resp').html(data.blackJackResponce);
		
	    });
	    fillTable(data.dealer);

	},
	error : function() {
	    console.error('getCards error');
	    getCardsReload = false;
	},
	complete : function() {
	    if (getCardsReload) {
		getCards();
	    }
	}
    });

}
function getStatus() {
    xhrStatus = $.ajax({
	type : 'GET',
	contentType : 'application/json',
	url : '/BlackJack/blackjack/status',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {
	    if (data.value === 'STEP') {
		reload = false;
		getCards();
	    }
	    if (data.value === 'INIT') {
		reload = false;
		getCards();
		getCardsReload = false;
	    }
	    $('#row_' + playerId + ' #status').html(data.value);
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