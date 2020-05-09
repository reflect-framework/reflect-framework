package nth.reflect.fw.layer5provider.notification;

import java.util.List;

import nth.reflect.fw.layer5provider.Provider;

/**
 * <ul>
 * <li>TODO What it does</li>
 * <li>TODO How you can use it (or explain who uses it)</li>
 * <li>TODO Code example</li>
 * <li>TODO How to register a custom ... provider</li>
 * </ul>
 */

public interface NotificationProvider extends Provider {

	public void addListener(NotificationListener notificationListener);

	public void remove(NotificationListener notificationListener);

	public void refreshUserInterface();

	public List<Task> getTasks();

	public void remove(Task task);

	public Task addNewTask(String string, String string2, int i, int j);

	public void fireOnTaskChange(Task task);

}
