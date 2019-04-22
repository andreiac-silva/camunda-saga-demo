package com.tdc.saga.demo.controller;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.builder.ProcessBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdc.saga.demo.adapter.ApproveOrderAdapter;
import com.tdc.saga.demo.adapter.CancellOrderAdapter;
import com.tdc.saga.demo.adapter.CreatePendingOrderAdapter;
import com.tdc.saga.demo.adapter.DebitCustomerPointsAdapter;
import com.tdc.saga.demo.adapter.RestoreCustomerPointsAdapter;
import com.tdc.saga.demo.web.RequestOrderCreation;

@RestController("/orders-sec")
public class OrderControllerSec {

	private static final String ORDER_BPMN = "order.bpmn";
	private static final String FLOW = "order";

	@Autowired
	private ProcessEngine camunda;

	@PostMapping
	public ResponseEntity<Void> createOrder(@RequestBody final RequestOrderCreation requestOrderCreation) {

		ProcessBuilder flow = Bpmn.createExecutableProcess(FLOW);

		flow.startEvent() //
				.serviceTask("CreatePedingOrder").name("Create Pending Order").camundaAsyncBefore()
				.camundaClass(CreatePendingOrderAdapter.class).boundaryEvent().compensateEventDefinition()
				.compensateEventDefinitionDone().compensationStart().serviceTask("CancellOrder")
				.camundaClass(CancellOrderAdapter.class).compensationDone()

				.serviceTask("DebitCustomerPoints").name("Debit Customer Points").camundaAsyncBefore()
				.camundaClass(DebitCustomerPointsAdapter.class).boundaryEvent().compensateEventDefinition()
				.compensateEventDefinitionDone().compensationStart().serviceTask("RestoreCustomerPoints")
				.camundaClass(RestoreCustomerPointsAdapter.class).compensationDone()

				.serviceTask("ApproveOrder").camundaAsyncBefore().name("Approve Order")
				.camundaClass(ApproveOrderAdapter.class).endEvent();

		flow.eventSubProcess().startEvent().error(Throwable.class.getCanonicalName()).intermediateThrowEvent()
				.compensateEventDefinition().compensateEventDefinitionDone().endEvent();

		this.camunda.getRepositoryService().createDeployment().addModelInstance(ORDER_BPMN, flow.done()).deploy();

		this.camunda.getRuntimeService().startProcessInstanceByKey(FLOW,
				Variables.putValue(RequestOrderCreation.class.getName(), requestOrderCreation));

		return ResponseEntity.accepted().build();
	}
}
