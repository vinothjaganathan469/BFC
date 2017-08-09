/**
 * blogcontroller
 */
app.controller('BlogPostController',function($scope,BlogPostService,$location){
	$scope.message=''
		$scope.saveBlogPost=function(){
		  BlogPostService.saveBlog($scope.blogPost).then(function(response){
			  $scope.success="Blog post inserted successfully and waiting for approval"
			  $location.path('/getallblogs')
		  },function(response){
			  $scope.message=response.data.message 
			  if(response.status==401){
				  
				  $location.path('/login')
		     }
			  if(response.status==500){
				  $location.path('/saveblogpost')
			  }
		   })
	    }
	/**
	 * List of blogs which are approved - value of approved=1
	 */
	
	  $scope.blogsApproved=BlogPostService.blogsApproved().then(function(response){
		  $scope.blogsApproved=response.data;
		 },function(response){
			 console.log(response.status)
		 })
	/**
	 * List of blogs which are waiting for approval - value of approved=0
	 */
	 
	 $scope.blogsWaitingForApproval=BlogPostService.blogsWaitingForApproval().then(function(response){
		  $scope.blogsWaitingForApproval=response.data;
	  },function(response){
		  console.log(response.status)
	  })

})
	 
