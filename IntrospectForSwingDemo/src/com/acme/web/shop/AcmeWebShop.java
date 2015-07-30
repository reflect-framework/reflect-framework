package com.acme.web.shop;

import java.util.ArrayList;
import java.util.List;

import com.acme.web.shop.email.EmailClient;
import com.acme.web.shop.payment.PaymentClient;
import com.acme.web.shop.product.ProductRepository;
import com.acme.web.shop.product.ProductService;
import com.acme.web.shop.shopingcart.ShoppingCartService;

import nth.introspect.ui.swing.IntrospectApplicationForSwing;

public class AcmeWebShop extends IntrospectApplicationForSwing {

	public AcmeWebShop(String[] commandLineArguments) {
		super(commandLineArguments);
	}

	public static void main(String[] commandLineArguments) {
		new AcmeWebShop(commandLineArguments);
	}
	
	
	@Override
	public List<Class<?>> getServiceClasses() {
		List<Class<?>> serviceClasses = new ArrayList<Class<?>>();
		serviceClasses.add(ShoppingCartService.class);
		serviceClasses.add(ProductService.class);
		return serviceClasses;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		List<Class<?>> infrastructureClasses = new ArrayList<Class<?>>();
		infrastructureClasses.add(ProductRepository.class);
		infrastructureClasses.add(EmailClient.class);
		infrastructureClasses.add(PaymentClient.class);
		return infrastructureClasses;
	}

}
