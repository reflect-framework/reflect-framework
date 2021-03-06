package nth.reflect.fw.layer5provider.reflection.info;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

public interface NameInfo {
	/**
	 * @return the simple name e.g.:
	 *         <ul>
	 *         <ul>
	 *         <li>{@link Class}Name: e.g.:Customer (starts with a UPPER case
	 *         character)</li>
	 *         <li>{@link DomainObjectProperty}Name: e.g.: familyName (starts with a
	 *         lower case character)</li>
	 *         <li>{@link ActionMethod}Name: e.g.: login (starts with a lower case
	 *         character)</li>
	 *         </ul>
	 *         </p>
	 */
	public String getSimpleName();

	/**
	 * @return a canonicalName name that uniquely identifies:
	 *         <ul>
	 *         <li>a {@link Class}:<br>
	 *         Syntax: &lt;package&gt;.&lt;className&gt;<br>
	 *         E.g.: com.acme.web.shop.Customer</li>
	 *         <li>a {@link DomainObjectProperty}:<br>
	 *         Syntax: &lt;package&gt;.&lt;className&gt;.&lt;propertyName&gt;<br>
	 *         E.g.: com.acme.web.shop.Customer.familyName</li>
	 *         <li>a {@link ActionMethod}:<br>
	 *         Syntax: &lt;package&gt;.&lt;className&gt;.&lt;className&gt;<br>
	 *         E.g.: com.acme.web.shop.Customer.login</li>
	 *         </ul>
	 */
	public String getCanonicalName();

	public TranslatableString getDisplayName();

	public TranslatableString getDescription();
}
