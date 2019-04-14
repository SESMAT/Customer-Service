package com.remotera.customer.web.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.remotera.customer.domain.Customer;
import com.remotera.customer.dto.CustomerDto;
import com.remotera.customer.service.CustomerService;

@RestController
@RequestMapping("/v1")
public class CustomerManagmentController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Mapper mapper;

	@PostMapping("/create/customer")
	public CustomerDto createCustomer(HttpServletRequest request, @Valid @RequestBody CustomerDto cust) {
		return toResponse(customerService.createCustomer(toModel(cust)));
	}

	@PutMapping("/customer/{id}")
	public CustomerDto updateCustomer(HttpServletRequest request, @PathVariable("id") String id,
			@Valid @RequestBody CustomerDto cust) {
		cust.setId(id);
		return toResponse(customerService.updateCustomer(toModel(cust)));
	}

	@GetMapping("/customers")
	public List<CustomerDto> getAllCustomers() {
		return toResponse(customerService.getAllCustomers());
	}

	@GetMapping("/customer/{id}")
	public CustomerDto getCustomer(@PathVariable("id") String id) {
		return toResponse(customerService.findCustomerById(id));
	}

	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable("id") String id) {
		customerService.deleteCustomer(id);
	}

	private Customer toModel(CustomerDto source) {
		Customer model = mapper.map(source, Customer.class);
		return model;
	}

	private CustomerDto toResponse(Optional<Customer> source) {
		return source.isPresent() ? toResponse(source.get()) : null;
	}

	private CustomerDto toResponse(Customer source) {
		Preconditions.checkNotNull(source);
		return mapper.map(source, CustomerDto.class);
	}

	private List<CustomerDto> toResponse(List<Customer> source) {
		return source == null ? null : source.stream().map(this::toResponse).collect(Collectors.toList());
	}

}
