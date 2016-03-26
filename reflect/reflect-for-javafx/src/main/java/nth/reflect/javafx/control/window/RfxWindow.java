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

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.DisplayType;
import nth.introspect.ui.style.MaterialColorPalette;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.MaterialStyle;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbar;

/**
 * Inspired by the Window Decorator by Shadi Shaheen: to resize/move its content without the default window look
 * 
 */
public class RfxWindow extends VBox {

	private Stage primaryStage;

	private double xOffset = 0;
	private double yOffset = 0;
	private double newX;
	private double newY;
	private double initX;
	private double initY;

	private boolean allowMove = false;
	private StackPane contentPlaceHolder = new StackPane();
	private RfxApplicationToolbar applicationToolbar;
	private Rectangle2D midmizePosAndSize;

	public RfxWindow(Stage stage, Node node, UserInterfaceContainer userInterfaceContainer, MaterialStyle materialStyle) {
		super();
		primaryStage = stage;
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		setMinWidth(300);
		setMinHeight(500);
		setPickOnBounds(false);
		//TODO initStyleProperties()
		//TODO initControlls()

		applicationToolbar = new RfxApplicationToolbar(materialStyle, this, userInterfaceContainer);

		contentPlaceHolder.setMinSize(0, 0);
		contentPlaceHolder.getChildren().add(node);
		((Region) node).setMinSize(0, 0);
		VBox.setVgrow(contentPlaceHolder, Priority.ALWAYS);
		contentPlaceHolder.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 3, 3, 3))));

		Rectangle clip = new Rectangle();
		clip.widthProperty().bind(((Region) node).widthProperty());
		clip.heightProperty().bind(((Region) node).heightProperty());
		node.setClip(clip);
		this.getChildren().addAll(applicationToolbar, contentPlaceHolder);


		// save the mouse pressed position when clicking on the decorator pane
		this.addEventFilter(MouseEvent.MOUSE_PRESSED, (mouseEvent) -> {
			initX = mouseEvent.getScreenX();
			initY = mouseEvent.getScreenY();
			xOffset = mouseEvent.getSceneX();
			yOffset = mouseEvent.getSceneY();
		});

		// show the drag cursor on the borders
		this.setOnMouseMoved((mouseEvent) -> {
			if (isMaximized() || primaryStage.isFullScreen()) {
				this.setCursor(Cursor.DEFAULT);
				return; // maximized mode does not support resize
			}
			if (!primaryStage.isResizable()) {
				return;
			}
			double x = mouseEvent.getX();
			double y = mouseEvent.getY();
			Bounds boundsInParent = this.getBoundsInParent();
			if (contentPlaceHolder.getBorder() != null
					&& contentPlaceHolder.getBorder().getStrokes().size() > 0) {
				double borderWidth = contentPlaceHolder.snappedLeftInset();
				if (isRightEdge(x, y, boundsInParent)) {
					if (y < borderWidth) {
						this.setCursor(Cursor.NE_RESIZE);
					} else if (y > this.getHeight() - (double) (borderWidth)) {
						this.setCursor(Cursor.SE_RESIZE);
					} else {
						this.setCursor(Cursor.E_RESIZE);
					}
				} else if (isLeftEdge(x, y, boundsInParent)) {
					if (y < borderWidth) {
						this.setCursor(Cursor.NW_RESIZE);
					} else if (y > this.getHeight() - (double) (borderWidth)) {
						this.setCursor(Cursor.SW_RESIZE);
					} else {
						this.setCursor(Cursor.W_RESIZE);
					}
				} else if (isTopEdge(x, y, boundsInParent)) {
					this.setCursor(Cursor.N_RESIZE);
				} else if (isBottomEdge(x, y, boundsInParent)) {
					this.setCursor(Cursor.S_RESIZE);
				} else {
					this.setCursor(Cursor.DEFAULT);
				}
			}
		});


		this.setOnMouseDragged((mouseEvent) -> {
			if (!mouseEvent.isPrimaryButtonDown() || (xOffset == -1 && yOffset == -1)) {
				return;
			}
			/*
			 * Long press generates drag event!
			 */
			if (primaryStage.isFullScreen() || mouseEvent.isStillSincePress()
					|| primaryStage.isMaximized()) {
				return;
			}

			newX = mouseEvent.getScreenX();
			newY = mouseEvent.getScreenY();

			double deltax = newX - initX;
			double deltay = newY - initY;
			Cursor cursor = this.getCursor();

			if (Cursor.E_RESIZE.equals(cursor)) {
				setStageWidth(primaryStage.getWidth() + deltax);
				mouseEvent.consume();
			} else if (Cursor.NE_RESIZE.equals(cursor)) {
				if (setStageHeight(primaryStage.getHeight() - deltay)) {
					primaryStage.setY(primaryStage.getY() + deltay);
				}
				setStageWidth(primaryStage.getWidth() + deltax);
				mouseEvent.consume();
			} else if (Cursor.SE_RESIZE.equals(cursor)) {
				setStageWidth(primaryStage.getWidth() + deltax);
				setStageHeight(primaryStage.getHeight() + deltay);
				mouseEvent.consume();
			} else if (Cursor.S_RESIZE.equals(cursor)) {
				setStageHeight(primaryStage.getHeight() + deltay);
				mouseEvent.consume();
			} else if (Cursor.W_RESIZE.equals(cursor)) {
				if (setStageWidth(primaryStage.getWidth() - deltax)) {
					primaryStage.setX(primaryStage.getX() + deltax);
				}
				mouseEvent.consume();
			} else if (Cursor.SW_RESIZE.equals(cursor)) {
				if (setStageWidth(primaryStage.getWidth() - deltax)) {
					primaryStage.setX(primaryStage.getX() + deltax);
				}
				setStageHeight(primaryStage.getHeight() + deltay);
				mouseEvent.consume();
			} else if (Cursor.NW_RESIZE.equals(cursor)) {
				if (setStageWidth(primaryStage.getWidth() - deltax)) {
					primaryStage.setX(primaryStage.getX() + deltax);
				}
				if (setStageHeight(primaryStage.getHeight() - deltay)) {
					primaryStage.setY(primaryStage.getY() + deltay);
				}
				mouseEvent.consume();
			} else if (Cursor.N_RESIZE.equals(cursor)) {
				if (setStageHeight(primaryStage.getHeight() - deltay)) {
					primaryStage.setY(primaryStage.getY() + deltay);
				}
				mouseEvent.consume();
			} else if (allowMove) {
				primaryStage.setX(mouseEvent.getScreenX() - xOffset);
				primaryStage.setY(mouseEvent.getScreenY() - yOffset);
				mouseEvent.consume();
			}
		});
	}

	private boolean isRightEdge(double x, double y, Bounds boundsInParent) {
		if (x < this.getWidth() && x > this.getWidth() - contentPlaceHolder.snappedLeftInset()) {
			return true;
		}
		return false;
	}

	private boolean isTopEdge(double x, double y, Bounds boundsInParent) {
		if (y >= 0 && y < contentPlaceHolder.snappedLeftInset()) {
			return true;
		}
		return false;
	}

	private boolean isBottomEdge(double x, double y, Bounds boundsInParent) {
		if (y < this.getHeight() && y > this.getHeight() - contentPlaceHolder.snappedLeftInset()) {
			return true;
		}
		return false;
	}

	private boolean isLeftEdge(double x, double y, Bounds boundsInParent) {
		if (x >= 0 && x < contentPlaceHolder.snappedLeftInset()) {
			return true;
		}
		return false;
	}

	boolean setStageWidth(double width) {
		if (width >= primaryStage.getMinWidth() && width >= applicationToolbar.getMinWidth()) {
			primaryStage.setWidth(width);
			initX = newX;
			return true;
		} else if (width >= primaryStage.getMinWidth()
				&& width <= applicationToolbar.getMinWidth()) {
			width = applicationToolbar.getMinWidth();
			primaryStage.setWidth(width);
		}
		return false;
	}

	boolean setStageHeight(double height) {
		if (height >= primaryStage.getMinHeight() && height >= applicationToolbar.getHeight()) {
			primaryStage.setHeight(height);
			initY = newY;
			return true;
		} else if (height >= primaryStage.getMinHeight()
				&& height <= applicationToolbar.getHeight()) {
			height = applicationToolbar.getHeight();
			primaryStage.setHeight(height);
		}
		return false;
	}

	public boolean isMaximized() {
		Screen screen = Screen.getPrimary();
		Rectangle2D screenBounds = screen.getVisualBounds();

		boolean xMatches = primaryStage.getX() == screenBounds.getMinX();
		boolean yMatches = primaryStage.getY() == screenBounds.getMinY();
		boolean widthMatches = primaryStage.getWidth() == screenBounds.getWidth();
		boolean heightMatches = primaryStage.getHeight() == screenBounds.getHeight();

		return xMatches && yMatches && widthMatches && heightMatches;
	}

	public void minimize() {
		primaryStage.setIconified(true);
	}

	public void midimize() {
		// TODO check midimizePosAndSize
		primaryStage.setX(midmizePosAndSize.getMinX());
		primaryStage.setY(midmizePosAndSize.getMinY());
		primaryStage.setWidth(midmizePosAndSize.getWidth());
		primaryStage.setHeight(midmizePosAndSize.getHeight());
	}

	public void maximize() {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		midmizePosAndSize = new Rectangle2D(primaryStage.getX(), primaryStage.getY(),
				primaryStage.getWidth(), primaryStage.getHeight());

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		
	}

	public void setX(double x) {
		primaryStage.setX(x);
	}

	public void setY(double y) {
		primaryStage.setY(y);
	}

	public double getX() {
		return primaryStage.getX();
	}

	public double getY() {
		return primaryStage.getY();
	}

}