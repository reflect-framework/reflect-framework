package nth.introspect.layer5provider.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultNotificationProvider implements NotificationProvider {

	private final List<NotificationListener> notificationListeners;
	private final List<Task> tasks;

	public DefaultNotificationProvider() {
		notificationListeners = new ArrayList<NotificationListener>();
		tasks = new ArrayList<Task>();
	}

	@Override
	public void addListener(NotificationListener notificationListener) {
		notificationListeners.add(notificationListener);
	}

	@Override
	public void remove(NotificationListener notificationListener) {
		notificationListeners.remove(notificationListener);
	}

	@Override
	public void refreshUserInterface() {
		for (NotificationListener notificationListener : notificationListeners) {
			notificationListener.onRefresh();
		}
	}

@Override
	public  void fireOnTaskChange(Task task) {
		for (NotificationListener notificationListener : notificationListeners) {
			notificationListener.onTaskChange(task);
		}
	}

	@Override
	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}

	@Override
	public void remove(Task task) {
		tasks.remove(task);
		fireOnTaskChange(task);
	}

	@Override
	public Task addNewTask(String title, String description, int currentValue, int maxValue) {
		Task task=new Task(this, title, description, currentValue, maxValue);
		tasks.add(task);
		fireOnTaskChange(task);
		return task;
	}

}
