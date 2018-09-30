package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile;

public class ColorNameGenerator extends FromStringListGenerator {
	public ColorNameGenerator() {
		super(new ResourceFile("ColorNames.txt"));
	}
}
