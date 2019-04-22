package com.tdc.saga.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdc.saga.demo.controller.exception.CustomerNotFoundException;
import com.tdc.saga.demo.controller.exception.CustomerNotSuficientPointsException;
import com.tdc.saga.demo.entity.Customer;
import com.tdc.saga.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public void addPoints(final Long id, final Long points) throws CustomerNotFoundException {

		Customer customer = findById(id);
		customer.addPoints(points);
		this.customerRepository.save(customer);
	}

	public void decreasePoints(final Long id, final Long points)
			throws CustomerNotFoundException, CustomerNotSuficientPointsException {

		Customer customer = findById(id);

		if (customer.getPoints() < points) {
			throw new CustomerNotSuficientPointsException(
					String.format("Customer %s does not have suficient points. Total customer points: %s.",
							customer.getName(), customer.getPoints()));
		}

		customer.decreasePoints(points);
		this.customerRepository.save(customer);
	}

	public Customer findById(final Long id) throws CustomerNotFoundException {
		Optional<Customer> customer = this.customerRepository.findById(id);

		if (customer.isPresent()) {
			return customer.get();
		}

		throw new CustomerNotFoundException("Customer not found! Id: " + id);
	}
}