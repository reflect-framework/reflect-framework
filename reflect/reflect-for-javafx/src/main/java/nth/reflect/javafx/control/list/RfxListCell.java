package nth.reflect.javafx.control.list;

import java.util.Date;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import nth.introspect.ui.style.control.ListStyle;
import nth.reflect.javafx.control.style.RfxBackgroundFactory;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxFontFactory;

public class RfxListCell<T> extends ListCell<T> {

	private final ListStyle listStyle;

	public RfxListCell(ListStyle listStyle) {
		this.listStyle = listStyle;
		setPadding(new Insets(0, listStyle.getPaddingLeft(), 0, listStyle.getPaddingRight()));
		setFont(RfxFontFactory.create(listStyle.getPrimaryTextFont()));
	}

	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		if (isEmpty()) {
			setText(null);
			setGraphic(null);
		} else {
			setText(item.toString());// TODO
		}
		System.out.println(new Date());

	}

//	@Override
//	public void updateSelected(boolean selected) {
//		if (selected) {
//			setBackground(
//					new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));// TODO
//			setTextFill(Color.WHITE);// TODO
//		} else {
//			setBackground(RfxBackgroundFactory.fill(listStyle.getBackgroundColor()));
//			setTextFill(RfxColorFactory.create(listStyle.getTextColor()));
//		}
//	}
	
	
		
	
}
