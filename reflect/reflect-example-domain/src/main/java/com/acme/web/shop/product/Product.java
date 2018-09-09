package com.acme.web.shop.product;

import java.math.BigDecimal;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Product {
	private static final String CURRENCY_FORMAT = "0.00 €";
	private String code;
	private String name;
	private String details;
	private BigDecimal price;

	@Hidden(exceptForRoleNames = "employee")
	@Order(sequenceNumber = 10)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Order(sequenceNumber = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(sequenceNumber = 30)
	@Hidden(propertyHiddenFor = HiddenFor.TABLES)
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Order(sequenceNumber = 40)
	@Format(pattern = CURRENCY_FORMAT)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(name).append(price, CURRENCY_FORMAT).toString();
	}

}
