/**
 * BlogPostService for Blog module
 */
app.factory('BlogPostService',function($http){
	var blogPostService={}
	
	blogPostService.saveBlog=function(blogPost){
		return $http.post("http://localhost:8083/chatback/saveblogpost",blogPost)
	}
	
	blogPostService.blogsApproved=function(){
		return $http.get("http://localhost:8083/chatback/listofblogs/"+1)
	}
	 
	blogPostService.blogsWaitingForApproval=function() {
		return $http.get("http://localhost:8083/chatback/listofblogs/"+0)
	}
	blogPostService.getBlogPost=function(id) {
		return $http.get("http://localhost:8083/chatback/getblogpost/"+id)
	}
	blogPostService.updateBlogPost=function(blogpost) {
		return $http.put("http://localhost:8083/chatback/updateblogpost",blogpost)
	}
	blogPostService.addComment=function(blogComment) {
		return $http.post("http://localhost:8083/chatback/addblogcomment",blogComment)
	}
	 blogPostService.getBlogComments=function(blogId) {
		return $http.get("http://localhost:8083/chatback/getblogcomments/"+blogId);
	}
	return blogPostService;
})