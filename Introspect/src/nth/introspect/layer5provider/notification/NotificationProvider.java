package nth.introspect.layer5provider.notification;


import java.util.List;

import nth.introspect.layer5provider.Provider;

public interface NotificationProvider extends Provider {

	public void addListener(NotificationListener notificationListener);
	
	public void remove(NotificationListener notificationListener);
	
	public void refreshUserInterface();
	
	public List<Task> getTasks();
	
	public void remove(Task task);

	public Task addNewTask(String string, String string2, int i, int j);

	public void fireOnTaskChange(Task task);

	
}
