package com.tdc.saga.demo.web;

import java.io.Serializable;

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
