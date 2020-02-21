package nth.reflect.fw.layer5provider;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Optional;
import java.util.function.Predicate;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * Tests for getter methods of a {@link ReflectApplication} without a parameter
 * that returns a {@link Provider} object or a Class<? extends Provider>
 * 
 * @author nilsth
 *
 */
public class ProviderMethodFilter implements Predicate<Method> {

	@Override
	public boolean test(Method method) {
		if (!method.getName().startsWith("get")) {
			return false;
		}
		if (method.getParameterCount() > 0) {
			return false;
		}
		Class<?> returnType = method.getReturnType();
		if (Provider.class.isAssignableFrom(returnType)) {
			return true;
		}
		if (returnType != Class.class) {
			return false;
		}
		Type genericType = method.getGenericReturnType();
		if (genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			if (actualTypeArguments.length != 1) {
				return false;
			}
			Type actualType = actualTypeArguments[0];
			if (actualType instanceof WildcardType) {
				WildcardType w = (WildcardType) actualType;
				Type[] upperBounds = w.getUpperBounds();
				if (upperBounds.length!=1) {
					return false;
				}
				Type foundType=upperBounds[0];
				if (foundType instanceof Class) {
					Class<?> foundClass=(Class<?>) foundType;
					boolean implementsProvider = Provider.class.isAssignableFrom(foundClass);
					return implementsProvider;
				}
			}
			return false;
		}
		return false;
	}

}
