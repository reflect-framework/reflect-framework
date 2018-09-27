package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class ColorNameGenerator extends FromStringListGenerator {
	public ColorNameGenerator() {
		super(new ResourceFile("ColorNames.txt"));
	}
}
