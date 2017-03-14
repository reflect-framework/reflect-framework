/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package nth.reflect.javafx.control.window;

import java.net.MalformedURLException;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.ContentColor;
import nth.introspect.ui.style.MaterialColorPalette;
import nth.introspect.ui.style.MaterialStyle;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbar;

/**
 * Inspired by the Window Decorator by Shadi Shaheen: to resize/move its content without the default window look
 * 
 */
public class RfxWindow extends VBox {

	private static final int MENU_WIDTH = 0;
	private StackPane contentPlaceHolder = new StackPane();
	private RfxApplicationToolbar applicationToolbar;

	public RfxWindow( Node node, UserInterfaceContainer userInterfaceContainer, MaterialStyle materialStyle) throws MalformedURLException {
		super();

		
		RfxUtil.init(this, MaterialColorPalette.TEAL, MaterialColorPalette.ORANGE, ContentColor.WHITE);
		
		//primaryStage.initStyle(StageStyle.TRANSPARENT);
		setMinWidth(300);
		setMinHeight(500);
//		setPickOnBounds(false);
		//TODO initStyleProperties()
		//TODO initControlls()

		applicationToolbar = new RfxApplicationToolbar(materialStyle,  userInterfaceContainer);

		contentPlaceHolder.setMinSize(0, 0);
		contentPlaceHolder.getChildren().add(node);
		((Region) node).setMinSize(0, 0);
		VBox.setVgrow(contentPlaceHolder, Priority.ALWAYS);
		//contentPlaceHolder.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 3, 3, 3))));

		this.getChildren().addAll(applicationToolbar, contentPlaceHolder);


	}


	public boolean isWide(){
		boolean wide = getWidth()<=(MENU_WIDTH*3);
		return wide;
	}

}