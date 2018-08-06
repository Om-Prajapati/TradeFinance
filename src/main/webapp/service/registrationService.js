var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.factory('registrationService', function($http) {
	var register = {};
	
	register.registeruser = function(user) {
		return $http({
			method : "POST", 
			url : 'register',
			data : user
		});
	}
	return register;
});
