package nth.reflect.fw.infrastructure.randomfactory.product;

import java.math.BigDecimal;

public class Product {
	private String code;
	private String name;
	private String details;
	private BigDecimal price;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	//TODO formatting
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	

}
