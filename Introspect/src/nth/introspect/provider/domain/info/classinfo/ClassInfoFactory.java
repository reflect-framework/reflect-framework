package nth.introspect.provider.domain.info.classinfo;


public class ClassInfoFactory {

	public static ClassInfo create(Class<?> domainOrServiceClass) {
		return new ClassInfo(domainOrServiceClass);
	}
	
}
