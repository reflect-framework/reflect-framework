package nth.reflect.fw.layer5provider.reflection.behavior.executionmode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * {@link ActionMethod}s can be annotated, so that the
 * {@link UserInterfaceController} knows how the {@link ActionMethod} needs to
 * be invoked after the user has clicked on the corresponding menu item. Note
 * that the {@link ExecutionMode} can not be changed during runtime.
 * </p>
 * <p>
 * Syntax: @ExecutionMode({@link ExecutionModeType} mode)
 * </p>
 * <p>
 * {@link ExecutionModeType}:
 * </p>
 * <p>
 * {@insert ExecutionModeType}
 * </p>
 * 
 * @author nilsth
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecutionMode {
	public ExecutionModeType mode();
}
