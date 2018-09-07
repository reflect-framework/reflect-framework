package nth.reflect.fw.junit.layer5provider.authorization;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.InvalidNameOrPasswordException;

public class AuthorizationProviderTestObject implements AuthorizationProvider {

	private final List<UserTestObject> userTestObjects;
	private UserTestObject currentUser;

	public AuthorizationProviderTestObject() {
		userTestObjects = new ArrayList<>();
		userTestObjects.add(new UserTestObject("carla", "password1", "salesmanager"));
		userTestObjects.add(new UserTestObject("john", "password2", "customer"));
	}

	public void login(String userName, String password)
			throws InvalidNameOrPasswordException {
		for (UserTestObject userTestObject : userTestObjects) {
			if (userTestObject.isValid(userName, password)) {
				currentUser = userTestObject;
				return;
			}
		}
		throw new InvalidNameOrPasswordException();
	}

	@Override
	public String getCurrentUserName() {
		return currentUser == null ? "" : currentUser.getName();
	}

	@Override
	public boolean userInRole(String role) {
		return currentUser == null ? false : currentUser.inRole(role);
	}

}
