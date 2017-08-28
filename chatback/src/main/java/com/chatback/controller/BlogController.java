package com.chatback.controller;
 //creating a package for blog controllr

import java.util.Date;
// Date class available in java.util package, this class encapsulates the current date and time.

import java.util.List;
  //List Interface is the subinterface of Collection.
  //It contains methods to insert and delete elements in index basis.It is a factory of ListIterator interface.

import javax.servlet.http.HttpSession;
//this class to use bind objects view and manipulate information about a session such as the session identifier,
// creation time, and last accessed time.

import org.springframework.beans.factory.annotation.Autowired;
 // Marks a constructor, field, setter method or config method as to be autowired by Spring's dependency injection facilities.

import org.springframework.http.HttpStatus;
// Marks a method or exception class with the status code() and reason() that should be returned.

     //  1	1xx Informational responses
     //  2	2xx Success
     //  3	3xx Redirection
     //  4	4xx Client errors
     //  5	5xx Server errors

import org.springframework.http.ResponseEntity;
//Extension of HttpEntity that adds a HttpStatus status code. Used in RestTemplate as well @Controller methods.
//In RestTemplate, this class is returned by getForEntity() and exchange():


import org.springframework.web.bind.annotation.RequestBody;
//Annotation indicating a method parameter should be bound to the body of the web request. 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.chatback.dao.BlogPostDao;
import com.chatback.model.BlogPost;
import com.chatback.model.BlogComment;
import com.chatback.model.Users;
import com.chatback.model.Error;

@RestController("blogcontroller")
public class BlogController {
	@Autowired
	private BlogPostDao blogPostDao;   
	@RequestMapping(value="/saveblogpost",method=RequestMethod.POST) 
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost,HttpSession session) {
		 Users users=(Users)session.getAttribute("user");
		  if(users==null) {
			  Error error=new Error(3,"UnAuthorized user");
			  return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		  } 
	      try {
	    	  blogPost.setPostedOn(new Date());
			  blogPost.setCreatedBy(users);
	    	  blogPostDao.saveBlogPost(blogPost);
			  return new ResponseEntity<Void>(HttpStatus.OK);
	    	  
	      }catch(Exception e) {
	    	  Error error=new Error(2,"Cannot insert blog post details");
	    	  return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	      }
		  
	}
	 
	@RequestMapping(value="/listofblogs/{approved}",method=RequestMethod.GET)
	  public ResponseEntity<?> getAllBlogs(@PathVariable int approved,HttpSession session) {
		  Users users=(Users)session.getAttribute("user");
		   if(users==null) {
			   Error error=new Error(3,"UnAuthorized user");
			   return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		   }
		       List<BlogPost> blogPosts=blogPostDao.getAllBlogs(approved);
			   return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
			   
		   }
    
	 @RequestMapping(value="/getblogpost/{id}",method=RequestMethod.GET)
     public ResponseEntity<?> getBlogPost(@PathVariable int id,HttpSession session) {
	  Users users=(Users)session.getAttribute("user");
	  if(users==null) {
		  Error error=new Error(3,"UnAuthorized user");
		  return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		 }
	    BlogPost blogPost=blogPostDao.getBlogById(id);
	    return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	  }
	 
	 @RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
	  public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session) {
		  Users users=(Users)session.getAttribute("user");
		  if(users==null) {
			  Error error=new Error(3,"UnAuthorized user");
			  return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			 }
		  blogPostDao.updateBlogPost(blogPost);
		  return new ResponseEntity<Void>(HttpStatus.OK);
		  }
	
	 @RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
	  public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session) {
		  Users users=(Users)session.getAttribute("user");
		  if(users==null) {
			  Error error=new Error(3,"UnAuthorized user");
			  return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			 }
		  try {
			 blogComment.setCommentedBy(users);
			 blogComment.setCommentedOn(new Date());
			 blogPostDao.addComment(blogComment);
			 return new ResponseEntity<Void>(HttpStatus.OK);
		  }
		  catch(Exception e) {
			  Error error=new Error(4,"Unable to add comment"+ e.getMessage());
			  return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	  }
	    @RequestMapping(value="/getblogcomments/{blogId}",method=RequestMethod.GET)
	    public ResponseEntity<?> getBlogComments(@PathVariable int blogId,HttpSession session) {
	    	System.out.println("ENTERING GETBLOGCOMMENTS");
	    	 Users users=(Users)session.getAttribute("user");
			  if(users==null) {
				  Error error=new Error(3,"UnAuthorized user");
				  return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			try 
			        {
				List<BlogComment> blogComments=blogPostDao.getBlogComments(blogId);
				System.out.println(blogComments.size());
				return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
			        }
		          catch(Exception e) 
			        {
				  System.out.println(e.getMessage());
				  return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			         }
				
	         }
	 
	 
	}

		  
	
 

