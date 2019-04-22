package com.tdc.saga.demo.exception;

public class OrderCancelledException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderCancelledException(final String errorMessage) {
		super(errorMessage);
	}
}
