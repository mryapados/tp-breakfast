'use strict';

angular_app.controller('ListPersonneCtrl', [
		'$scope',
		'$http',
		'$rootScope',
		'$window',
		function($scope, $http, $rootScope, $window) {

			var errorHandler = function(data, status) {
				if(status == 401){
					$window.location.href = './login.html';
				}
			}
			
			$scope.list = [];
			$http.get(URL_SERVER_REST + "/personne/list/")
					.success(
					function(response) {
						$scope.list = response;
					}).error(function (data, status){
						errorHandler(data, status);
					});

			$rootScope.$on(BROADCAST_SAVE_PERSONNE_ID,
					function(event, personne) {
						$scope.updateItem(personne);
					});

			$scope.updateItem = function(personne) {
				var add = false;
				angular.forEach($scope.list, function(each, index) {
					if (each.id === personne.id) {
						$scope.list[index] = personne;
						add = true;
					}
				});
				if (!add) {
					$scope.list.push(personne);
				}
			}

			$scope.add = function() {
				$rootScope.$broadcast(BROADCAST_EDIT_PERSONNE_ID, {});
			}

			$scope.edit = function(personne) {
				$rootScope.$broadcast(BROADCAST_EDIT_PERSONNE_ID, angular
						.copy(personne));
			}

			$scope.remove = function(personne) {
				$http.get(URL_SERVER_REST + "/personne/delete/" + personne.id)
						.success(
								function(response) {
									$scope.list.splice($scope.list
											.indexOf(personne), 1);
								});
			}
		} ]);
