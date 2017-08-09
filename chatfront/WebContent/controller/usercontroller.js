/**
 * usercontroller for user module
 */
app.controller('UserController',function(UserService,$scope,$location,$rootScope,$cookieStore){
    
	$scope.registrationSuccess=''
  		
	
	$scope.register=function(){
		console.log('entering register function')
		console.log($scope.user.username) 
       UserService.registerUser($scope.user).then(function(response){
    	   
    	   $scope.registrationSuccess="Registered Successfully...please login .."
    	    	
    		   $location.path('/login')
       },function(response) {
    	   $scope.error=response.data;
    	   $location.path('/registration')	
       })    
    }
    
    $scope.login=function(){
    	UserService.login($scope.user).then(function(response) {
    		$rootScope.currentUser=response.data
    		$cookieStore.put("currentUser",response.data)
    		$location.path('/home')
 
    	},function(response) {
    		$scope.error=response.data
    		$location.path('/login')
        })
      } 
    
    $scope.userobj=UserService.getUserByUsername().then(function(response){
    	$scope.userobj=response.data
    },function(response) {
    	console.log(response.status)
    })
    
    $scope.update=function(){
    	UserService.updateUserProfile($scope.userobj).then(function(response){
            $scope.userobj={}
    		$scope.message="Updated the profile successfully"
    	},function(response){ 
    	  console.log(response.data)	
    	})
    }
    
    
 }) 