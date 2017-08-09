/**
 *  UserService for User module
 */
app.factory('UserService',function($http){
	var userService={}
	
	userService.registerUser=function(user){
		return $http.post("http://localhost:8083/chatback/registration",user)
	}
	
	userService.login=function(user){
		return $http.post("http://localhost:8083/chatback/login",user)
	}
	 
	userService.logout=function(){
		return $http.get("http://localhost:8083/chatback/logout")
	}
	
	userService.getUserByUsername=function(){
		return $http.get("http://localhost:8083/chatback/getuserdetails")
	}
	
	userService.updateUserProfile=function(user){
		return $http.put("http://localhost:8083/chatback/updateprofile",user)
	}
	return userService;
	})