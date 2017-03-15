package ch09.ex08;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class PointCompareterTest {

	@Test
	public void testConpareToX_BiggerThanOther() {
		PointCompareter pc = new PointCompareter();
		pc.setX(Integer.MAX_VALUE);
		Point other = new Point(Integer.MIN_VALUE, 0);
		assertTrue(0 < pc.conpareTo(other));
	}

	@Test
	public void testConpareToX_SmallerThanOther() {
		PointCompareter pc = new PointCompareter();
		pc.setX(Integer.MIN_VALUE);
		Point other = new Point(0, 0);
		assertTrue(pc.conpareTo(other) < 0);
	}

	@Test
	public void testConpareToY_BiggerThanOther() {
		PointCompareter pc = new PointCompareter();
		pc.setY(Integer.MAX_VALUE);
		Point other = new Point(0, Integer.MIN_VALUE);
		assertTrue(0 < pc.conpareTo(other));
	}

	@Test
	public void testConpareToY_SmallerThanOther() {
		PointCompareter pc = new PointCompareter();
		pc.setY(Integer.MIN_VALUE);
		Point other = new Point(0, 0);
		assertTrue(pc.conpareTo(other) < 0);
	}

	@Test
	public void testConpareToXY_equal() {
		PointCompareter pc = new PointCompareter();
		Point other = new Point(0, 0);
		assertTrue(pc.conpareTo(other) == 0);
	}
}
