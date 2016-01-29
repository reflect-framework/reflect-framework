package com.acme.web.shop.product;

import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * Demo code for JavaDoc of {@link IntrospectApplicationForJUnit}
 * 
 * @author nilsth
 *
 */
public class ProductServiceTest {

	private ProductService productService;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new IntrospectApplicationForJUnit()
				.addServiceClass(ProductService.class)
				.addInfrastructureClass(ProductRepositoryMockup.class).createContainer();
		productService = container.get(ProductService.class);
	}

	@Test
	public void bestSellingProductsTest() {
		List<Product> products = productService.bestSellingProducts();
		// assert method calls ...
	}

	// other test methods ...
}
