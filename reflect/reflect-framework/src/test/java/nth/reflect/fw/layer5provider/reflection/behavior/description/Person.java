package nth.reflect.fw.layer5provider.reflection.behavior.description;

public class Person {

	public final static String NO_NAME = "No name";
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
			return NO_NAME;
		} else {
			return name;
		}
	}
}
