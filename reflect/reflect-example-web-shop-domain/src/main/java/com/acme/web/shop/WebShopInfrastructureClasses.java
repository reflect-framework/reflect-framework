package com.acme.web.shop;

import java.util.Arrays;
import java.util.List;

import com.acme.web.shop.email.EmailClient;
import com.acme.web.shop.payment.PaymentClient;
import com.acme.web.shop.product.ProductGenerator;
import com.acme.web.shop.product.ProductRepository;

public class WebShopInfrastructureClasses {

	private static List<Class<? extends Object>> infrastructureClasses;

	static {
		infrastructureClasses = Arrays.asList(ProductRepository.class, ProductGenerator.class, EmailClient.class,
				PaymentClient.class);
	}

	public static List<Class<?>> get() {
		return infrastructureClasses;
	}
}
