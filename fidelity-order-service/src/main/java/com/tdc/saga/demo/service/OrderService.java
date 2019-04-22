package com.tdc.saga.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdc.saga.demo.entity.Order;
import com.tdc.saga.demo.enumeration.OrderStatus;
import com.tdc.saga.demo.exception.OrderCancelledException;
import com.tdc.saga.demo.exception.OrderNotFoundException;
import com.tdc.saga.demo.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order save(final Order order) {
		order.setStatus(OrderStatus.PENDING);
		return this.orderRepository.save(order);
	}

	public void cancellOrder(final Long id) throws OrderNotFoundException {
		Order order = findById(id);
		order.setStatus(OrderStatus.CANCELLED);
		this.orderRepository.save(order);
	}

	public void approveOrder(final Long id) throws OrderNotFoundException, OrderCancelledException {
		Order order = findById(id);

		if (order.isCancelled()) {
			throw new OrderCancelledException("The order was cancelled. Id: " + id);
		}

		order.setStatus(OrderStatus.APPROVED);
		this.orderRepository.save(order);
	}

	public Order findById(final Long id) throws OrderNotFoundException {
		Optional<Order> order = this.orderRepository.findById(id);

		if (order.isPresent()) {
			return order.get();
		}

		throw new OrderNotFoundException("Order does not exist. Id: " + id);
	}

}