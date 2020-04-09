package nth.reflect.util.random.generator.name;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class MaleNameGenerator extends FromListGenerator<String> {

	public MaleNameGenerator() {
		super(Resources.maleNameRepository());
	}

	
	
}
