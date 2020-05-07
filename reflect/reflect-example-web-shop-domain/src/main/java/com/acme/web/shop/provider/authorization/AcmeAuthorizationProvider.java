package com.acme.web.shop.provider.authorization;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.InvalidNameOrPasswordException;

public class AcmeAuthorizationProvider implements AuthorizationProvider {

	private final List<User> users;
	private User currentUser;

	public AcmeAuthorizationProvider() {
		users = new ArrayList<>();
		users.add(new User("carla", "password1", "salesmanager"));
		users.add(new User("john", "password2", "customer"));
	}

	public void login(String userName, String password)
			throws InvalidNameOrPasswordException {
		for (User user : users) {
			if (user.isValid(userName, password)) {
				currentUser = user;
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
