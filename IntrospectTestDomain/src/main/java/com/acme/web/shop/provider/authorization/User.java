package com.acme.web.shop.provider.authorization;

public class User {

	private final String name;
	private final String password;
	private final String[] roles;

	public User(String name, String password, String ...roles) {
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
