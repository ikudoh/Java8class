package ch01.ex01;

import java.util.Arrays;
import java.util.Random;

public class ConfirmThread {

	private static boolean isUsingSameThread = false;

	public static void main(String args[]) {
		String mainThreadName = Thread.currentThread().getName();
		int numLength = 1024;
		Integer[] numbers = new Integer[numLength];
		Random random = new Random();
		for(int i = 0; i< numLength; i++) {
			numbers[i] = random.nextInt(numLength);
		}

		Arrays.sort(numbers, (first, second) -> {
			String comparatorThread = Thread.currentThread().getName();
			if(!mainThreadName.equals(comparatorThread)) {
				System.out.println("thread name: " + comparatorThread);
				isUsingSameThread = true;
			}
			return first.compareTo(second);
		});
		System.out.println("同じスレッドを使っているか？ : " + isUsingSameThread);
		System.out.println(Arrays.toString(numbers));
	}
}
