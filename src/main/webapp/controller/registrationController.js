var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.controller('registerController', function($scope, registrationService, $location) {
	console.log("registerController");
	$scope.user = {};
	$scope.register = function() {
		var regVariable = registrationService.registeruser($scope.user);

		regVariable.then(function(response) {
			console.log(response.data.message);
			$location.path('/login')
		}, function(response) {
			$scope.errorMessage = response.data.message;
		});
	}
});

