package com.acme.web.shop.shopingcart;

import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

@FontIcon(fontIconUrl=FontAwesomeUrl.SHOPPING_CART )
@DisplayName(englishName="Shopping cart")
public class ShoppingCartService {

	@FontIcon(fontIconUrl=FontAwesomeUrl.PAYPAL)
	public void checkOut(ShoppingCart shoppingCart) {
		
	}
	
	@FontIcon(fontIconUrl=FontAwesomeUrl.SHOPPING_CART )
	public void myShoppingCart(ShoppingCart shoppingCart) {
		
	}
	
	public ShoppingCart myShoppingCartParameterFactory() {
		//TODO get shoppingCart from ShoppingCartRepository for give user
		return new ShoppingCart();
	}
}
