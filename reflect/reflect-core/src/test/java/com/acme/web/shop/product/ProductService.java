package com.acme.web.shop.product;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class ProductService {
	 
 	private final ProductRepository productRepository;
 
 	public ProductService(ProductRepository productRepository) {
 		this.productRepository = productRepository;
 	}
 
 	@ParameterFactory
 	public List<Product> findProduct(ProductSearchCritiria searchCritiria) {
 		return productRepository.findProduct(searchCritiria);
 	}

 	@Hidden(exceptForRoleNames="salesmanager")
	public List<Product> bestSellingProducts() {
 		return productRepository.bestSellingProducts();
	}
 
 	// other action methods...
 }
