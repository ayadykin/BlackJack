function createGame() {
    $.ajax({
	type : 'GET',
	contentType : 'application/json',
	url : '/BlackJack/game/create',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {
	    getStatus();
	}
    });
}

function connect() {
    $.ajax({
	type : 'GET',
	contentType : 'application/json',
	url : '/BlackJack/game/connect',
	headers : {
	    'X-XSRF-TOKEN' : $.cookie("XSRF-TOKEN")
	},
	success : function(data) {
	    getStatus();
	}
    });
}