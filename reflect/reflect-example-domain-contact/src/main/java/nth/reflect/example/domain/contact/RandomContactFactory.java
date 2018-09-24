package nth.reflect.example.domain.contact;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class RandomContactFactory implements RandomGenerator<Contact>{

	public RandomContactFactory() {
//FIXME		RandomFirstNameFactory firstNameFactory=new RandomFirstNameFactory(); 
	}
	
	
	@Override
	public Contact generate() {
//	FIXME	Contact contact=new Contact();
//		contact.setFirstName(JRand.firstname().gen());
//		contact.setLastName(JRand.lastname().gen());
//		
//		List<String> emailAddresses=Random.list(Random.eMail().setName()).setMax(2);
//		contact.seteMailAdresses(eMailAdresses);
		return null;
	}

}
