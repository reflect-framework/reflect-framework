package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.util.Arrays;
import java.util.List;

public class Person {
	private String name;
	private List<String> children;
	public static final String[] CHILDREN = new String[] { "first born", "second born" };

	public Person() {
		children = Arrays.asList(CHILDREN);
	}

	public Person(String name) {
		this.name = name;
		children = Arrays.asList(CHILDREN);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static final String CHILDREN_PROPERTY_NAME = "children";

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> children) {
		this.children = children;
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
