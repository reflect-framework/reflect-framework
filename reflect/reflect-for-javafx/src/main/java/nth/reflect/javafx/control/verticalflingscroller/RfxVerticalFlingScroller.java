package nth.reflect.javafx.control.verticalflingscroller;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import javafx.animation.Interpolator;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoublePropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import nth.reflect.javafx.control.RfxControl;

/**
 * Flinging is the type of scrolling that occurs when a user drags and lifts her
 * finger quickly. After the user lifts her finger, you generally want to keep
 * scrolling (moving the viewport), but decelerate until the viewport stops
 * moving.
 * 
 * @author nilsth
 *
 */

@SuppressWarnings("restriction")
public class RfxVerticalFlingScroller implements RfxControl{

	protected static final double MIN_VELOCITY = 0.1;
	protected static final double MAX_SCROLL_VELOCITY = 1;
	protected static final double MIN_SCROLL_VELOCITY = 1;
	protected double lastYposition = 0;
	protected double firstYposition = 0;
	private final VelocityTracker velocityTracker;
	private final ReadOnlyDoubleProperty totalContentHeightProperty;
	private final Node node;
	private ScrollBar scrollBar;

	public RfxVerticalFlingScroller(Node node, ReadOnlyDoubleProperty totalContentHeightProperty) {
		this.node = node;
		this.totalContentHeightProperty = totalContentHeightProperty;
		this.velocityTracker = new VelocityTracker();
		node.setOnMousePressed(createOnMousePressedHandler());
		node.setOnMouseReleased(createOnMouseReleasedHandler());
		node.setOnMouseDragged(createOnMouseDraggerdHandler());
	}

	public RfxVerticalFlingScroller(ListView<?> listView) {
		this(listView, createTotalContentHeightProperty(listView));
	}

	private static ReadOnlyDoubleProperty createTotalContentHeightProperty(ListView<?> listView) {
		return new ReadOnlyDoublePropertyBase() {

			@SuppressWarnings({ "rawtypes" })
			@Override
			public double get() {
				VirtualFlow flow = (VirtualFlow) listView.lookup(".virtual-flow");
				int nrOfItems = listView.getItems().size();
				if (nrOfItems == 0) {
					return 0;
				}
				IndexedCell cell = flow.getCell(0);
				// assuming all cells have same size
				double cellHeight = cell.getBoundsInLocal().getHeight();
				double totalContentHeight = cellHeight * nrOfItems;
				return totalContentHeight;
			}

			@Override
			public String getName() {
				return null;
			}

			@Override
			public Object getBean() {
				return null;
			}
		};
	}

	private EventHandler<? super MouseEvent> createOnMouseReleasedHandler() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double velocity = velocityTracker.getVelocity();
				velocityTracker.clearDisplacement();
				if (Math.abs(velocity) > MIN_VELOCITY) {
					// movement at end of swipe gesture (fling gesture):
					moveAfterFling(velocity);
				}
			}

			/**
			 * scroll further up or down after flinging gesture (wheel of
			 * fortune effect)
			 * 
			 * @param velocity
			 */
			@SuppressWarnings({ "unchecked", "rawtypes" })
			private void moveAfterFling(double velocity) {
				ScrollBar scrollbar = getScrollBar();
				final Duration duration = Duration.millis(1200);
				BindableTransition transition = new BindableTransition(duration);
				transition.setInterpolator(Interpolator.EASE_OUT);
				double startValue = scrollbar.getValue();
				transition.setStartValue(startValue);
				double viewPortDisplacement =getDisplacement(getViewPortHeight());
				double fivePersentDisplacement =0.05;
				double displacement = Math.max(viewPortDisplacement, fivePersentDisplacement);
				transition.setEndValue(startValue - displacement * velocity);
				transition.fractionProperty().addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue observable, Object oldValue,
							Object newValue) {
						moveScrollBar((double) newValue, scrollbar);

					}
				});
				transition.play();
			}

		};

	}

	private EventHandler<MouseEvent> createOnMouseDraggerdHandler() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				double newYposition = event.getSceneY();

				double diff = newYposition - lastYposition;
				velocityTracker.addDisplacement(diff);
				lastYposition = newYposition;

				// if ((firstYposition - lastYposition) > MIN_VELOCITY
				// && (firstYposition - lastYposition) > 0
				// || (firstYposition - lastYposition) < (MIN_VELOCITY * -1)
				// && (firstYposition - lastYposition) < 0) {
				// //TODO list.getSelectionModel().clearSelection();
				// }

				moveDuringSwipe(diff);

			}

			private void moveDuringSwipe(double diff) {
				double displacement = getDisplacement(diff);
				ScrollBar scrollbar = getScrollBar();
				double newScrollBarValue = scrollbar.getValue() - displacement;
				moveScrollBar(newScrollBarValue, scrollbar);
			}

		};
	}

	private double getDisplacement(double diff) {
		double totalContentHeight = totalContentHeightProperty.get();
		double viewPortHeight = getViewPortHeight();
		double maxDisplacementHeight = totalContentHeight - viewPortHeight;
		if (maxDisplacementHeight < 0) {
			return 0;
		} else {
			return diff / maxDisplacementHeight;
		}
	}

	private double getViewPortHeight() {
		return node.getBoundsInLocal().getHeight();
	}

	private void moveScrollBar(double newScrollBarValue, ScrollBar scrollbar) {
		if (newScrollBarValue < 0) {
			newScrollBarValue = 0;
		}
		if (newScrollBarValue > 1) {
			newScrollBarValue = 1;
		}
		scrollbar.setValue(newScrollBarValue);
	}

	private ScrollBar getScrollBar() {
		if (scrollBar == null) {
			scrollBar = findScrollBar();
		}
		return scrollBar;
	}

	private ScrollBar findScrollBar() {
		for (Node child : node.lookupAll(".scroll-bar")) {
			if (child instanceof ScrollBar) {
				ScrollBar bar = (ScrollBar) child;
				if (bar.getOrientation().equals(Orientation.VERTICAL)) {
					return bar;
				}
			}
		}
		throw new RuntimeException("Could not find the vertical scrollbar in component: "
				+ node.getClass().getCanonicalName());
	}

	private EventHandler<MouseEvent> createOnMousePressedHandler() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				lastYposition = event.getSceneY();
				firstYposition = lastYposition;

			}
		};
	}

}
