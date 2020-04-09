package nth.reflect.util.random.generator.name;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class ColorNameGenerator extends FromListGenerator<String> {

	public ColorNameGenerator() {
		super(Resources.colorNameRepository());
	}
	
	
	
}
