package com.tdc.saga.demo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdc.saga.demo.controller.exception.CustomerNotFoundException;
import com.tdc.saga.demo.controller.exception.CustomerNotSuficientPointsException;
import com.tdc.saga.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

	@Autowired
	private CustomerService customerService;

	@PatchMapping("/{id}/points/add")
	public ResponseEntity<Void> addCustomerPoints(@PathVariable("id") Long id,
			@RequestParam("numberOfPoints") Long numberOfPoints) {

		try {
			this.customerService.addPoints(id, numberOfPoints);

		} catch (CustomerNotFoundException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}/points/descrease")
	public ResponseEntity<Void> decreaseCustomerPoints(@PathVariable("id") Long id,
			@RequestParam("numberOfPoints") Long numberOfPoints) {

		try {
			this.customerService.decreasePoints(id, numberOfPoints);

		} catch (CustomerNotFoundException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return ResponseEntity.notFound().build();

		} catch (CustomerNotSuficientPointsException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}

		return ResponseEntity.ok().build();
	}
}
