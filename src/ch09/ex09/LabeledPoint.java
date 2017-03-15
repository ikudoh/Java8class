package ch09.ex09;

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

}
