function getPagePlayer(id) {
	$.get("/teams-manager/player?id=" + id, function(data) {
		console.log(data);
		
		$("#section_title").text(data.firstName + ' ' + data.lastName);
		
		var options = '';
		$.get("/teams-manager/teams", function(datas) {
			$(datas).each(function() {
				var selected = '';
				if (this.name == data.teamName){selected = ' selected';}
				options += '<option value="' + this.id + '"' + selected + '>' + this.name + '</option>';
				selected = '';
			});
			
			var form = '' 
				+ '<form id="player_form" class="form-horizontal" role="form" method="POST" action="#">'
					+ '<div class="form-group">'
						+ '<label class="control-label col-sm-2" for="firstname">Prénom :</label>'
						+ '<div class="col-sm-10">'
							+ '<input type="text" class="form-control" id="firstname" placeholder="Prénom" value="' + data.firstName + '">'
						+ '</div>'
					+ '</div>'
				+ '<div class="form-group">'
					+ '<label class="control-label col-sm-2" for="lastname">Nom :</label>'
					+ '<div class="col-sm-10">'
						+ '<input type="text" class="form-control" id="lastname" placeholder="Nom" value="' + data.lastName + '">'
					+ '</div>'
				+ '</div>'
				+ '<div class="form-group">'
					+ '<label class="control-label col-sm-2" for="nickname">Surnom :</label>'
					+ '<div class="col-sm-10">'
						+ '<input type="text" class="form-control" id="nickname" placeholder="Surnom" value="' + data.nickName + '">'
					+ '</div>'
				+ '</div>'	
				+ '<div class="form-group">'
					+ '<label class="control-label col-sm-2" for="tshirt_number">N° Maillot :</label>'
					+ '<div class="col-sm-10">'
						+ '<input type="text" class="form-control" id="tshirt_number" placeholder="N° Maillot" value="' + data.tshirtNumber + '">'
					+ '</div>'
				+ '</div>'
				+ '<div class="form-group">'
					+ '<label class="control-label col-sm-2" for="sel_id_team">Equipe :</label>'
					+ '<div class="col-sm-10">'
						+ '<select class="form-control" id="selidteam" name="sel_id_team">'
							+ '<option value="">Choisir une équipe</option>'
							+ options 
						+ '</select>'
					+ '</div>'
				+ '</div>'
				+ '<div class="form-group">'
					+ '<div class="col-sm-offset-2 col-sm-10">'
						+ '<button id="player_add" type="submit" class="btn btn-default">Modifier</button>'
					+ '</div>'
				+ '</div>'
				+ '</form>';

				$("#pg").empty();
				$('#pg').append(form);
				
				
				
				
			
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