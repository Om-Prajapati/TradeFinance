<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style/loginAndRegistration.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>  
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.19/angular-ui-router.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.19/angular-ui-router.min.js"></script>
<script type="text/javascript" src="script/app.js"></script>

<script type="text/javascript" src="controller/loginController.js"></script>
<script type="text/javascript" src="controller/registrationController.js"></script>
<script type="text/javascript" src="controller/homeController.js"></script>
<script type="text/javascript" src="controller/resetController.js"></script>


<script type="text/javascript" src="service/loginService.js"></script>
<script type="text/javascript" src="service/registrationService.js"></script>
<script type="text/javascript" src="service/homeService.js"></script>
<script type="text/javascript" src="service/resetService.js"></script>

</head>
<body ng-app="tradeFinanceApp">
	<div ui-view></div>
</body>
</html>
