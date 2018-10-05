package nth.reflect.fw.infrastructure.random.generator.name;

public class Product {
private final String name;
private final String description;
private final String company;
public Product(String name, String description, String company) {
	super();
	this.name = name;
	this.description = description;
	this.company = company;
}
public String getName() {
	return name;
}
public String getDescription() {
	return description;
}
public String getCompany() {
	return company;
}


}
