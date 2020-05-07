package com.acme.web.shop;

import java.util.Arrays;
import java.util.List;

import com.acme.web.shop.product.ProductService;
import com.acme.web.shop.shopingcart.ShoppingCartService;

public class WebShopServiceClasses {
	private static List<Class<? extends Object>> serviceClasses;

	static {
		serviceClasses = Arrays.asList(ProductService.class, ShoppingCartService.class);
	}

	public static List<Class<?>> get() {
		return serviceClasses;
	}
}
