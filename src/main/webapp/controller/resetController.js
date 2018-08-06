var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.controller('resetController', function($scope, resetService, $location) {

	$scope.sendLink = function() {

		var httpSendLink = resetService.forgetpassword($scope.user);

		httpSendLink.then(function(response) {
			$location.path('resetpassword');
		});
	}

	$scope.resetPassword = function() {

		var httpReset = resetService.resetPassword($scope.user,$scope.token);
		
		httpReset.then(function(response) {
			$location.path('login');
		});
	}
});