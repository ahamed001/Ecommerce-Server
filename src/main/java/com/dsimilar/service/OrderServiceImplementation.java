package com.dsimilar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dsimilar.exception.OrderException;
import com.dsimilar.model.Address;
import com.dsimilar.model.Order;
import com.dsimilar.model.User;
import com.dsimilar.repository.CartRepository;

@Service
public class OrderServiceImplementation implements OrderService {

	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;

	public OrderServiceImplementation(CartRepository cartRepository, CartItemService cartItemService,
			ProductService productService) {
		this.cartRepository = cartRepository;
		this.cartItemService = cartItemService;
		this.productService = productService;
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderById(Long OrderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placeOrder(Long OrderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order confirmedOrder(Long OrderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order shippedOrder(Long OrderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deliveredOrder(Long OrderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order cancelledOrder(Long OrderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long OrderId) throws OrderException {
		// TODO Auto-generated method stub

	}
}
