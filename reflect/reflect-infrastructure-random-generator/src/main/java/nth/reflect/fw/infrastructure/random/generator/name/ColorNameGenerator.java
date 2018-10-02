package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class ColorNameGenerator extends FromStringListGenerator {
	
	public ColorNameGenerator() {
		super(Repositories.colorNameRepository().getColorNames());
	}
}
