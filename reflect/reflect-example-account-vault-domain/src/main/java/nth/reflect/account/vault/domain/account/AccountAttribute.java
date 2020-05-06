package nth.reflect.account.vault.domain.account;

import nth.reflect.fw.generic.util.TitleBuilder;

public class AccountAttribute {
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return new TitleBuilder().setSeperator(":").append(name).append(value).toString();
	}

}
