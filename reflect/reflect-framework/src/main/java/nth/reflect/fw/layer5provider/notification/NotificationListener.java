package nth.reflect.fw.layer5provider.notification;

public interface NotificationListener {

	public void onTaskChange(Task task);
	
	public void onRefresh();

	public void onNewMessage(String title, String message);
}
