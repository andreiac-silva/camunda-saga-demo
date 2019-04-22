package com.tdc.saga.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.tdc.saga.demo.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
