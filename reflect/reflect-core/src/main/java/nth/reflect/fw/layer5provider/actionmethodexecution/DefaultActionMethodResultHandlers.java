package nth.reflect.fw.layer5provider.actionmethodexecution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.fw.layer5provider.actionmethodexecution.result.NoResultHandler;

public class DefaultActionMethodResultHandlers {

	public static List<ActionMethodResultHandler> all = new ArrayList(Arrays.asList(new NoResultHandler()));

}
