create table country (
id BIGINT not null,
name varchar(100)
);

CREATE TABLE customer (
  id varchar(255) PRIMARY KEY,
  title varchar(50) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  address varchar(255),
  landmark varchar(255),
  mobile_number varchar(50),
  email varchar(50),
  country_id BIGINT,
  notes varchar(255)
);
