package nth.reflect.fw.vaadin.layer5provider.actionmethod.prehandler.impl;

import java.io.InputStream;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.upload.FinishedEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.UploadStream;

public class SelectFileToUpload
		extends nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl.SelectFileToUpload {

	private MemoryBuffer memoryBuffer;

	@Override
	protected void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, UploadStream uploadStream) throws Exception {
		memoryBuffer = new MemoryBuffer();

		Upload upload = new Upload(memoryBuffer);
//		upload.setVisible(false);
		if (methodParameter != null) {
			// TODO
			// fileTypes=uploadStream.getMimeTypes();
			// upload.setAcceptedFileTypes(fileTypes);
		}
		upload.addFinishedListener(createListener(container, methodInfo, methodOwner, methodParameter));

		// TODO javascript to trigger upload does not work...
		upload.getUploadButton().getElement().executeJs("$0.click()");
		upload.getElement().executeJs("$0.uploadFiles()");
	}

	private ComponentEventListener<FinishedEvent> createListener(UserInterfaceContainer container,
			ActionMethodInfo methodInfo, Object methodOwner, Object methodParameter) {
		return new ComponentEventListener<FinishedEvent>() {

			private static final long serialVersionUID = -382263209591709879L;

			@Override
			public void onComponentEvent(FinishedEvent event) {
				String fileName = event.getFileName();
				InputStream inputStream = memoryBuffer.getInputStream();
				UploadStream uploadStream = (UploadStream) methodParameter;
				uploadStream.setResult(inputStream, fileName);
				ActionMethodInvoker invoker = new ActionMethodInvoker(container, methodInfo, methodOwner,
						methodParameter);
				invoker.run();
			}

		};
	}

}
