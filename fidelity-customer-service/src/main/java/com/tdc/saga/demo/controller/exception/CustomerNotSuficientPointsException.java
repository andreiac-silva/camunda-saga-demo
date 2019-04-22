package com.tdc.saga.demo.controller.exception;

public class CustomerNotSuficientPointsException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerNotSuficientPointsException(final String errorMessage) {
		super(errorMessage);
	}
}
