package nth.reflect.account.vault.domain.account;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import nth.reflect.account.vault.domain.tag.Tag;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Account {
	private String accountName;
	private List<Tag> tags;
	private List<AccountAttribute> attributes;

	public Account() {
		attributes = new ArrayList<AccountAttribute>();
	}

	@NotNull
	@Order(value = 1)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Order(value = 2)
	public List<AccountAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AccountAttribute> attributes) {
		this.attributes = attributes;
	}

	public void attributesAddEmailAttribute(AccountAttribute emailAttribute) {
		attributes.add(emailAttribute);
	}

	public AccountAttribute attributesAddEmailAttributeParameterFactory() {
		AccountAttribute attribute = new AccountAttribute();
		attribute.setName("E-Mail");
		return attribute;
	}

	public void attributesAddUserNameAttribute(AccountAttribute userNameAttribute) {
		attributes.add(userNameAttribute);
	}

	public AccountAttribute attributesAddUserNameAttributeParameterFactory() {
		AccountAttribute attribute = new AccountAttribute();
		attribute.setName("Tag name");
		return attribute;
	}

	public void attributesAddPasswordAttribute(AccountAttribute passwordAttribute) {
		attributes.add(passwordAttribute);
	}

	public AccountAttribute attributesAddPasswordAttributeParameterFactory() {
		AccountAttribute attribute = new AccountAttribute();
		attribute.setName("Password");
		return attribute;
	}

	public void attributesAddUrlAttribute(AccountAttribute urlAttribute) {
		attributes.add(urlAttribute);
	}

	public AccountAttribute attributesAddUrlAttributeParameterFactory() {
		AccountAttribute attribute = new AccountAttribute();
		attribute.setName("URL");
		return attribute;
	}

	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void attributesAddCustomAttribute(AccountAttribute newAttribute) {
		attributes.add(newAttribute);
	}

	public AccountAttribute attributesAddCustomAttributeParameterFactory() {
		return new AccountAttribute();
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void attributesRemoveAttribute(AccountAttribute attribute) {
		attributes.remove(attribute);
	}

	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void attributesModifyAttribute(AccountAttribute attribute) {
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void attributesCopyAttributeValue(AccountAttribute attribute) {
		StringSelection selection = new StringSelection(attribute.getValue());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}

	@Order(value = 3)
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void tagsAddTag(Tag tag) {
		tags.add(tag);
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void tagsRemoveTag(Tag tag) {
		tags.remove(tag);
	}

	@Override
	public String toString() {
		return new TitleBuilder().setSeperator("-").append(tags).append(accountName).toString();
	}

}
