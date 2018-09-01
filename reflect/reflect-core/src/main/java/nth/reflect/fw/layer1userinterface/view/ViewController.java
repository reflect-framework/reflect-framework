package nth.reflect.fw.layer1userinterface.view;

public interface ViewController<T> {

	public int getViewCount();
	public T getView(int index);
	public void setSelectedView(T view);
	public View getSelectedView();
	public void addView(T view);
	public void removeView(T view);
	
}
