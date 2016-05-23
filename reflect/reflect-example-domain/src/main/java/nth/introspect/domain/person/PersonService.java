package nth.introspect.domain.person;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.hidden.Hidden;
import nth.introspect.layer5provider.reflection.behavior.icon.Icon;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;

@Icon(iconURL=FontAwesomeUrl.USERS)
public class PersonService {

	private List<Person> persons;
	
	public PersonService() {
		persons=new ArrayList<Person>();

		//create persons
		Person nils=new Person();
		nils.setName("Nils ten Hoeve");
		GregorianCalendar birthDay = (GregorianCalendar) GregorianCalendar.getInstance();
		birthDay.set(1974+1900, 9,6);
		nils.setBirtday(birthDay);
		nils.setGender(Gender.MALE);
		
		Person bianca=new Person();
		bianca.setName("Bianca ten Hoeve");
		birthDay = (GregorianCalendar) GregorianCalendar.getInstance();
		birthDay.set(1974+1900, 11,11);
		bianca.setBirtday(birthDay);
		bianca.setGender(Gender.FEMALE);
		
		Person tycho=new Person();
		tycho.setName("Tycho ten Hoeve");
		birthDay = (GregorianCalendar) GregorianCalendar.getInstance();
		birthDay.set(2003+1900, 10,19);
		tycho.setBirtday(birthDay);
		tycho.setGender(Gender.MALE);
		
		Person sanne=new Person();
		sanne.setName("Sanne ten Hoeve");
		birthDay = (GregorianCalendar) GregorianCalendar.getInstance();
		birthDay.set(2006+1900, 3,1);
		sanne.setBirtday(birthDay);
		sanne.setGender(Gender.FEMALE);
		
		//set relationships
		nils.setSpouce(bianca);
		nils.setChildren(new ArrayList<Person>());
		nils.getChildren().add(tycho);
		nils.getChildren().add(sanne);
		
		bianca.setSpouce(nils);
		bianca.setChildren(new ArrayList<Person>());
		bianca.getChildren().add(tycho);
		bianca.getChildren().add(sanne);
		
		//add to collection
		persons.add(nils);
		persons.add(bianca);
		persons.add(tycho);
		persons.add(sanne);
	}

	@Hidden(exceptForRoleNames="salesmanager")
	public List<Person> allPersons() {
		return persons;
	}
}
