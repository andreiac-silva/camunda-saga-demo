package com.tdc.saga.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tdc.saga.demo.enumeration.OrderStatus;
import com.tdc.saga.demo.web.RequestOrderCreation;

@Entity
@Table(name = "customer_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "total_points", nullable = false)
	private Long totalPoints;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private OrderStatus status;

	public Order() {
	}

	public Order(Long id, Long points, Long customerId) {
		this.id = id;
		this.totalPoints = points;
		this.customerId = customerId;
	}

	public Order(RequestOrderCreation requestOrderCreation) {
		this.customerId = requestOrderCreation.getCustomerId();
		this.totalPoints = requestOrderCreation.getTotalPoints();
		this.status = OrderStatus.PENDING;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public boolean isCancelled() {
		return OrderStatus.CANCELLED.equals(this.status);
	}
}