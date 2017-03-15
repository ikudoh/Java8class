package ch09.ex08;

import java.awt.Point;

public class PointCompareter extends Point {

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public int conpareTo(Point other) {
		int diff = Integer.compare(x, other.x);	// オーバーフローの危険はない
		if(diff != 0) {
			return diff;
		}
		return Integer.compare(y, other.y);
	}
}
