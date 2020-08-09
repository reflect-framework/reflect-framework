package nth.reflect.fw.javafx.layer5provider.actionmethod.prehandler.impl;

import java.io.File;

import javafx.stage.FileChooser;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.UploadStream;

public class SelectFileToUpload
		extends nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl.SelectFileToUpload {

	protected void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, UploadStream uploadStream) throws Exception {
		LanguageProvider languageProvider = container.get(LanguageProvider.class);

		FileChooser fileChooser = new FileChooser();
		String title = methodInfo.getTitle(uploadStream).getTranslation(languageProvider);
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		if (uploadStream.getFileExtentionFilters() != null && uploadStream.getFileExtentionFilters().length > 0) {
			fileChooser
					.getExtensionFilters()
					.addAll(new FileChooser.ExtensionFilter(uploadStream.getFileTypeDescription(),
							uploadStream.getFileExtentionFilters()));
		}
		ReflectApplicationForJavaFX application = container.get(ReflectApplicationForJavaFX.class);
		File selectedFile = fileChooser.showOpenDialog(application.getPrimaryStage());
		if (selectedFile != null) {
			uploadStream.setResult(selectedFile);
			methodParameter = uploadStream;
			ActionMethodInvoker invoker = new ActionMethodInvoker(container, methodInfo, methodOwner, methodParameter);
			invoker.run();
		}
	}

}
