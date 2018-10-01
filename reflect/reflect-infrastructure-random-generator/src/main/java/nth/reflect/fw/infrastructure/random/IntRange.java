package nth.reflect.fw.infrastructure.random;

import java.util.concurrent.ThreadLocalRandom;

public class IntRange {

	private final int min;
    private final int max;

    
    public IntRange(int size) {
    	if (size<0) {
    		size=1;
    	}
        this.min = size;
        this.max = size;
    }

    
    public IntRange(int min, int max) {
    	if (min<0) {
    		min=0;
    	}
    	if (max<0) {
    		max=0;
    	}
    	if (min>max) {
    		int oldMin = min;
    		min=max;
    		max=oldMin;
    	}
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    
    public int getRandomInt() {
    	if (min==max) {
    		return max;
    	} else {
    		return ThreadLocalRandom.current().nextInt(min, max);
    	}
    }

}