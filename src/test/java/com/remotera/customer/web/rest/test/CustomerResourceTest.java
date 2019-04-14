package com.remotera.customer.web.rest.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.remotera.customer.CustomerApplication;
import com.remotera.customer.domain.Country;
import com.remotera.customer.domain.Customer;
import com.remotera.customer.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CustomerResourceTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	Gson gson;


	@Test
	public void saveCustomerTest() throws Exception {
		Customer cust = createCustomer(false);

		mvc.perform(post("/v1/users/").header("Origin", "*").content(gson.toJson(cust))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("id", is(cust.getId())));
	}

	@Test
	public void saveCustomerTestWithMissingFirstName() throws Exception {
		Customer cust = createCustomer(false);
		cust.setFirstName(null);

		mvc.perform(post("/v1/create/customer/").header("Origin", "*").content(gson.toJson(cust))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}
	
	
	
	private Customer createCustomer(boolean save) {

		Customer cust = new Customer();
		cust.setTitle("Title");
		cust.setFirstName("first name");
		cust.setLastName("last name");
		cust.setCountry(new Country(1L));
		
		if (save)
			cust = customerRepository.save(cust);
		return cust;

	}
}
