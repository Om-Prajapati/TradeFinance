var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.controller('loginController',function($scope, loginService , $location) {
			
			$scope.user = {};
			$scope.login = function() {

				var loginVariable = loginService.loginuser($scope.user);
				
				loginVariable.then(function(response) {
					console.log(response.data.message);
					console.log(response.data.user);
					$location.path('/home')
					
					//localStorage.setItem('token',response.data.message
				}, function(response) {
					$scope.errorMessage = response.data.message;
				});
			}
		});
