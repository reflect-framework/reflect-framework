package com.acme.web.shop.product;

import java.util.List;

public class ProductService {
	 
 	private final ProductRepository productRepository;
 
 	public ProductService(ProductRepository productRepository) {
 		this.productRepository = productRepository;
 	}
 
 	public List<Product> findProduct(ProductSearchCritiria searchCritiria) {
 		return productRepository.findProduct(searchCritiria);
 	}
 
 	// other action methods...
 }
