package com.dsimilar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsimilar.exception.ProductException;
import com.dsimilar.exception.UserException;
import com.dsimilar.model.User;
import com.dsimilar.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public ResponseEntity<User> createReview(@RequestHeader("Authorization") String jwt)
			throws UserException, ProductException {

		User user = userService.findUserProfileByJwt(jwt);

		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}

}
