package com.tdc.saga.demo.adapter;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.tdc.saga.demo.web.RequestOrderCreation;

@Component
public class RestoreCustomerPointsAdapter extends AbstractAdapter {

	private static final Logger logger = Logger.getLogger(RestoreCustomerPointsAdapter.class.getName());

	private static final String CUSTOMER_ADD_POINTS_URI = "http://localhost:4008/customers/points/add";
	private static final String ID = "id";
	private static final String NUMBER_OF_POINTS = "numberOfPoints";

	@Override
	@SuppressWarnings("unchecked")
	public void execute(final DelegateExecution ctx) throws Exception {

		RequestOrderCreation requestOrderCreation = (RequestOrderCreation) ctx
				.getVariable(RequestOrderCreation.class.getName());

		String uri = UriComponentsBuilder.fromUriString(CUSTOMER_ADD_POINTS_URI)
				.queryParam(NUMBER_OF_POINTS, requestOrderCreation.getTotalPoints()).build()
				.expand(Collections.singletonMap(ID, requestOrderCreation.getCustomerId())).toString();

		ResponseEntity<Void> responseEntity = this.rest.patchForObject(uri, null, ResponseEntity.class);

		checkResponseEntity(responseEntity);

		logger.log(Level.INFO, "Restored points of customer id {0} ", requestOrderCreation.getCustomerId());
	}
}
