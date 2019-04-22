package com.tdc.saga.demo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdc.saga.demo.entity.Order;
import com.tdc.saga.demo.exception.OrderCancelledException;
import com.tdc.saga.demo.exception.OrderNotFoundException;
import com.tdc.saga.demo.service.OrderService;
import com.tdc.saga.demo.web.RequestOrderCreation;
import com.tdc.saga.demo.web.ResponseOrderCreation;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private static final Logger logger = Logger.getLogger(OrderController.class.getName());

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<ResponseOrderCreation> createOrder(@RequestBody RequestOrderCreation requestOrderCreation) {

		Order savedOrder = this.orderService.save(new Order(requestOrderCreation));
		ResponseOrderCreation responseOrderCreation = new ResponseOrderCreation(savedOrder);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseOrderCreation);
	}

	@PatchMapping("/{id}/cancellation")
	public ResponseEntity<Void> cancellOrder(@PathVariable("id") Long id) {

		try {
			this.orderService.cancellOrder(id);

		} catch (OrderNotFoundException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}/approval")
	public ResponseEntity<Void> approveOrder(@PathVariable("id") Long id) {

		try {
			this.orderService.approveOrder(id);

		} catch (OrderNotFoundException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return ResponseEntity.notFound().build();

		} catch (OrderCancelledException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		return ResponseEntity.ok().build();
	}
}
