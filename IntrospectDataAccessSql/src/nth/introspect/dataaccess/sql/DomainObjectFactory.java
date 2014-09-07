package nth.introspect.dataaccess.sql;

import java.util.Map;

public interface DomainObjectFactory<T> {

	public T createDomainObject(Map<String,Object> record) throws Exception;
}
