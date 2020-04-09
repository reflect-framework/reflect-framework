package nth.reflect.util.random.generator.name;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class FemaleNameGenerator extends FromListGenerator<String> {

	public FemaleNameGenerator() {
		super(Resources.femaleNameRepository());
		// TODO Auto-generated constructor stub
	}

}
