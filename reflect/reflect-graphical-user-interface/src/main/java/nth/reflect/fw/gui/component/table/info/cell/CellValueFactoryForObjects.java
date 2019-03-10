package nth.reflect.fw.gui.component.table.info.cell;

import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class CellValueFactoryForObjects<S, T> implements CellValueFactory {

	private final ClassInfo classInfo;

	public CellValueFactoryForObjects(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	@Override
	public String getValue(Object obj) {
		String objTitle = classInfo.getTitle(obj);
		return objTitle;
	}

}
