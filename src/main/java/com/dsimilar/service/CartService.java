package com.dsimilar.service;

import com.dsimilar.exception.ProductException;
import com.dsimilar.model.Cart;
import com.dsimilar.model.User;
import com.dsimilar.request.AddItemRequest;

public interface CartService {

	public Cart	createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
