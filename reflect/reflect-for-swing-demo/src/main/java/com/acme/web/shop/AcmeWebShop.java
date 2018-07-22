package com.acme.web.shop;

import java.util.ArrayList;
import java.util.List;

import com.acme.web.shop.email.EmailClient;
import com.acme.web.shop.payment.PaymentClient;
import com.acme.web.shop.product.ProductRepository;
import com.acme.web.shop.product.ProductService;
import com.acme.web.shop.shopingcart.ShoppingCartService;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.ui.swing.ReflecttApplicationForSwing;

@DisplayName(englishName= "ACME Web Shop")
@Description(englishDescription="ACME Web Shop for everything you need...")
public class AcmeWebShop extends ReflecttApplicationForSwing {


	public static void main(String[] commandLineArguments) {
		launch();
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
