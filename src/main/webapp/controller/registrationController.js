var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.controller('registerController', function($scope, registrationService, toastr, $location) {
	console.log("registerController");
	$scope.user = {};
	$scope.register = function() {
		var regVariable = registrationService.registeruser($scope.user);

		regVariable.then(function(response) {
			toastr.success(response.data.message,'Registration');
			console.log(response.data.message);
			$location.path('/login')
		}, function(response) {
			$scope.errorMessage = response.data.message;
		});
	}
});

