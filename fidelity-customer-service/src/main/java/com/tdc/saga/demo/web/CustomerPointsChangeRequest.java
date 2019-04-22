package com.tdc.saga.demo.web;

import java.io.Serializable;

public class CustomerPointsChangeRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long customerId;

	private Long points;

	public CustomerPointsChangeRequest(Long customerId, Long points) {
		this.customerId = customerId;
		this.points = points;
	}

	public CustomerPointsChangeRequest() {
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}
}
