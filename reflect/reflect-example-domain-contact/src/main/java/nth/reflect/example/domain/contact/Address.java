package nth.reflect.example.domain.contact;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Address {

	private String street;
	private String zipCode;
	private String city;
	private String state;
	private String country;
	private AddressType addressType;
	
	@Order(sequenceNumber=10)
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Order(sequenceNumber=20)
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Order(sequenceNumber=30)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Order(sequenceNumber=40)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Order(sequenceNumber=50)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Order(sequenceNumber=60)
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
	
}
