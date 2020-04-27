package nth.reflect.ui.vaadin;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.Command;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.DialogType;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.ui.vaadin.dialog.Dialog;
import nth.reflect.ui.vaadin.mainwindow.MainWindow;
import nth.reflect.ui.vaadin.tab.form.FormTab;
import nth.reflect.ui.vaadin.tab.form.row.PropertyPanel;
import nth.reflect.ui.vaadin.tab.form.row.PropertyPanelFactory;
import nth.reflect.ui.vaadin.tab.treetable.TreeTableTab;

public class UserInterfaceControllerForVaadin14
		extends GraphicalUserinterfaceController<nth.reflect.ui.vaadin.tab.Tab, PropertyPanel> {

	private final PropertyPanelFactory propertyPanelFactory;
	private final Dialog dialog;
	private final ReflectApplicationForVaadin14 reflectAppForVaadin;

	public UserInterfaceControllerForVaadin14(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		reflectAppForVaadin = userInterfaceContainer.get(ReflectApplicationForVaadin14.class);
		PropertyFieldProvider propertyFieldProvider = userInterfaceContainer.get(PropertyFieldProvider.class);
		propertyPanelFactory = new PropertyPanelFactory(propertyFieldProvider);
		dialog = new Dialog();
	}

	/**
	 * We do not have to create the {@link ReflectApplicationForVaadin}, because it
	 * is created by the Vaadin framework when is receives a
	 * {@link HttpServletRequest}.
	 */
	@Override
	public void launch() {
	}

	@Override
	public void showProgressDialog(TranslatableString taskDescription, int currentValue, int maxValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeProgressDialog() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMessage(TranslatableString message) {
		String translatedMessage = message.getTranslation(languageProvider);
		Notification notification = new Notification(translatedMessage, 3000);
		notification.open();
	}

	@Override
	public void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo, UploadStream uploadStream) {
		// TODO
		// final JFileChooser fc = new JFileChooser();
		// String title = TitleUtil.createTitle(reflectionProvider, methodInfo,
		// uploadStream);
		// fc.setDialogTitle(title);
		// fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		// FileNameExtensionFilter filter = new FileNameExtensionFilter(
		// uploadStream.getFileTypeDescription(),
		// uploadStream.fileExtentionFilters());
		// fc.setFileFilter(filter);
		// int result = fc.showOpenDialog(getMainWindow());
		// if (result == JFileChooser.APPROVE_OPTION) {
		// File selectedFile = fc.getSelectedFile();
		// uploadStream.setFile(selectedFile);
		// processActionMethodExecution(methodOwner, methodInfo, uploadStream);
		// }
	};

	@Override
	public void downloadFile(DownloadStream downloadStream) {
		// TODO
		// JFileChooser chooser = new JFileChooser();
		// chooser.setSelectedFile(downloadStream.getFile());
		// InputStream inputStream = downloadStream.getInputStream();
		// int returnVal = chooser.showSaveDialog(reflectApplicationForVaadin);
		// if (returnVal == JFileChooser.APPROVE_OPTION) {
		// File file = chooser.getSelectedFile();
		// // safe file
		// try {
		// OutputStream out = new FileOutputStream(file);
		// byte buf[] = new byte[1024];
		// int len;
		// while ((len = inputStream.read(buf)) > 0)
		// out.write(buf, 0, len);
		// out.close();
		// inputStream.close();
		//
		// } catch (Exception e) {
		// showErrorDialog("Error saving file", "Failed to save file.", e);
		// }
		// // open file
		// try {
		// Desktop.getDesktop().open(file);
		// } catch (Exception e) {
		// showErrorDialog("Error opening file", "Failed to open file.", e);
		// }
		// }

	}

	private MainWindow getMainWindow() {
		return reflectAppForVaadin.getMainWindow();
	}

	@Override
	public FormTab createFormTab(Object serviceObject, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object domainObject, FormMode formMode) {
		return new FormTab(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue, domainObject,
				formMode);
	}

	@Override
	public TreeTableTab createTreeTableTab(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		return new TreeTableTab(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue);
	}

	@Override
	public void openURI(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			TranslatableString title = ERROR_DIALOG_TITLE;
			TranslatableString message = ERROR_OPEN_URI.withParameters(uri.toString());
			showError(title, message, exception);
		}
	}

	@Override
	public void showDialog(DialogType dialogType, TranslatableString title, TranslatableString message,
			List<Item> items) {
		String translatedTitle = title.getTranslation(languageProvider);
		String translatedMessage = message.getTranslation(languageProvider);
		dialog.open(translatedTitle, translatedMessage, items);
	}

	@SuppressWarnings("serial")
	@Override
	public void executeInThread(Runnable methodExecutionRunnable) {
		Optional<UI> ui = getMainWindow().getUI();
		if (ui.isPresent()) {
			ui.get().access(new Command() {

				@Override
				public void execute() {
					methodExecutionRunnable.run();
					;
				}
			});
		}
	}

	@Override
	public nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory<PropertyPanel> getPropertyPanelFactory() {
		return propertyPanelFactory;
	}

}
