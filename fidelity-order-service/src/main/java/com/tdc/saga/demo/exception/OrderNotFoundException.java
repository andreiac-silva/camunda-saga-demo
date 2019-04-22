package com.tdc.saga.demo.exception;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(final String errorMessage) {
		super(errorMessage);
	}
}
