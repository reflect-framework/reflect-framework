package nth.reflect.ui.vaadin;

import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class ContentPanel extends Div {

	public ContentPanel() {
		setSizeFull();
	}

	public void show(Component selectedPage) {
		for (Component child : getChildren().collect(Collectors.toList())) {
			child.setVisible(child == selectedPage);
		}
	}
}
