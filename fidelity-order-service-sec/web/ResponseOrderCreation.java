package com.tdc.saga.demo.web;

import java.io.Serializable;

import com.tdc.saga.demo.entity.Order;

public class ResponseOrderCreation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long orderId;

	private Long customerId;

	private Long totalPoints;

	public ResponseOrderCreation() {
	}

	public ResponseOrderCreation(Long customerId, Long orderId, Long totalPoints) {
		this.customerId = customerId;
		this.orderId = orderId;
		this.totalPoints = totalPoints;
	}

	public ResponseOrderCreation(Order order) {
		this.orderId = order.getId();
		this.customerId = order.getCustomerId();
		this.totalPoints = order.getTotalPoints();
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}

}
