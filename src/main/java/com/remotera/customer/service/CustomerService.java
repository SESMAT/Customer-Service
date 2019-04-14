package com.remotera.customer.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.remotera.customer.domain.Customer;
import com.remotera.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Transactional
	public Customer createCustomer(Customer cust) {
		return save(cust);
	}

	private Customer save(Customer cust) {
		return repository.save(cust);
	}

	@Transactional
	public Optional<Customer> updateCustomer(Customer cust) {
		Preconditions.checkNotNull(cust);
		Preconditions.checkNotNull(cust.getId(), "Id must not be empty");

		return findCustomerById(cust.getId()).flatMap(x -> Optional.of(save(cust)));
	}

	public List<Customer> getAllCustomers() {
		return repository.findAll();
	}

	@Transactional
	public Optional<Customer> deleteCustomer(String id) {
		Preconditions.checkNotNull(id);
		return findCustomerById(id).map(x -> {
			repository.delete(x);
			return x;
		});
	}

	public Optional<Customer> findCustomerById(String id) {
		Preconditions.checkNotNull(id);
		Customer cust = repository.findById(id);
		return Optional.ofNullable(cust);
	}

}
