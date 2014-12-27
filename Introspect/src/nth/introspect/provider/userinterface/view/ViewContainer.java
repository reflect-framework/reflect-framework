package nth.introspect.provider.userinterface.view;

public interface ViewContainer<T> {

	public int getViewCount();
	public T getView(int index);
	public void setSelectedView(T view);
	public View getSelectedView();
	public void addView(T view);
	public void removeView(T view);
	
}
