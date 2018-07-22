package com.acme.web.shop.product;

import java.util.List;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Demo code for JavaDoc of {@link ReflectApplicationForJUnit}
 * 
 * @author nilsth
 *
 */
public class ProductServiceTest {

	private ProductService productService;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit()
				.addServiceClass(ProductService.class)
				.addInfrastructureClass(ProductRepositoryMockup.class).createContainer();
		productService = container.get(ProductService.class);
	}

	@Test
	public void bestSellingProductsTest() {
		List<Product> products = productService.bestSellingProducts();
		assertTrue(products.size()>0);
		// other asserts
	}

	// other test methods ...
}
