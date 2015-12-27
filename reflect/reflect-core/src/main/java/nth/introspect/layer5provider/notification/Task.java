package nth.introspect.layer5provider.notification;

public class Task {

	private final NotificationProvider notificationProvider;
	private final String name;
	private String description;
	private int maxValue;
	private int currentValue;

	public Task(final NotificationProvider notificationProvider,
			final String name, String description, int currentValue,
			int maxValue) {
		this.notificationProvider = notificationProvider;
		this.name = name;
		this.description = description;
		this.currentValue = currentValue;
		this.maxValue = maxValue;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		notificationProvider.fireOnTaskChange(this);
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
		notificationProvider.fireOnTaskChange(this);
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
		notificationProvider.fireOnTaskChange(this);
	}

}
