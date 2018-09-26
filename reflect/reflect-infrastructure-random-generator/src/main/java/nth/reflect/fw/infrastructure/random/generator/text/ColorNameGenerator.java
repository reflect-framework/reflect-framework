package nth.reflect.fw.infrastructure.random.generator.text;

public class ColorNameGenerator extends StringGenerator {
	public ColorNameGenerator() {
		super(new ResourceFile("ColorNames.txt"));
	}
}
