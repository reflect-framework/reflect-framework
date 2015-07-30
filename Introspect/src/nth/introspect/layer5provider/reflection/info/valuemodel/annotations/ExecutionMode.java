package nth.introspect.layer5provider.reflection.info.valuemodel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo.ExecutionModeType;
/**
* <ul>
* <li>@{@link ExecutionMode}({@link ExecutionModeType#EXECUTE_METHOD_DIRECTLY}
* ): executes the method directly without user intervention</li>
* <li>@{@link ExecutionMode}(
* {@link ExecutionModeType#EXECUTE_METHOD_AFTER_CONFORMATION}): the
* {@link UserInterfaceController} opens a confirmation dialog. The method is
* executed after the user activates the confirmation button. The method is NOT
* executed when the user cancels the confirmation dialog.</li>
* <li>@{@link ExecutionMode}(
* {@link ExecutionModeType#EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL}): the
* {@link UserInterfaceController} opens a form on a new tab, so that the user
* can modify (edit) the {@link DomainObject}. The method is executed with the
* edited {@link DomainObject} as the method parameter, when the user clicks the
* confirmation button on the button bar. The method is NOT executed when the
* user clicks on cancel in the bottom bar.</li>
* </ul>
*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecutionMode {
	public ExecutionModeType value();
}
