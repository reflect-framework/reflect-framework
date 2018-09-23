package nth.reflect.example.domain.contact;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.apache.bval.constraints.NotEmpty;

import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Event {
	
	private LocalDate date;
	private String name;

	@Order(sequenceNumber=10)
	@NotNull
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Order(sequenceNumber=20)
	@NotEmpty
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Hidden(propertyHiddenFor=HiddenFor.TABLES_AND_FORMS)
	public boolean isBirthday() {
		if (name==null) {
			return false;
		}
		return name.toLowerCase().contains("birthday");
	}
	

}
