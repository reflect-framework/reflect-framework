package nth.reflect.fw.infrastructure.random.generator.resource;

import java.util.List;

public interface Repository<T> {
	public List<T> getAll();
}
