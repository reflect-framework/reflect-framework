package nth.reflect.fw.ui.commandline.provider.actionmethodexecution.result;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethodexecution.result.NoResultHandler;

public class ActionMethodResultHandlerFactory
		implements nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandlerFactory {

	private final List<ActionMethodResultHandler> all;

	public ActionMethodResultHandlerFactory() {
		all = new ArrayList();
		all.add(new NoResultHandler());
		all.add(new FormResultHandler());
		all.add(new TableResultHandler());
	}

	@Override
	public List<ActionMethodResultHandler> getAll() {
		return all;
	}

}
