var tradeFinanceApp = angular.module('tradeFinanceApp');

tradeFinanceApp.controller('loginController',function($scope, loginService , toastr, $location) {
			
			$scope.user = {};
			$scope.login = function() {

				var loginVariable = loginService.loginuser($scope.user);
				
				loginVariable.then(function(response) {
					console.log(response.data.message);
				   $scope.resp = response.data.message;
					
					if("Your account is not activate" == $scope.resp){
						toastr.success(response.data.message,'Login');
					}else if("Your email is not register" == $scope.resp){
						toastr.success(response.data.message,'Login');
					}else if("Your password is invalid" == $scope.resp){
						toastr.success(response.data.message,'Login');
					}
					else{
						localStorage.setItem('token',response.data.message)
						$location.path('/home')
						toastr.success('Login successfull','Login');
					}
				}, function (response) {
					toastr.success(response.data.message,'Login');
					console.log(response.data.message);
					$scope.errorMessage = response.data.message;
				});
			}
		});
