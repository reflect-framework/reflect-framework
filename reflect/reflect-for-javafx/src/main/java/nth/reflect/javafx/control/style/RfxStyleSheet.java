package nth.reflect.javafx.control.style;

import java.awt.Label;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.LabelView;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.control.list.RfxList;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbar;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarButton;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarMenuButton;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarTitle;

public class RfxStyleSheet {

	private final List<RfxStyleGroup> styleGroups;
	private final MaterialStyle materialStyle;

	public RfxStyleSheet(MaterialStyle materialStyle) {
		this.materialStyle = materialStyle;
		styleGroups = new ArrayList<>();
	}

	public RfxStyleGroup addStyleGroup(RfxStyleSelector styleSelector) {
		RfxStyleGroup styleGroup = new RfxStyleGroup(styleSelector);
		styleGroups.add(styleGroup);
		return styleGroup;														
	}

	public void addToScene(Scene scene) {
		scene.getStylesheets().setAll(new RfxStyleSheetUrl().toString());
	}


	public static String createStyleClassName(Class<? extends Node> controlClass) {
		StringBuilder styleClassName = new StringBuilder();
		String controlClassName = controlClass.getSimpleName();
		char previousCh = 0;
		for (char ch : controlClassName.toCharArray()) {
			if (Character.isLowerCase(previousCh) && Character.isUpperCase(ch)) {
				styleClassName.append("-");
			}
			styleClassName.append(Character.toLowerCase(ch));
			previousCh = ch;
		}
		return styleClassName.toString();
	}

	@Override
	public String toString() {
		StringBuilder css = new StringBuilder();
		for (RfxStyleGroup styleGroup : styleGroups) {
			css.append(styleGroup);
			css.append("\n");
		}
		return css.toString();
	}

	
}
