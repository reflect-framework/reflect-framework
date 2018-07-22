package nth.reflect.fw.layer5provider.reflection.behavior.executionmode;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObject;

/**
 * <ul>
 * <li>{@link ExecutionModeType#EXECUTE_METHOD_DIRECTLY} : executes the method
 * directly without user intervention</li>
 * <li>{@link ExecutionModeType#EXECUTE_METHOD_AFTER_CONFORMATION}: the
 * {@link UserInterfaceController} opens a confirmation dialog. The method is
 * executed after the user activates the confirmation button. The method is NOT
 * executed when the user cancels the confirmation dialog.</li>
 * <li>
 * {@link ExecutionModeType#EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL}: the
 * {@link UserInterfaceController} opens a form on a new tab, so that the user
 * can modify (edit) the {@link DomainObject}. The method is executed with the
 * edited {@link DomainObject} as the method parameter, when the user clicks the
 * confirmation button on the button bar. The method is NOT executed when the
 * user clicks on cancel in the bottom bar.</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public enum ExecutionModeType {
	EXECUTE_METHOD_DIRECTLY, EXECUTE_METHOD_AFTER_CONFORMATION, EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL
}