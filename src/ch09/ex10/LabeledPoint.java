package ch09.ex10;

import java.util.Comparator;
import java.util.Objects;

public class LabeledPoint {
	private String label;
	private int x;
	private int y;

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof LabeledPoint)) {
			return false;
		}
		LabeledPoint lp = (LabeledPoint)obj;
		// p222
		return Objects.equals(label, lp.label) &&
				Objects.equals(x, lp.x) &&
				Objects.equals(y, lp.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, x, y);	// p223
	}

	public String getLabel() {
		return label;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int compareTo(LabeledPoint lp) {
		Comparator.comparing(labelComp)
		.thenComparing(xComp)
		.thenComparing(yComp);
	}

}
