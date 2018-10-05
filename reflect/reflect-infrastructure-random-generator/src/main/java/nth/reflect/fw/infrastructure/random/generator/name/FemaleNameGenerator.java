package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class FemaleNameGenerator extends FromListGenerator<String> {

	public FemaleNameGenerator() {
		super(Resources.femaleNameRepository());
		// TODO Auto-generated constructor stub
	}

}
