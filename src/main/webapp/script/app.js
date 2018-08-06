var tradeFinanceApp = angular.module('tradeFinanceApp', [ 'ui.router']);

tradeFinanceApp.config(function($stateProvider, $urlRouterProvider) {
	
	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'template/login.html',
		controller : 'loginController'
	})
	.state('register', {
		url : '/register',
		templateUrl : 'template/registration.html',
		controller : 'registerController'
	})
	.state('home', {
		url : '/home',
		templateUrl : 'template/home.html',
	})
	.state('forgetpassword', {
	 	url : '/forgetpassword',
		templateUrl : 'template/forgetpassword.html',
		controller : 'resetController'
	})
	.state('resetpassword', {
	 	url : '/resetpassword',
		templateUrl : 'template/resetpassword.html',
		controller : 'resetController'
	});
	
	$urlRouterProvider.otherwise('login');
});

