package nth.reflect.example.domain.person;

import java.util.GregorianCalendar;
import java.util.List;

public class Person {
	private String name;
	private GregorianCalendar birtday;
	private Person spouce;
	private List<Person> children;
	private Gender gender;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GregorianCalendar getBirtday() {
		return birtday;
	}
	public void setBirtday(GregorianCalendar birtday) {
		this.birtday = birtday;
	}
	public Person getSpouce() {
		return spouce;
	}
	public void setSpouce(Person spouce) {
		this.spouce = spouce;
	}
	
	public List<Person> getChildren() {
		return children;
	}
	public void setChildren(List<Person> children) {
		this.children = children;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return name;
	}

	
	
}