package nth.reflect.ui.vaadin.layer5provider.actionmethodexecution;

import java.io.InputStream;
import java.util.UUID;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.DownloadStream;

/**
 * See:
 * https://vaadin.com/forum/thread/17909506/vaadin-14-let-user-to-download-a-file-by-clicking-on-menu-item
 * 
 * @author nilsth
 *
 */
public class DownloadStreamResultHandler
		extends nth.reflect.fw.layer5provider.actionmethodexecution.result.DownloadStreamResultHandler {

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		DownloadStream downloadStream = (DownloadStream) methodResult;

		String fileName = downloadStream.getFile().getName();

		StreamResource streamResource = createStreamResource(fileName, downloadStream);

		String id = UUID.randomUUID().toString();

		Anchor anchor = new Anchor(streamResource, "");
		anchor.getStyle().set("display", "hidden");
		anchor.setId(id);
		anchor.getElement().setAttribute("download", true);
		UI.getCurrent().add(anchor);

		Page page = UI.getCurrent().getPage();
		String javaScript = createClickAndRemoveJavaScript(id);
		page.executeJs(javaScript);
	}

	private String createClickAndRemoveJavaScript(String id) {
		return "var element=document.getElementById('" + id
				+ "'); element.click(); document.body.removeChild(element);";
	}

	private StreamResource createStreamResource(String fileName, DownloadStream downloadStream) {
		InputStreamFactory inputStreamFactory = createInputStreamFactory(downloadStream);

		StreamResource streamResource = new StreamResource(fileName, inputStreamFactory);

		return streamResource;
	}

	private InputStreamFactory createInputStreamFactory(DownloadStream downloadStream) {
		return new InputStreamFactory() {

			private static final long serialVersionUID = 1L;

			@Override
			public InputStream createInputStream() {
				return downloadStream.getInputStream();
			}
		};
	}

}
