package com.tdc.saga.demo.adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractAdapter implements JavaDelegate {

	@Autowired
	protected RestTemplate rest;

	private static final Logger logger = Logger.getLogger(AbstractAdapter.class.getName());
	private static final String ERROR = "Error";

	protected void checkResponseEntity(final ResponseEntity<?> responseEntity) {

		if (responseEntity.getStatusCode().isError()) {
			addError();
			logger.log(Level.SEVERE, "Error executing rest call of adapter {0}", getAdapterName());
		}
	}

	protected void addError() {
		String adapterName = getAdapterName();
		throw new BpmnError(adapterName.concat(ERROR));
	}

	private String getAdapterName() {
		return this.getClass().asSubclass(this.getClass()).getSimpleName();
	}
}
