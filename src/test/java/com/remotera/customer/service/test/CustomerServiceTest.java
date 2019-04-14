package com.remotera.customer.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.remotera.customer.CustomerApplication;
import com.remotera.customer.domain.Country;
import com.remotera.customer.domain.Customer;
import com.remotera.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CustomerServiceTest {

	
	@Autowired
	private CustomerService customerService;

	
	@Test
	public void testFindAll() {
		Customer user = prepareCustomer();
		customerService.createCustomer(user);
		List<Customer> actual = customerService.getAllCustomers();
	}

	@Test
	public void saveUserTest() {
		Customer cust = prepareCustomer();
		Customer found = customerService.createCustomer(cust);
		assertThat(found.getId().length()).isGreaterThan(0);
	}

	private Customer prepareCustomer() {
		
		Customer cust = new Customer();
		cust.setTitle("Title");
		cust.setFirstName("first name");
		cust.setLastName("last name");
		cust.setCountry(new Country(1L));
		
		return cust;

	}

}
