package com.ms.product.ms_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // Exclude DataSourceAutoConfiguration if not using a database
@SpringBootApplication()
public class MsProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductApplication.class, args);
		System.out.println("Product Service is running...");
	}

}
