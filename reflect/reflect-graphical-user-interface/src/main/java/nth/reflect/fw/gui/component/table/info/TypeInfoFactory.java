package nth.reflect.fw.gui.component.table.info;

import java.lang.reflect.Method;
import java.util.Optional;

import nth.reflect.fw.gui.component.tab.ActionMethodTab;
import nth.reflect.fw.gui.component.table.info.column.UnknownGenericReturnTypeException;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class TypeInfoFactory {

	public static TypeInfo createfor(ActionMethodTab tab) {
		ActionMethodInfo methodInfo = tab.getMethodInfo();
		Method method = methodInfo.getMethod();

		Optional<TypeInfo> optionalGenericTypeInfo = methodInfo.getReturnTypeInfo().getGenericTypeInfo();
		TypeInfo genericTypeInfo = optionalGenericTypeInfo
				.orElseThrow(() -> new UnknownGenericReturnTypeException(method));
		return genericTypeInfo;
	}

}
