package nth.reflect.fw.junit.layer5provider.authorization;

public class UserTestObject {

	private final String name;
	private final String password;
	private final String[] roles;

	public UserTestObject(String name, String password, String ...roles) {
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public boolean isValid(String name, String password) {
		return this.name.equals(name) && this.password.equals(password);
	}

	public boolean inRole(String roleToFind) {
		for (String role:roles) {
			if(role.equals(roleToFind)) {
				return true;
			}
		}
		return false;
	}

	

}
