package nth.reflect.fw.javafx.control.verticalflingscroller;

import java.util.ArrayList;
import java.util.List;

public class VelocityTracker {
	private static final int SIGNIFICANT_DISPLACEMENT = 20;
	private static final double MAX_POS_VELOCITY = 1;
	private static final double MAX_NEG_VELOCITY = MAX_POS_VELOCITY * -1;
	private static final int MAX_NR_DISPLACEMENTS = 5;
	private List<Double> displacements;

	public VelocityTracker() {
		this.displacements = new ArrayList<>();
	}

	public void addDisplacement(double displacement) {
		displacements.add(displacement);
		if (displacements.size() > MAX_NR_DISPLACEMENTS) {
			displacements.remove(0);
		}
	}

	public void clearDisplacement() {
		displacements.clear();
	}

	public double getVelocity() {
		if (displacements.size() == 0) {
			return 0;
		}
		double averageDisplacement = getAverageDisplacement();
		double velocity = averageDisplacement / SIGNIFICANT_DISPLACEMENT;
		if (velocity > MAX_POS_VELOCITY) {
			return MAX_POS_VELOCITY;
		} else if (velocity < MAX_NEG_VELOCITY) {
			return MAX_NEG_VELOCITY;
		} else {
			return velocity;
		}
	}

	private double getAverageDisplacement() {
		double total = 0;
		for (Double displacement : displacements) {
			total += displacement;
		}
		return total / displacements.size();
	}
}
