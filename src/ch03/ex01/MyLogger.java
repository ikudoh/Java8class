package ch03.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;

public class MyLogger {

	public static String logIf(Level level, BooleanSupplier criteria, Supplier<String> action) {
		String ret = "";
		if(level.equals(Level.FINEST)) {
			System.out.println("--- level finest");
			if(criteria.getAsBoolean()) {
				ret = action.get();
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] a = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int i = 10;
		// 全て通るケース
		System.out.println("*** 1 ***");
		System.out.println(MyLogger.logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]));
		// Levelは評価するがBooleanSupplierで処理が止まるケース
		System.out.println("*** 2 ***");
		System.out.println(MyLogger.logIf(Level.FINEST, () -> i == 9, () -> "a[10] = " + a[10]));
		//何も評価しないケース
		System.out.println("*** 3 ***");
		System.out.println(MyLogger.logIf(Level.WARNING, () -> i == 9, () -> "a[10] = " + a[10]));

	}
}
