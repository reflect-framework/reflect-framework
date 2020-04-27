package nth.reflect.fw.gui.component.table.info;

import java.lang.reflect.Method;
import java.util.Optional;

import nth.reflect.fw.gui.component.table.info.column.UnknownGenericReturnTypeException;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class TypeInfoFactory {

	public static TypeInfo createfor(Method method, TypeInfo returnTypeInfo) {

		Optional<TypeInfo> optionalGenericTypeInfo = returnTypeInfo.getGenericTypeInfo();
		TypeInfo genericTypeInfo = optionalGenericTypeInfo
				.orElseThrow(() -> new UnknownGenericReturnTypeException(method));
		return genericTypeInfo;
	}

	public static boolean canCreatefor(ActionMethodInfo actionMethodInfo) {
		Method method = actionMethodInfo.getMethod();
		TypeInfo returnTypeInfo = actionMethodInfo.getReturnTypeInfo();
		try {
			createfor(method, returnTypeInfo);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

}
