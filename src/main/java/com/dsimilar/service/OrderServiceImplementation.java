package com.dsimilar.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dsimilar.exception.OrderException;
import com.dsimilar.model.Address;
import com.dsimilar.model.Cart;
import com.dsimilar.model.CartItem;
import com.dsimilar.model.Order;
import com.dsimilar.model.OrderItem;
import com.dsimilar.model.User;
import com.dsimilar.repository.AddressRepository;
import com.dsimilar.repository.OrderItemRepository;
import com.dsimilar.repository.OrderRepository;
import com.dsimilar.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService {

	private OrderRepository orderRepository;
	private CartService cartService;
	private AddressRepository addressRepository;
	private UserRepository userRepository;
	private OrderItemRepository orderItemRepository;
	private OrderItemService orderItemService;

	public OrderServiceImplementation(OrderRepository orderRepository, CartService cartService,
			AddressRepository addressRepository, UserRepository userRepository, OrderItemRepository orderItemRepository,
			OrderItemService orderItemService) {
		this.orderRepository = orderRepository;
		this.cartService = cartService;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.orderItemRepository = orderItemRepository;
		this.orderItemService = orderItemService;
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) {

		shippingAddress.setUser(user);
		Address address = addressRepository.save(shippingAddress);
		user.getAddress().add(address);
		userRepository.save(user);

		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem item : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();

			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountPrice(item.getDiscountPrice());

			OrderItem createOrderItem = orderItemRepository.save(orderItem);

			orderItems.add(createOrderItem);
		}

		Order createdOrder = new Order();

		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountPrice(cart.getTotalDiscountPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());

		Order savedOrder = orderRepository.save(createdOrder);

		for (OrderItem item : orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}

		return savedOrder;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");

		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");

		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("SHIPPED");

		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
	}

	@Override
	public Order cancelledOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> opt = orderRepository.findById(orderId);

		if (opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("Order Does Not Exist With Id : " + orderId);
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		List<Order> orders = orderRepository.getUsersOrders(userId);

		return orders;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long OrderId) throws OrderException {
		Order order = findOrderById(OrderId);

		orderRepository.deleteById(OrderId);
	}

}
