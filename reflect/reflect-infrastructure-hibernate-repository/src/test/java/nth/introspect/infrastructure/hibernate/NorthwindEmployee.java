package nth.introspect.infrastructure.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import nth.introspect.generic.util.TitleBuilder;

@Entity
@Table(name = "Employees")
public class NorthwindEmployee {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String title;

	@Id
	@GeneratedValue
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(title).append(" ", firstName)
				.append(" ", lastName).append(employeeId).toString();
	}
}
