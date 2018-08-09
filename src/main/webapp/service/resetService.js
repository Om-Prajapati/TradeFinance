var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.factory('resetService', function($http, $location) {
	var reset = {};

	reset.forgetpassword = function(user) {
		return $http({
			method : "POST",
			url : 'forgetpassword',
			data : user
		})
	}

	reset.resetPassword = function(user) {
		return $http({
			method : "PUT",
			url : 'resetpassword',
			data : user
		})
	}
	return reset;
});