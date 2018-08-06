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

	reset.resetPassword = function(user,token) {
		return $http({
			method : "PUT",
			url : 'resetpassword',
			data : user,
			headers:{
				token:token
			}
		})
	}
	return reset;
});