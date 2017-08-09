/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={}
	
	friendService.suggestedUsers=function(){
		return $http.get("http://localhost:8083/chatback/suggesteduserslist")
	}
	
	friendService.sendFriendRequest=function(toUsername){
		return $http.get("http://localhost:8083/chatback/friendrequest/"+toUsername);
	}
	 
	friendService.pendingRequests=function() {
		return $http.get("http://localhost:8083/chatback/pendingrequests")
	}
	
	friendService.updatePendingRequest=function(fromId,status) {
		return $http.put("http://localhost:8083/chatback/updatependingrequest/"+fromId+"/"+status);
	}
	
	friendService.listOfFriends=function() {
		return $http.get("http://localhost:8083/chatback/listoffriends")
	}
	
	return friendService;
})