package nth.reflect.javafx.control.table;

import java.text.Format;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.format.impl.JavaFormatFactory;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeCell;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.view.table.RfxTableView;

public class RfxTable extends TableView<Object> {

	// ROW_HEIGHT: Material design says 48 but we use same height as menu items
	private static final int ROW_HEIGHT = RfxItemTreeCell.ITEM_HEIGHT;
	private static final int ROW_FONT_SIZE = 14;
	private static final int HEADER_FONT_SIZE = 13;

	public RfxTable() {
		// TODO new RfxVerticalFlingScroller(this);
		addStyleClass();
	}

	/**
	 * Constructor for creating a table that shows a {@link ActionMethod} result
	 * @param reflectionProvider
	 * @param languageProvider
	 * @param methodOwner
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 * @param tableView 
	 */
	public RfxTable(RfxTableView tableView) {
		this();
		UserInterfaceContainer userInterfaceContainer=tableView.getuserInterfaceContainer();
		ReflectionProvider reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class); 
		LanguageProvider languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		ActionMethodInfo actionMethodInfo = tableView.getMethodInfo();
		
		Class<?> objectClass = actionMethodInfo.getGenericReturnType();
		
		//TODO arrays and other collections than List
		if (TypeUtil.isJavaType(objectClass) || TypeUtil.isEnum(objectClass)) {
			TableColumn<Object, String> singeColumn = new TableColumn("");
			singeColumn.setCellValueFactory(
					createCellValueFactoryForJavaTypeOrEnum(languageProvider, objectClass));			
			hideHeader();
		} else {
			setItems(createObservableList(tableView));
			createColumns(reflectionProvider, objectClass);
			ColumnAutoSizer.autoFitTable(this);
		}

	}

	private void createColumns(ReflectionProvider reflectionProvider, Class<?> objectClass) {
		ClassInfo classInfo = reflectionProvider.getClassInfo(objectClass);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAnsVisibleInTable();
		for (PropertyInfo propertyInfo : propertyInfos) {
			TableColumn propertyColumn = new TableColumn(propertyInfo.getDisplayName());
			propertyColumn.setMinWidth(100);
			propertyColumn.setCellValueFactory(
					new PropertyValueFactory<>(propertyInfo.getSimpleName()));
			getColumns().add(propertyColumn);
		}
	}
	
	private Callback<CellDataFeatures<Object, String>, ObservableValue<String>> createCellValueFactoryForJavaTypeOrEnum(
			LanguageProvider languageProvider, Class<?> objectClass) {
		JavaFormatFactory formatFactory = new JavaFormatFactory(languageProvider);
		Format format = formatFactory.create(objectClass);
		return new Callback<CellDataFeatures<Object, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Object, String> param) {
				String value = format.format(param);
				return new ReadOnlyObjectWrapper<String>(value);
			}
		};
	}

	private ObservableList<Object> createObservableList(RfxTableView tableView) {
		try {
			 Object methodOwner=tableView.getMethodOwner();
				ActionMethodInfo actionMethodInfo=tableView.getMethodInfo();
						Object methodParameterValue=tableView.getMethodParameter();
			Object result = actionMethodInfo.invoke(methodOwner, methodParameterValue);
			List<Object> list = (List<Object>) result;
			// TODO create a createObservableList for all types, and that can be
			// updated when needed
			return new ObservableListWrapper<Object>(list);
		} catch (Exception e) {
			UserInterfaceController userInterfaceController = tableView.getuserInterfaceContainer()
					.get(UserInterfaceController.class);
			userInterfaceController.showErrorDialog(tableView.getViewTitle(), "Error getting table values.",
					e);
			return null;
		}
	}

	private void hideHeader() {
		Pane header = (Pane) lookup("TableHeaderRow");
		header.setVisible(false);
		setLayoutY(-header.getHeight());
		autosize();
	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxTable.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxTable.class)).getProperties()
				.setFont(MaterialFont.getRobotoRegular(ROW_FONT_SIZE))
				// remove focus border
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND());
		styleSheet
				.addStyleGroup(
						RfxStyleSelector.createFor(RfxTable.class).appendChild("column-header"))
				.getProperties().setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
				.setBorderColor(MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT())
				.setSize(ROW_HEIGHT);
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(RfxTable.class)
						.appendChild("column-header-background"))
				.getProperties()
				// hide vertical line in header
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND());
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(RfxTable.class)
						.appendChild("column-header-background").appendChild("filler"))
				.getProperties().setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
				.setBorderColor(MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT());
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(RfxTable.class)
						.appendChild("column-header").appendChild(Label.class))
				.getProperties().setFont(MaterialFont.getRobotoMedium(HEADER_FONT_SIZE))
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND2())
				.setFontWeight(FontWeight.NORMAL);
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-column")).getProperties()
				.setBorderColor("transparent").setProperty("-fx-alignment", "CENTER-LEFT");
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-row-cell")).getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1())
				.setBorderColor(MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT())
				.setCellSize(ROW_HEIGHT);
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-row-cell").appendFocused())
				.getProperties().setBackground(MaterialColorSetCssName.CONTENT.FOREGROUND3())
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1())
				.setBorderColor(MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT());
	}
}
