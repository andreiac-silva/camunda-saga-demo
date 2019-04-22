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

import com.tdc.saga.demo.web.RequestOrderCreation;

@Component
public class DebitCustomerPointsAdapter extends AbstractAdapter {

	private static final Logger logger = Logger.getLogger(DebitCustomerPointsAdapter.class.getName());

	private static final String CUSTOMER_DRECREASE_POINTS_URI = "http://localhost:4008/customers/{id}/points/descrease";
	private static final String ID = "id";
	private static final String NUMBER_OF_POINTS = "numberOfPoints";

	@Override
	public void execute(final DelegateExecution ctx) throws Exception {

		RequestOrderCreation requestOrderCreation = (RequestOrderCreation) ctx
				.getVariable(RequestOrderCreation.class.getName());

		String uri = UriComponentsBuilder.fromUriString(CUSTOMER_DRECREASE_POINTS_URI)
				.queryParam(NUMBER_OF_POINTS, requestOrderCreation.getTotalPoints()).build()
				.expand(Collections.singletonMap(ID, requestOrderCreation.getCustomerId())).toString();

		ResponseEntity<ResponseEntity<Void>> responseEntity = this.rest.exchange(uri, HttpMethod.PATCH, null,
				new ParameterizedTypeReference<ResponseEntity<Void>>() {});

		checkResponseEntity(responseEntity);

		logger.log(Level.INFO, "Debited points from customer id {0} ", requestOrderCreation.getCustomerId());
	}

}
