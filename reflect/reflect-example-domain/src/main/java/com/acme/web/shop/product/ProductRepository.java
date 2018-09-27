package com.acme.web.shop.product;

import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.fw.infrastructure.random.generator.collection.ListGenerator;

public class ProductRepository {

	private final List<Product> products;

	public ProductRepository(ProductGenerator productGenerator) {
		products = productGenerator.generateSet(300);
	}

	public List<Product> findProduct(ProductSearchCritiria searchCritiria) {
		String searchString = searchCritiria.getSearch().toLowerCase();
		return products.stream()
				.filter(p -> p.getName().toLowerCase().contains(searchString)
						|| p.getCode().toLowerCase().contains(searchString)
						|| p.getDetails().toLowerCase().contains(searchString))
				.collect(Collectors.toList());
	}

	public List<Product> bestSellingProducts() {
		return products;
	}

}
