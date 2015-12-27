package com.acme.web.shop.product;

import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.generic.util.ClassList;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * Demo code for JavaDoc of {@link IntrospectApplicationForJUnit}
 * @author nilsth
 *
 */
public class ProductServiceTest {

	private ProductService productService;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {

			@Override
			public List<Class<?>> getServiceClasses() {
				return new ClassList(ProductService.class);
			}

			@Override
			public List<Class<?>> getInfrastructureClasses() {
				return new ClassList(ProductRepositoryMockup.class);
			}
		};
		DependencyInjectionContainer container = application.createContainer();
		productService = container.get(ProductService.class);
	}

	@Test
	public void bestSellingProductsTest() {
		List<Product> products = productService.bestSellingProducts();
		// assert method calls ...
	}

	// other test methods ...
}
