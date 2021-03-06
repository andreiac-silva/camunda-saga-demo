package com.tdc.saga.demo.adapter;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ApproveOrderAdapter extends AbstractAdapter {

	private static final Logger logger = Logger.getLogger(CreatePendingOrderAdapter.class.getName());

	private static final String CREATED_ORDER_ID = "createdOrderId";
	private static final String ID = "id";
	private static final String ORDERS_APPROVAL_URI = "http://localhost:4009/orders/{id}/approval/";

	@Override
	public void execute(final DelegateExecution ctx) throws Exception {

		Long createdOrderId = (Long) ctx.getVariable(CREATED_ORDER_ID);

		String uri = UriComponentsBuilder.fromUriString(ORDERS_APPROVAL_URI).build()
				.expand(Collections.singletonMap(ID, createdOrderId)).toString();

		ResponseEntity<ResponseEntity<Void>> responseEntity = this.rest.exchange(uri, HttpMethod.PATCH, null,
				new ParameterizedTypeReference<ResponseEntity<Void>>() {});

		checkResponseEntity(responseEntity);

		logger.log(Level.INFO, "Order Approved. Id {0}. Saga was finished!", createdOrderId);
	}
}
