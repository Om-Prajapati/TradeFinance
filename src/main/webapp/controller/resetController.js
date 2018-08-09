var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.controller('resetController', function($scope, resetService,toastr, $location) {

	$scope.sendLink = function() {

		var httpSendLink = resetService.forgetpassword($scope.user);
		
		httpSendLink.then(function(response) {
			toastr.success(response.data.message,'Forgot Password');
			$location.path('info');
		});
	}

	$scope.resetPassword = function() {

		var httpReset = resetService.resetPassword($scope.user);
		
		httpReset.then(function(response) {
			toastr.success(response.data.message,'Reset Password');
			$location.path('login');
		});
	}
});