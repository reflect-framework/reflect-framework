package nth.reflect.example.domain2.account;

import nth.introspect.generic.util.TitleBuilder;

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
		TitleBuilder titleBuilder=new TitleBuilder(":");
		titleBuilder.append(name);
		titleBuilder.append(value);
		return titleBuilder.toString();
	}

}
