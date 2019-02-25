package nth.reflect.fw.junit.generic.titlebuilder;

import static org.junit.Assert.assertEquals;

import nth.reflect.fw.generic.util.TitleBuilder;

public class TitleBuilderTestThread extends Thread {

	@Override
	public void run() {
		TitleBuilder titleBuilder = null;
		for (int i = 0; i < 100; i++) {
			titleBuilder = TitleBuilder.getInstance();
			titleBuilder.contact(i);
			assertEquals(Integer.toString(i), titleBuilder.toString());
		}

	}

}
