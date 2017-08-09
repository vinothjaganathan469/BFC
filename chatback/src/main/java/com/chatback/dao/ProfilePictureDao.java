package com.chatback.dao;

import com.chatback.model.ProfilePicture;
public interface ProfilePictureDao {

    void saveProfilePicture(ProfilePicture profilePicture);
	ProfilePicture getProfilePicture(String username);
}
