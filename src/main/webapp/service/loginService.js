var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.factory('loginService', function($http) {
	
	
	var login = {};
	
	login.loginuser = function(user) {
		return $http({
			method : "POST",
			url : 'login',
			data : user
		});
	}
	return login;
});
