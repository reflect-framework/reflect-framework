package nth.reflect.fw.javafx.layer5provider.actionmethod.result;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.DownloadStream;
import nth.reflect.fw.stream.FailedToOpenFileException;
import nth.reflect.fw.stream.FailedToSaveFileException;

public class DownloadStreamResultHandler
		extends nth.reflect.fw.layer5provider.actionmethod.result.handler.DownloadStreamResultHandler {

	public static final TranslatableString TITLE = new TranslatableString(
			DownloadStreamResultHandler.class.getCanonicalName() + ".title", "%s: Save as:");

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {

		DownloadStream downloadStream = (DownloadStream) methodResult;

		javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
		File parentFolder = downloadStream.getFile().getParentFile();
		if (parentFolder != null) {
			fileChooser.setInitialDirectory(parentFolder);
		}
		fileChooser.setInitialFileName(downloadStream.getFile().getName());

		String title = createTitle(container, methodInfo, methodParameter);
		fileChooser.setTitle(title);

		File file = fileChooser.showSaveDialog(null);

		InputStream inputStream = downloadStream.getInputStream();

		if (file != null) {

			saveFile(inputStream, file);

			openFile(file);
		}
	}

	private String createTitle(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodParameter) {
		LanguageProvider languageProvider = container.get(LanguageProvider.class);
		TranslatableString methodTitle = methodInfo.getTitle(methodParameter);
		String title = TITLE.withParameters(methodTitle).getTranslation(languageProvider);
		return title;
	}

	private void openFile(File file) {
		try {
			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			throw new FailedToOpenFileException(file, e);
		}
	}

	private void saveFile(InputStream inputStream, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte buf[] = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0)
				out.write(buf, 0, len);
			out.close();
			inputStream.close();
		} catch (Exception e) {
			throw new FailedToSaveFileException(file, e);
		}
	}

}
