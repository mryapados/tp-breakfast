$.urlParam = function(name) {
	var results = new RegExp('[\?&]' + name + '=([^&#]*)')
			.exec(window.location.href);
	if (results == null) {
		return null;
	} else {
		return results[1] || 0;
	}
}

function getPage(pg) {
	var script = "js/" + pg + ".js";
	if (pg == "players") {
		$.getScript(script).done(function() {getPagePlayers();})
	} else if (pg == "player") {
		$.getScript(script).done(function() {getPagePlayer($.urlParam('id'));})
	}
}

$(document).ready(function() {
	getPage($.urlParam('pg'));
});