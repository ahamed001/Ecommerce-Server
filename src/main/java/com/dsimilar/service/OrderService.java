package com.dsimilar.service;

import java.util.List;

import com.dsimilar.exception.OrderException;
import com.dsimilar.model.Address;
import com.dsimilar.model.Order;
import com.dsimilar.model.User;

public interface OrderService {

	public Order createOrder(User user, Address shippingAddress);

	public Order findOrderById(Long OrderId) throws OrderException;

	public List<Order> usersOrderHistory(Long userId);

	public Order placedOrder(Long OrderId) throws OrderException;

	public Order confirmedOrder(Long OrderId) throws OrderException;

	public Order shippedOrder(Long OrderId) throws OrderException;

	public Order deliveredOrder(Long OrderId) throws OrderException;

	public Order cancelledOrder(Long OrderId) throws OrderException;

	public List<Order> getAllOrders();

	public void deleteOrder(Long OrderId) throws OrderException;

}
