package com.tdc.saga.demo.web;

import java.io.Serializable;

public class RequestCustomerPointsChange implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long customerId;

	private Long points;

	public RequestCustomerPointsChange(Long customerId, Long points) {
		this.customerId = customerId;
		this.points = points;
	}

	public RequestCustomerPointsChange() {
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
