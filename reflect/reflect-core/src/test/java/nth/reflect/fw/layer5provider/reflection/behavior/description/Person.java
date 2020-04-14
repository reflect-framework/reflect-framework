package nth.reflect.fw.layer5provider.reflection.behavior.description;

public class Person {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		if (name == null) {
			return "";
		} else {
			return name;
		}
	}
}
