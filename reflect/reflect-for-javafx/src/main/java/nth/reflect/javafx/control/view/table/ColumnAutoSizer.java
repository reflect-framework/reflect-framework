package nth.reflect.javafx.control.view.table;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sun.javafx.scene.control.skin.TableViewSkinBase;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;

public class ColumnAutoSizer {
	private static Method columnToFitMethod;

	static {
		try {
			columnToFitMethod = TableViewSkinBase.class.getDeclaredMethod(
					"resizeColumnToFitContent", TableColumnBase.class, int.class);
			columnToFitMethod.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public static void autoFitTable(TableView tableView) {
		tableView.skinProperty().addListener(new ChangeListener<Skin>() {
			@Override
			public void changed(ObservableValue<? extends Skin> observable, Skin oldValue,
					Skin newValue) {
				Platform.runLater(() -> resizeColumns(tableView));		
			}

			private void resizeColumns(TableView tableView) {
				for (Object column : tableView.getColumns()) {
					try {
						columnToFitMethod.invoke(tableView.getSkin(), column, -1);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
}
