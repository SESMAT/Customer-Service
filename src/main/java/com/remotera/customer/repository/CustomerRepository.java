package com.remotera.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotera.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findById(String id);

   
}
