package com.remotera.customer.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.remotera.customer.domain.Country;
import com.remotera.customer.domain.Customer;
import com.remotera.customer.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CustomerRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository userRepository;

	@Test
	public void saveCustomerTest() {
		// given
		Customer cust = addCustomer();

		// when
		Customer found = userRepository.findById(cust.getId());

		// then
		assertThat(found.getId()).isEqualTo(cust.getId());
	}

	private Customer addCustomer() {
		Customer cust = new Customer();
		cust.setTitle("Title");
		cust.setFirstName("first name");
		cust.setLastName("last name");
		cust.setCountry(new Country(1L));
		
		cust = entityManager.persist(cust);
		entityManager.flush();

		return cust;

	}
	@Test
	public void getAllTest() {
		addCustomer();
		List<Customer> actual = userRepository.findAll();
		assertThat(actual).hasSize(1);
	}

}
