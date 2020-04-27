package nth.reflect.fw.junit;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandlerFactory;

public class JunitActionMethodResultHandlerFactory implements ActionMethodResultHandlerFactory {

	private final List<ActionMethodResultHandler> all;

	public JunitActionMethodResultHandlerFactory() {
		all = new ArrayList();
		all.add(new JunitActionMethodResultHandler());
	}

	@Override
	public List<ActionMethodResultHandler> getAll() {
		return all;
	}

}
