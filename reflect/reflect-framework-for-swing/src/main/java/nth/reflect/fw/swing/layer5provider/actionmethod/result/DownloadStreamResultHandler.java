package nth.reflect.fw.swing.layer5provider.actionmethod.result;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.DownloadStream;
import nth.reflect.fw.stream.FailedToOpenFileException;
import nth.reflect.fw.stream.FailedToSaveFileException;

public class DownloadStreamResultHandler
		extends nth.reflect.fw.layer5provider.actionmethod.result.handler.DownloadStreamResultHandler {

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		DownloadStream downloadStream = (DownloadStream) methodResult;
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(downloadStream.getFile());
		InputStream inputStream = downloadStream.getInputStream();

		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();

			saveFile(inputStream, file);

			openFile(file);
		}
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
