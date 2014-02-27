package nth.introspect.provider.userinterface.view;

public interface ViewContainer<T> {

	public int getViewCount();
	public T getView(int index);
	public void setSelectView(T view);
	public View getSelectedView();
	public void addView(T view);
	public void removeView(T view);
	
}
