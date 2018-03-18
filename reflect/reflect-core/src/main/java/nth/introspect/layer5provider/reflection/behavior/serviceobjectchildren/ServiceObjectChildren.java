package nth.introspect.layer5provider.reflection.behavior.serviceobjectchildren;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer2service.MainMenu;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer2service.ServiceObjectActionMethod;

/**
 * <p>
 * You can nest {@link ServiceObject}'s by adding a
 * {@link ServiceObjectChildren} annotation before the class keyword. It has the
 * following parameters:
 * <ul>
 * <Li>{@link #serviceClasses()}: an array of {@link ServiceObject} classes that
 * need to be displayed as children of that {@link ServiceObject}</li>
 * <Li>{@link #beforeActionMethods()}: a boolean to indicate if these children
 * should be displayed before or after the {@link ServiceObjectActionMethod}s
 * (default=before)</li>
 * </ul>
 * </p>
 * <p>
 * Example:
 * <p>
 * <code>
 * &#064;ServiceObjectChildren(serviceClasses = { CheckingAccountService.class, SavingsAccountService.class}, beforeActionMethods=false)<br>
 * public class BankAccountService {<br><br>
 * &nbsp;&nbsp;public void logout() {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;//logout<br>
 * &nbsp;&nbsp;}<br><br>
 * &nbsp;&nbsp;//etc...<br>
 * }<br>
 * </code>
 * </p>
 * </p>
 * <p>
 * Things to consider:
 * <ul>
 * <li>Do not nest to deep. The {@link MainMenu} is not very wide and a deep
 * tree structure is time consuming to navigate trough</li>
 * <li>You can nest the same {@link ServiceObject} as child of multiple other
 * {@link ServiceObject}s, but this is not recommended because it is more time
 * consuming to navigate trough.</li>
 * <li>Do not add a {@link ServiceObject} child if its type is already a
 * {@link ServiceObject} parent, to prevent infinite loops</li>
 * <li>The {@link ServiceObjectChildren} annotation only impacts the way the
 * {@link ServiceObject} are displayed in the {@link MainMenu}.
 * {@link ServiceObject}'s that are nested still need to be registered with the
 * {@link IntrospectApplication#getServiceClasses()} method.</li>
 * </ul>
 * </p>
 * 
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ServiceObjectChildren {
	public Class<?>[] serviceClasses();

	public boolean beforeActionMethods() default true;
}
