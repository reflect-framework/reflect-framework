package nth.introspect.ui.valuemodel;

import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.ui.view.FormMode;
import nth.introspect.util.CloneUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class BufferedDomainValueModel implements ReadOnlyValueModel {

	
	private final Object domainObject;
	private Object domainObjectCopy;
	private boolean comitted;
	private final FormMode formMode;
	private final DomainInfoProvider domainInfoProvider;

	public BufferedDomainValueModel(DomainInfoProvider domainInfoProvider, Object domainObject, FormMode formMode) {
		this.domainInfoProvider = domainInfoProvider;
		this.domainObject = domainObject;
		this.formMode = formMode;
		if (FormMode.EDIT_MODE==formMode) {
			this.domainObjectCopy = CloneUtil.clone(domainInfoProvider, domainObject); //Do not deep clone! Properties with a value object(s) (such as Customer) need to be the actual object and may not contain cloned objects! 
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
		CloneUtil.clone(domainInfoProvider, domainObjectCopy, domainObject);
		//set committed flag
		comitted=true;
	}

	@Override
	public boolean canGetValue() {
		return true;
	}
	
	
}
