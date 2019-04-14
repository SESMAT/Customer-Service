package com.remotera.customer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.remotera.customer.generator.IdGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name="customer")
@EqualsAndHashCode(of = "id", callSuper = false)
public class Customer {

	@Id
	@GeneratedValue(generator = IdGenerator.generatorName)
	@GenericGenerator(name = IdGenerator.generatorName, strategy = "com.remotera.customer.generator.IdGenerator")
	private String id;

	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column
	private String address;
	@Column
	private String landmark;
	@Column
	private String mobileNumber;
	@Column
	private String email;
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	@Column
	private String notes;
	

}
