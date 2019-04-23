package com.tdc.saga.demo.adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tdc.saga.demo.web.RequestOrderCreation;
import com.tdc.saga.demo.web.ResponseOrderCreation;

@Component
public class CreatePendingOrderAdapter extends AbstractAdapter {

	private static final Logger logger = Logger.getLogger(CreatePendingOrderAdapter.class.getName());

	private static final String ORDERS_CREATION_URI = "http://localhost:4009/orders";
	private static final String CREATED_ORDER_ID = "createdOrderId";

	@Override
	public void execute(final DelegateExecution ctx) throws Exception {

		RequestOrderCreation requestOrderCreation = (RequestOrderCreation) ctx
				.getVariable(RequestOrderCreation.class.getName());

		ParameterizedTypeReference<ResponseOrderCreation> parameterizedTypeReference = new ParameterizedTypeReference<ResponseOrderCreation>() {};

		ResponseEntity<ResponseOrderCreation> responseEntity = this.rest.exchange(ORDERS_CREATION_URI, HttpMethod.POST,
				new HttpEntity<>(requestOrderCreation), parameterizedTypeReference);

		checkResponseEntity(responseEntity);

		Long orderId = responseEntity.getBody().getOrderId();

		if (orderId == null) {
			addError();
			return;
		}

		ctx.setVariable(CREATED_ORDER_ID, orderId);
		logger.log(Level.INFO, "Pending Order Created. Id {0}. ", orderId);
	}
}
