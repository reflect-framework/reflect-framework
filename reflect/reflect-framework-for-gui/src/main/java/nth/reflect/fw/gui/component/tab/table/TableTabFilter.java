package nth.reflect.fw.gui.component.tab.table;

import java.util.function.Predicate;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class TableTabFilter implements Predicate<Tab> {

	private Object methodOwner;
	private ActionMethodInfo actionMethodInfo;
	private Object methodParameter;
	private Object methodResult;

	public TableTabFilter(Object methodOwner, ActionMethodInfo actionMethodInfo, Object methodParameter,
			Object methodResult) {
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameter = methodParameter;
		this.methodResult = methodResult;
	}

	@Override
	public boolean test(Tab tab) {
		if (tab instanceof TableTab) {
			TableTab tableTab = (TableTab) tab;
			return tableTab.getMethodOwner() == methodOwner && tableTab.getMethodInfo() == actionMethodInfo
					&& tableTab.getMethodParameter() == methodParameter;
		} else {
			return false;
		}
	}

}
