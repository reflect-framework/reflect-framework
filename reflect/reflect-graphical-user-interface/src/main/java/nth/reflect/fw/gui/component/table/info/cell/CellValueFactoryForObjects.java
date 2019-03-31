package nth.reflect.fw.gui.component.table.info.cell;

import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

public class CellValueFactoryForObjects<S, T> implements CellValueFactory {

	private final DomainClassInfo domainClassInfo;

	public CellValueFactoryForObjects(DomainClassInfo domainClassInfo) {
		this.domainClassInfo = domainClassInfo;
	}

	@Override
	public String getValue(Object obj) {
		String objTitle = domainClassInfo.getTitle(obj);
		return objTitle;
	}

}
