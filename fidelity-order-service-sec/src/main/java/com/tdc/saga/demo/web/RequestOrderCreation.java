package com.tdc.saga.demo.web;

import java.io.Serializable;

public class RequestOrderCreation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long customerId;

	private Long totalPoints;

	public RequestOrderCreation() {
	}

	public RequestOrderCreation(Long customerId, Long totalPoints) {
		this.customerId = customerId;
		this.totalPoints = totalPoints;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}
}
