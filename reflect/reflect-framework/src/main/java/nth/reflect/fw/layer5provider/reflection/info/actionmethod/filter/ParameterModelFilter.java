package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ParameterModelFilter implements Predicate<ActionMethodInfo> {

	private Predicate<ActionMethodInfo> filter;

	public ParameterModelFilter(ReadOnlyValueModel actionMethodParameterModel) {
		Class<?> type = actionMethodParameterModel.getValueType();
		Object actionMethodParameter = actionMethodParameterModel.getValue();
		filter = new ParameterTypeFilter(type);
		if (!actionMethodParameterModel.canGetValue() || actionMethodParameter == null) {
			filter = filter.and(new NoParameterOrParameterFactoryFilter());
		}
	}

	@Override
	public boolean test(ActionMethodInfo actionMethodInfo) {
		return filter.test(actionMethodInfo);
	}

}
