'use strict';

angular_app.controller('FormPersonneCtrl', [
		'$scope',
		'$http',
		'$rootScope',
		function($scope, $http, $rootScope) {
			$scope.visible = false;
			$scope.selectedPersonne = {};

			$rootScope.$on(BROADCAST_EDIT_PERSONNE_ID,
					function(event, personne) {
						$scope.selectedPersonne = personne;
						$scope.visible = true;
					});

			$scope.save = function() {
				$http.post(URL_SERVER_REST + "/personne/save/",
						$scope.selectedPersonne).success(function(personne) {
					$scope.selectedPersonne = personne;
					$scope.broadCastSavePersonne();
					$scope.visible = false;
				});
			}
			
			$scope.cancel = function() {
				$scope.visible = false;	
			}

			$scope.broadCastSavePersonne = function() {
				$rootScope.$broadcast(BROADCAST_SAVE_PERSONNE_ID,
						$scope.selectedPersonne);
			}

		} ]);