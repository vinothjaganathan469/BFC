package com.chatback.dao;

import java.util.List;

import com.chatback.model.Friend;
import com.chatback.model.Users;

public interface FriendDao {
	List<Users> listOfSuggestedUsers(String username);
	void friendRequest(String fromUsername,String toUsername);
	List<Friend> listOfPendingRequests(String loggedInUsername);
    void updatePendingRequest(String fromId,String toId,char status);
	List<Friend> listOfFriends(String username);
	 
}
