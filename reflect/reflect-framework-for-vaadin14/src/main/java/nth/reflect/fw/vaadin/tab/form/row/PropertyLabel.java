package nth.reflect.fw.vaadin.tab.form.row;

import com.vaadin.flow.component.html.Span;

import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyLabelStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.vaadin.css.SizeUnit;
import nth.reflect.fw.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class PropertyLabel extends Span implements nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyLabel {

	/**
	 * TODO: conform to Material Design: {@link PropertyLabelStyle} and
	 * {@link PropertyField} should be
	 * <ul>
	 * <li>in one container (56px high and have same background color).</li>
	 * <li>expand when needed in multi line mode</li>
	 * <li>be styled differently</li>
	 * <li>See: <a href=
	 * "https://material.io/design/components/text-fields.html#spec">Material Design
	 * for TextFields</a></li>
	 * </ul>
	 */
	public PropertyLabel() {
		// Material design: font-family: Raleway; font-size: 12px; font-style:
		// SemiBold ; padding 0,12,0,12
		new StyleBuilder()
				.setFontSize(14, SizeUnit.PX)
				.setFont("Roboto Condensed")
				.setPadding(0, 10, 0, 10)
				.setFor(this);
	}

	@Override
	public void setText(String text) {
		super.setText(text);
	}

	@Override
	public void setDescription(String description) {
		super.setTitle(description);
	}

}
