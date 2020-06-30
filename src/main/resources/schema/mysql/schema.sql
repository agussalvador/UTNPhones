-- ==========================================================
--  Trabajo Practico Integrador - UTNPhones 
-- ==========================================================
CREATE DATABASE utn_phones;

USE utn_phones;
SET GLOBAL time_zone = '-3:00';

CREATE TABLE provinces(
    id_province BIGINT AUTO_INCREMENT,
    province_name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_id_province PRIMARY KEY (id_province),
    CONSTRAINT unq_province_name UNIQUE (province_name) );

CREATE TABLE cities(
    id_city BIGINT AUTO_INCREMENT,
    id_province BIGINT NOT NULL,
    city_name VARCHAR(50) NOT NULL,
    area_code VARCHAR(5) NOT NULL,
    CONSTRAINT pk_id_city PRIMARY KEY (id_city),
    CONSTRAINT fk_id_province FOREIGN KEY (id_province) REFERENCES provinces(id_province) ON DELETE CASCADE,
	CONSTRAINT unq_area_code UNIQUE (area_code) );
    
CREATE TABLE tariffs(
	id_tariff BIGINT AUTO_INCREMENT,
    id_city_origin BIGINT NOT NULL,
    id_city_destination BIGINT NOT NULL,
    cost_price DOUBLE NOT NULL DEFAULT 0,
	price DOUBLE NOT NULL DEFAULT 0,    
    CONSTRAINT pk_id_tariff PRIMARY KEY (id_tariff),
    CONSTRAINT fk_rate_per_minutes_id_city_origin FOREIGN KEY (id_city_origin) REFERENCES cities(id_city),
    CONSTRAINT fk_rate_per_minutes_id_city_destination FOREIGN KEY (id_city_destination) REFERENCES cities(id_city) );    

CREATE TABLE users(
	id_user BIGINT AUTO_INCREMENT,
	id_city BIGINT NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    dni VARCHAR(20) NOT NULL,
    pwd VARCHAR(50) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
    user_role ENUM ("client", "employee"),
    CONSTRAINT pk_id_user PRIMARY KEY (id_user),
    CONSTRAINT fk_id_city FOREIGN KEY (id_city) REFERENCES cities(id_city) ON UPDATE CASCADE,
    CONSTRAINT unq_dni UNIQUE (dni) );
    
    
CREATE TABLE telephone_lines(
	id_telephone_line BIGINT AUTO_INCREMENT,
    id_user BIGINT NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    type_line ENUM ('mobile', 'home'),
	enabled BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_id_telephone_line PRIMARY KEY (id_telephone_line),
    CONSTRAINT fk_telephone_lines_id_user FOREIGN KEY (id_user) REFERENCES users(id_user) ON UPDATE CASCADE,
    CONSTRAINT unq_phone_number UNIQUE (phone_number) );
    
CREATE TABLE bills(
    id_bill BIGINT AUTO_INCREMENT,
    id_telephone_line BIGINT NOT NULL,
    count_calls INT NOT NULL,
    cost_price DOUBLE NOT NULL DEFAULT 0,
    total_price DOUBLE NOT NULL DEFAULT 0,
    date_bill DATETIME NOT NULL,
    expirate_date DATETIME NOT NULL,
    is_pay TINYINT NOT NULL DEFAULT 0,
    CONSTRAINT pk_bills_id_bill PRIMARY KEY (id_bill),
    CONSTRAINT fk_bills_id_telephone_line FOREIGN KEY (id_telephone_line) REFERENCES telephone_lines(id_telephone_line) );

CREATE TABLE calls(
	id_call BIGINT AUTO_INCREMENT,
	id_telephone_origin BIGINT NOT NULL,
	id_telephone_destination BIGINT NOT NULL,
	id_city_origin BIGINT NOT NULL,
	id_city_destination BIGINT NOT NULL,
	billed TINYINT default 0,
	id_bill BIGINT DEFAULT 0,
    duration INT NOT NULL DEFAULT 0,
    cost_price DOUBLE NOT NULL DEFAULT 0,
    call_price DOUBLE NOT NULL DEFAULT 0,
    call_date DATETIME  NOT NULL,
    CONSTRAINT pk_calls_id_call PRIMARY KEY (id_call),
    CONSTRAINT fk_calls_id_telephone_origin FOREIGN KEY (id_telephone_origin) REFERENCES telephone_lines(id_telephone_line) ,
	CONSTRAINT fk_calls_id_telephone_destination FOREIGN KEY (id_telephone_destination) REFERENCES telephone_lines(id_telephone_line) ,
	CONSTRAINT fk_calls_id_city_origin FOREIGN KEY (id_city_origin) REFERENCES cities(id_city) ,
	CONSTRAINT fk_calls_id_city_destination FOREIGN KEY (id_city_destination) REFERENCES cities(id_city),
    CONSTRAINT fk_calls_id_bill FOREIGN KEY (id_bill) REFERENCES bills(id_bill)  );
