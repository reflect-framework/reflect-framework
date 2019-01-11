package nth.reflect.fw.gui.component.tab.form.valuemodel;

import nth.reflect.fw.generic.util.CloneUtil;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

public class BufferedDomainValueModel implements ReadOnlyValueModel {

	
	private final Object domainObject;
	private Object domainObjectCopy;
	private boolean comitted;
	private final FormMode formMode;
	private final ReflectionProvider reflectionProvider;

	public BufferedDomainValueModel(UserInterfaceContainer userInterfaceContainer, Object domainObject, FormMode formMode) {
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.domainObject = domainObject;
		this.formMode = formMode;
		if (FormMode.EDIT_MODE==formMode) {
			this.domainObjectCopy = CloneUtil.clone(userInterfaceContainer, reflectionProvider, domainObject); //Do not deep clone! Properties with a value object(s) (such as Customer) need to be the actual object and may not contain cloned objects! 
		}
		comitted=false;
	}
	
	@Override
	public Object getValue() {
		if (FormMode.READ_ONLY_MODE==formMode || comitted) {
			return domainObject ;
		} else {
			return domainObjectCopy;
		}
	}


	@Override
	public Class<?> getValueType() {
		return domainObject.getClass();
	}

	/**
	 * This method commits the edited property values by coping them back to the domainValue 
	 */
	public void commit() {
		//copy all property values of domainObjectCopy to domainObject
		CloneUtil.clone(reflectionProvider, domainObjectCopy, domainObject);
		//set committed flag
		comitted=true;
	}

	@Override
	public boolean canGetValue() {
		return true;
	}
	
	
}
