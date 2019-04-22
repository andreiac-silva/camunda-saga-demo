package com.tdc.saga.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.tdc.saga.demo.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
