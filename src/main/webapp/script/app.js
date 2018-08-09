var tradeFinanceApp = angular.module('tradeFinanceApp', [ 'ui.router','toastr']);

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
	.state('info', {
		url : '/info',
		templateUrl : 'template/info.html',
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

