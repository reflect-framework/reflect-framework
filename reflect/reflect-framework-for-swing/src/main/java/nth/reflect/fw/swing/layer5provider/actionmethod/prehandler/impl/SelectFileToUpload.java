package nth.reflect.fw.swing.layer5provider.actionmethod.prehandler.impl;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.UploadStream;
import nth.reflect.fw.swing.UserinterfaceControllerForSwing;
import nth.reflect.fw.swing.mainwindow.MainWindow;

public class SelectFileToUpload
		extends nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl.SelectFileToUpload {

	@Override
	protected void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, UploadStream uploadStream) throws Exception {
		LanguageProvider languageProvider = container.get(LanguageProvider.class);
		UserinterfaceControllerForSwing userInterface = container.get(UserinterfaceControllerForSwing.class);
		MainWindow mainWindow = userInterface.getMainWindow();

		final JFileChooser fc = new JFileChooser();
		String title = methodInfo.getTitle(uploadStream).getTranslation(languageProvider);
		fc.setDialogTitle(title);
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(uploadStream.getFileTypeDescription(),
				uploadStream.getFileExtentionFilters());
		fc.setFileFilter(filter);
		int result = fc.showOpenDialog(mainWindow);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			uploadStream.setResult(selectedFile);
			methodParameter = uploadStream;
			ActionMethodInvoker invoker = new ActionMethodInvoker(container, methodInfo, methodOwner, methodParameter);
			invoker.run();
		}

	}

}
