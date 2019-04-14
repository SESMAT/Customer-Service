package com.remotera.customer.generator;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGenerator implements IdentifierGenerator {

	public static final String generatorName = "IdGenerator";

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		return UUID.randomUUID().toString();
	}
}