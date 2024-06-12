package com.dsimilar.service;

import com.dsimilar.exception.UserException;
import com.dsimilar.model.User;

public interface UserService {

	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
}
