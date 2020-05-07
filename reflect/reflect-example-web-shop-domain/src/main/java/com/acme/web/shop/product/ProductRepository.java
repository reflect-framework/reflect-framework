package com.acme.web.shop.product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {

	private final List<Product> products;

	public ProductRepository(ProductGenerator productGenerator) {
		products = productGenerator.generateList(20);
	}

	public List<Product> findProduct(ProductSearchCritiria searchCritiria) {
		String searchString = (searchCritiria.getSearch() == null) ? "" : searchCritiria.getSearch().toLowerCase();
		return products.stream()
				.filter(p -> (p.getName() != null && p.getName().toLowerCase().contains(searchString))
						|| (p.getCode() != null && p.getCode().toLowerCase().contains(searchString))
						|| (p.getDetails() != null && p.getDetails().toLowerCase().contains(searchString)))
				.collect(Collectors.toList());
	}

	public List<Product> allProducts() {
		return products;
	}

}
