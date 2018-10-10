package nth.reflect.fw.javafx.control.view.form.proppanel;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.component.PropertyLabelStyle;
import nth.reflect.fw.ui.style.component.PropertyPanelStyle;

public class PropertyLabel extends Label implements nth.reflect.fw.ui.view.form.propertypanel.PropertyLabel {

	
	public PropertyLabel() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertyLabel.class));
	}
	
	@Override
	public void setDescription(String description) {
		setTooltip(new Tooltip(description));
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertyLabel.class)).getProperties()
		.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1())
		.setFont(PropertyLabelStyle.getFont()).setWrapText(true).setPadding(0, PropertyPanelStyle.getPaddingLeftRight(), 0, PropertyPanelStyle.getPaddingLeftRight());
	}

}
