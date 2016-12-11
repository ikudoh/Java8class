package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public class MyComparator {

	/**
	 * 与えられたフィールド値を比較する関数を生成します
	 * @param fieldNames 比較するフィールド名
	 * @return 比較関数
	 */
	public static Comparator<Object> lexicographicComparator(String... fieldNames) {
		return (x, y) -> {
			for(String fieldName : fieldNames) {
				Object xField = getFieldValue(x, fieldName);
				Object yField = getFieldValue(y, fieldName);
				if(xField.equals(yField)) {
					continue;
				}
				return ((Comparable<Object>)xField).compareTo(yField);
			}
			return 0;
		};
	}

	/**
	 * 指定したオブジェクトのフィールド値を取得します
	 * @param targetObject 取得元オブジェクト
	 * @param fieldName 取得対象フィールド名
	 * @return フィールド値
	 */
	private static Object getFieldValue(Object targetObject, String fieldName) {
		Class<?> clazz = targetObject.getClass();
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(targetObject);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public static void main(String[] args) {
		TestClass testObj01 = new TestClass();
		testObj01.lastname = "lastname";
		testObj01.firstname = "firstname01";

		TestClass testObj02 = new TestClass();
		testObj02.lastname = "lastname";
		testObj02.firstname = "firstname02";

		System.out.println(
				MyComparator.lexicographicComparator("lastname").compare(testObj01, testObj02)
				);
		System.out.println(
		MyComparator.lexicographicComparator("lastname", "firstname").compare(testObj01, testObj02)
				);

	}

}
