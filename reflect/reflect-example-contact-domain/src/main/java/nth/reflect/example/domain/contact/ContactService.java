package nth.reflect.example.domain.contact;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class ContactService {
	private final List<Contact> allContacts;
	
	public ContactService() {
		allContacts=new ContactGenerator().generateList(20, 150);
	}

	public List<Contact> allContacts() {
		return allContacts;
	}

	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public Contact viewContact(Contact contact) {
		return contact;
	}
	
	@ParameterFactory
	public void editContact(Contact contact){
	}
}
