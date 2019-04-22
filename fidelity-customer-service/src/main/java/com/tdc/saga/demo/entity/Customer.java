package com.tdc.saga.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "points", nullable = false)
	private Long points;

	public Customer() {
	}

	public Customer(Long id, String name, Long credit) {
		this.id = id;
		this.name = name;
		this.points = credit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public void decreasePoints(Long points) {
		this.points -= points;
	}

	public void addPoints(Long points) {
		this.points += points;
	}
}
