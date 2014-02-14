package nth.introspect.ui.valuemodel;

import nth.introspect.util.CloneUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class BufferedDomainValueModel implements ReadOnlyValueModel {

	
	private final Object domainObject;
	private Object domainObjectCopy;
	private boolean comitted;
	private final boolean isReadOnly;

	public BufferedDomainValueModel(Object domainObject, boolean isReadOnly) {
		this.domainObject = domainObject;
		this.isReadOnly = isReadOnly;
		if (!isReadOnly) {
			this.domainObjectCopy = CloneUtil.clone(domainObject); //Do not deep clone! Properties with a value object(s) (such as Customer) need to be the actual object and may not contain cloned objects! 
		}
		comitted=false;
	}
	
	@Override
	public Object getValue() {
		if (isReadOnly || comitted) {
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
		CloneUtil.clone(domainObjectCopy, domainObject);
		//set committed flag
		comitted=true;
	}

	@Override
	public boolean canGetValue() {
		return true;
	}
	
}
