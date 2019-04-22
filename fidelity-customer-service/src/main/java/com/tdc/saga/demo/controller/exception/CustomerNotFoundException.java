package com.tdc.saga.demo.controller.exception;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(final String errorMessage) {
		super(errorMessage);
	}
}
