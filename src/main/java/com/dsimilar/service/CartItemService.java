package com.dsimilar.service;

import com.dsimilar.exception.CartItemException;
import com.dsimilar.exception.UserException;
import com.dsimilar.model.Cart;
import com.dsimilar.model.CartItem;
import com.dsimilar.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
	
	public void removeCartItem(Long userId, Long cartItemId)throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
	
}
