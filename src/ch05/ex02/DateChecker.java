package ch05.ex02;

import java.time.LocalDate;

public class DateChecker {

	public static final LocalDate BASE_DATE = LocalDate.of(2000, 2, 29);

	public static void main(String[] args) {

		// Q1. 1年を加算するとどうなるか？
		LocalDate date = BASE_DATE;
		date = date.plusYears(1);
		System.out.println("1. " + date);	// A1. 1. 2001-02-28

		// Q2. 4年を加算するとどうなるか？
		date = BASE_DATE;
		date = date.plusYears(4);
		System.out.println("2. " + date);	// A2. 1. 2. 2004-02-29

		// Q3. 1年を4回加算するとどうなるか？
		date = BASE_DATE;
		date = date.plusYears(1);
		System.out.println("3-1. " + date);	// A3-1. 1. 2. 2001-02-28
		date = date.plusYears(1);
		System.out.println("3-2. " + date);	// A3-2. 1. 2. 2002-02-28
		date = date.plusYears(1);
		System.out.println("3-3. " + date);	// A3-3. 1. 2. 2003-02-28
		date = date.plusYears(1);
		System.out.println("3-4. " + date);	// A3-4. 1. 2. 2004-02-28 
		
		// 基本は指定年後の同じ日、
		// 元とする日付がうるう年ならそれを加味した日付が出力される。
	}
}

