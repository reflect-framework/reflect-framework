package nth.reflect.fw.infrastructure.randomfactory;

import java.util.concurrent.ThreadLocalRandom;

public class RandomBoolFactory implements Factory<Boolean> {

	@Override
	public Boolean create() {
		return ThreadLocalRandom.current().nextBoolean();
	}

}
