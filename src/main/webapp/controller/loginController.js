var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.controller('loginController',function($scope, loginService , toastr, $location) {
			
			$scope.user = {};
			$scope.login = function() {

				var loginVariable = loginService.loginuser($scope.user);
				
				loginVariable.then(function(response) {
					console.log(response.data.message);
					toastr.success('User Login successful','Login');
					localStorage.setItem('token',response.data.message)
					$location.path('/home')
				}, function(response) {
					$scope.errorMessage = response.data.message;
				});
			}
		});
