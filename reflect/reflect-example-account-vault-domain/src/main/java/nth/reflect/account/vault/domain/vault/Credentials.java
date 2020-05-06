package nth.reflect.account.vault.domain.vault;

import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldMode;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;

public class Credentials {
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@TextFieldMode(mode = TextFieldModeType.PASSWORD)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
