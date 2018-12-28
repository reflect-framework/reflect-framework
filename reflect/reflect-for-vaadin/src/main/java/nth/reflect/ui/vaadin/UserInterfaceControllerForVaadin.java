package nth.reflect.ui.vaadin;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.DialogType;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.component.tab.form.FormMode;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyPanelFactory;
import nth.reflect.ui.vaadin.mainwindow.MainWindow;
import nth.reflect.ui.vaadin.tab.form.FormTab;
import nth.reflect.ui.vaadin.tab.form.row.PropertyPanel;
import nth.reflect.ui.vaadin.tab.table.TableTab;
import nth.reflect.ui.vaadin.tab.treetable.TreeTableTab;

public class UserInterfaceControllerForVaadin extends GraphicalUserinterfaceController<nth.reflect.ui.vaadin.tab.Tab, PropertyPanel> {

	private final ReflectApplicationForVaadin reflectAppForVaadin;
	private final nth.reflect.ui.vaadin.tab.form.row.PropertyPanelFactory propertyPanelFactory;

	public UserInterfaceControllerForVaadin(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		reflectAppForVaadin = userInterfaceContainer.get(ReflectApplicationForVaadin.class);
		propertyPanelFactory = new nth.reflect.ui.vaadin.tab.form.row.PropertyPanelFactory();
	}

	/**
	 * We do not have to create the {@link ReflectApplicationForVaadin}, because
	 * it is created by the Vaadin framework when is receives a
	 * {@link HttpServletRequest}.
	 */
	@Override
	public void launch() {
	}

	@Override
	public void showProgressDialog(String taskDescription, int currentValue, int maxValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeProgressDialog() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showInfoMessage(String message) {
		// TODO Toast.makeText(reflectApplicationForVaadin, message,
		// Style.NORMAL).display();
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
	public TableTab createTableTab(Object serviceObject, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		return new TableTab(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue);
	}

	@Override
	public  TreeTableTab createTreeTableTab(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		return new TreeTableTab(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue);
	}

	@Override
	public void openURI(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			showErrorDialog("Error", "Error browsing URI: " + uri.toString(), exception);
		}
	}

	@Override
	public void showDialog(DialogType dialogType, String title, String message, List<Item> items) {

		// // get dialog type
		// int messageType = 0;
		// switch (dialogType) {
		// case QUESTION:
		// messageType = JOptionPane.QUESTION_MESSAGE;
		// break;
		// case ERROR:
		// messageType = JOptionPane.ERROR_MESSAGE;
		// }
		//
		// // get options, assuming that all items are enabled and visible
		// Object[] options = new String[items.size()];
		// for (int index = 0; index < items.size(); index++) {
		// options[index] = items.get(index).getText();
		// }
		// Object defaultOption = options[items.size() - 1];

		// show dialog
		// TODO int selectedIndex =
		// JOptionPane.showOptionDialog(reflectApplicationForVaadin, message,
		// title,
		// JOptionPane.DEFAULT_OPTION, messageType, null, options,
		// defaultOption);
		//
		// // execute selected item
		// if (selectedIndex != -1) {
		// VaadinItem selectedItem = items.get(selectedIndex);
		// Action action = selectedItem.getAction();
		// if (action != null) {
		// action.run();
		// }
		// }

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
	public PropertyPanelFactory<PropertyPanel> getPropertyPanelFactory() {
		return propertyPanelFactory;
	}

}
