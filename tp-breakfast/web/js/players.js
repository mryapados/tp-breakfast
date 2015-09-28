
function playerHtmlLine(data_line) {
	return '<tr id="playerId' + data_line.id + '">' + '<td>' + data_line.firstName + '</td>' + '<td>'
			+ data_line.lastName + '</td>' + '<td>' + data_line.nickName
			+ '</td>' + '<td>' + data_line.tshirtNumber + '</td>' + '<td>'
			+ data_line.teamName + '</td>' + '<td><a href="?pg=player&id='
			+ data_line.id + '">Edit</a> | <a onclick="PlayerRemove(' + data_line.id + ')" href="#' + data_line.id + '">Remove</a></td></tr>';
}
function getPagePlayers() {
	$("#section_title").text("Liste des joueurs");

	var page = document.getElementById('pg');
	
	var div = document.createElement('div');
	div.setAttribute("class", "sub-header");
	var inp = document.createElement('input');
	inp.setAttribute("type", "button");
	inp.setAttribute("id", "player_add_button");
	inp.setAttribute("value", "Ajouter un joueur");
	inp.setAttribute("class", "btn btn-default");
	
	var div2 = document.createElement('div');
	div2.setAttribute("id", "parent_player_form");
	
	div.appendChild(inp);
	div.appendChild(div2);
	page.appendChild(div);
	 
	$("#player_add_button").prop('value', 'Ajouter un joueur');
	$("#player_add_button").click(function() {
		
		if ($("#player_add_button").prop('value') == 'Ajouter un joueur'){
			$("#player_add_button").prop('value', 'Annuler');
			$('#parent_player_form').show();
		} else {
			$("#player_add_button").prop('value', 'Ajouter un joueur');
			$('#parent_player_form').hide();
		}

	});
	
	div = document.createElement('div');
	div.setAttribute("class", "table-responsive");

	var tbl = document.createElement('table');
	tbl.setAttribute("class", "table table-striped");

	var thd = document.createElement('thead');

	var tr = document.createElement('tr');

	var th = document.createElement('th');
	th.innerHTML = "Prénom";
	tr.appendChild(th);

	th = document.createElement('th');
	th.innerHTML = "Nom";
	tr.appendChild(th);

	th = document.createElement('th');
	th.innerHTML = "Surnom";
	tr.appendChild(th);

	th = document.createElement('th');
	th.innerHTML = "N° Maillot";
	tr.appendChild(th);

	th = document.createElement('th');
	th.innerHTML = "Equipe";
	tr.appendChild(th);

	th = document.createElement('th');
	th.innerHTML = "Outils";
	tr.appendChild(th);

	thd.appendChild(tr);

	var tbdy = document.createElement('tbody');
	tbdy.setAttribute('id', 'players');

	tbl.appendChild(thd);
	tbl.appendChild(tbdy);

	page.appendChild(tbl);
	
	getFormPagePlayer();
	
	mkTablePlayers();
}

function mkTablePlayers() {
	$.get("/teams-manager/players", function(datas) {
		$("#players").empty();
		$(datas).each(function() {
			$("#players").append(playerHtmlLine(this));
		});
	})
	.done(function() {
		//alert( "second success" );
	})
	.fail(function(err) {
		alert(JSON.parse(err.responseText).message);
	})
	.always(function() {
		//alert( "finished" );
	});
}


function PlayerRemove(playerId) {
	$.get("/teams-manager/player-remove?id=" + playerId, function() {
		$("#playerId" + playerId).css('background-color', '#428BCA').fadeOut('slow');
		})
		.done(function() {
			//alert( "second success" );
		})
		.fail(function(err) {
			alert(JSON.parse(err.responseText).message);
		})
		.always(function() {
			//alert( "finished" );
		});
}


function getFormPagePlayer() {

	var options = '';
	$.get("/teams-manager/teams", function(datas) {
		$(datas).each(function() {
			options += '<option value="' + this.id + '">' + this.name + '</option>';
		});
		
		var form = '' 
			+ '<form id="player_form" class="form-inline" role="form" method="POST" action="#">'
				+ '<div class="form-group" style="margin:0 2px 2px 0;">'
					+ '<label class="sr-only" for="firstname">Prénom</label>'
					+ '<input class="form-control" id="firstname" name="first_name" placeholder="Prénom" type="text">'
				+ '</div>'
				+ '<div class="form-group" style="margin:0 2px 2px 0;">'
					+ '<label class="sr-only" for="lastname">Nom</label>'
					+ '<input class="form-control" id="lastname" name="last_name" placeholder="Nom" type="text">'
				+ '</div>'
				+ '<div class="form-group" style="margin:0 2px 2px 0;">'
					+ '<label class="sr-only" for="nickname">Surnom</label>'
					+ '<input class="form-control" id="nickname" name="nick_name" placeholder="Surnom" type="text">'
				+ '</div>'
				+ '<div class="form-group" style="margin:0 2px 2px 0;">'
					+ '<label class="sr-only" for="tshirt_number">N° Maillot</label>'
					+ '<input class="form-control" id="tshirt_number" name="tshirt_number" placeholder="N° Maillot" type="text">'
				+ '</div>'
				+ '<div class="form-group" style="margin:0 2px 2px 0;">'
					+ '<select class="form-control" id="selidteam" name="sel_id_team">'
						+ '<option value="">Choisir une équipe</option>'
						+ options 
					+ '</select>'
				+ '</div>'
				+ '<button id="player_add" type="submit" class="btn btn-default">Creer le joueur</button>'
			+ '</form>';
			
			
			$("#parent_player_form").empty();
			
			$('#parent_player_form').append(form);
			$('#parent_player_form').hide();
		
		$('#player_add').click(function(e) {
			e.preventDefault();
			
			$.post( "/teams-manager/player-add", $("#player_form").serialize(), function(data) {
				mkTablePlayers();
				$("#player_add_button").click();
				alert( "Le joueur a été ajouté." );
			})
			.done(function() {

			})
			.fail(function(xhr, textStatus, errorThrown) {
				var message = JSON.parse(xhr.responseText).message;
	            var errors = JSON.parse(xhr.responseText).errors;
	            console.log(errors);
				alert(message + "\n" + errors);
			})
			.always(function() {

			});
			
		});
		
		
	})
	.done(function() {
		//alert( "second success" );
	})
	.fail(function(err) {
		alert(JSON.parse(err.responseText).message);
	})
	.always(function() {
		//alert( "finished" );
	});

}








