package com.dsimilar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsimilar.exception.CartItemException;
import com.dsimilar.exception.UserException;
import com.dsimilar.model.CartItem;
import com.dsimilar.model.User;
import com.dsimilar.response.ApiResponse;
import com.dsimilar.service.CartItemService;
import com.dsimilar.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException {

		User user = userService.findUserProfileByJwt(jwt);

		cartItemService.removeCartItem(user.getId(), cartItemId);

		ApiResponse res = new ApiResponse();
		res.setMessage("Item Deleted Successfully");
		res.setStatus(true);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException {

		User user = userService.findUserProfileByJwt(jwt);

		CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

		return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
	}
}
