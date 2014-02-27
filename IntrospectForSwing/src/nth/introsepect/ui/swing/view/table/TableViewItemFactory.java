package nth.introsepect.ui.swing.view.table;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.filter.EqualsFilter;
import nth.introspect.filter.FilterBuilder;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.method.filter.ParameterTypeFilter;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.FormView;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.report.msexcel.item.ExportTableToExcelItem;
import nth.introspect.ui.item.HierarchicalItem;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.item.method.PropertyMethodOwnerItem;
import nth.introspect.util.TitleUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

/**
 * @deprecated use {@link ItemFactory}
 * @author nilsth
 *
 */
public class TableViewItemFactory {
	// TODO move to introspect package and let other user-interfaces implementations use this factory

	public static List<Item> createMenuItems(MethodInfo methodInfoOfView, Object serviceObject, ReadOnlyValueModel domainClassValueModel, Refreshable refreshable) {
		// create root
		List<Item> items = new ArrayList<Item>();
		// create service menu for service object of TreeView

		Class<?> domainClass = domainClassValueModel.getValueType();
		

		// add FormPropertyItems to call propertyMethods (i.e. property=owner, propertyMethod=ownerClear()) for properties on forms that are in edit mode
		ViewContainer viewContainer = Introspect.getUserInterfaceProvider().getViewContainer();
		for (int index = 0; index < viewContainer.getViewCount(); index++) {
			Object view = viewContainer.getView(index);
			if (view instanceof FormView) {
				FormView formView = (FormView) view;
				if (!formView.isFormReadOnly()) {
					//form is in edit mode
					PropertyMethodOwnerItem propertyMethodOwnerItem = new PropertyMethodOwnerItem(formView,domainClassValueModel);
					items.add(propertyMethodOwnerItem);
				}
			}
		}

		FilterBuilder filter = new FilterBuilder().exclude(new EqualsFilter<MethodInfo>(methodInfoOfView)).include(new ParameterTypeFilter(domainClass));
		MethodOwnerItem serviceObjectMenu = new MethodOwnerItem(serviceObject, filter, domainClassValueModel);

		// add serviceObjectMenu
		items.add(serviceObjectMenu);
		// create remaining service menus for the other service objects
		List<Object> serviceObjects = Introspect.getDomainProvider().getServiceObjects();
		for (Object otherServiceObject : serviceObjects) {
			if (serviceObject != otherServiceObject) {
				items.add(new MethodOwnerItem(otherServiceObject, new ParameterTypeFilter(domainClass), domainClassValueModel));
			}
		}

		return items;
	}

	public static List<Item> createMenuItems(MethodInfo methodInfoOfView, Object serviceObject, Refreshable refreshable, MethodInfo methodInfo, Object methodParameter, ReadOnlyValueModel selectedRowsValueModel, ReadOnlyValueModel allRowsValueModel) {
		List<Item> items = createMenuItems(methodInfoOfView, serviceObject, selectedRowsValueModel, refreshable);
		items.add(createExportMenu(selectedRowsValueModel, allRowsValueModel, methodInfo, methodParameter));
		return items;
	}

	private static Item createExportMenu(ReadOnlyValueModel selectedRowsValueModel, ReadOnlyValueModel allRowsValueModel, MethodInfo methodInfo, Object methodParameter) {
		final HierarchicalItem exportMenu = new HierarchicalItem();
		exportMenu.setText("Export");
		exportMenu.setDescription("Export");

		StringBuffer reportTitle = new StringBuffer();
		reportTitle.append(Introspect.getInfoProvider().getApplicationTitle());
		reportTitle.append(" - ");
		reportTitle.append(TitleUtil.createTitle(methodInfo, methodParameter, true));
		exportMenu.addItem(new ExportTableToExcelItem(selectedRowsValueModel, "Export selected rows to Excel", reportTitle.toString()));
		exportMenu.addItem(new ExportTableToExcelItem(allRowsValueModel, "Export all rows to Excel", reportTitle.toString()));
		// exportMenu.addSeparator();
		// TODO exportMenu.addItem(new ExportTableToPdfItem(valueModel, true));
		// exportMenu.addItem(new ExportTableToPdfItem(valueModel, false));
		return exportMenu;
	}

}
