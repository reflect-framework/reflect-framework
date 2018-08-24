package com.acme.web.shop.product;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

@FontIcon(fontIconUrl=FontAwesomeUrl.PRODUCT_HUNT)
public class ProductService {
	 
 	private final ProductRepository productRepository;
 
 	public ProductService(ProductRepository productRepository) {
 		this.productRepository = productRepository;
 	}
 
 	
 	@FontIcon(fontIconUrl=FontAwesomeUrl.SEARCH)
 	public List<Product> findProduct(ProductSearchCritiria searchCritiria) {
 		return productRepository.findProduct(searchCritiria);
 	}

 	@FontIcon(fontIconUrl=FontAwesomeUrl.BALANCE_SCALE)
 	@Hidden(exceptForRoleNames="salesmanager")
	public List<Product> bestSellingProducts() {
 		return productRepository.bestSellingProducts();
	}
 
 	// other action methods...
 }
