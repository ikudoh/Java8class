package ch06.ex08;

import java.util.Arrays;
import java.util.Random;

public class SortSpeedChecker {
	public static void main(String[] args) {

		int arrayNum = 1024*50;	// ここの値を増やしながら傾向を見る
		Double[] datas = new Double[arrayNum];
		Random random = new Random();

		// Arrays.sort
		for(int i = 0; i < arrayNum; i++) {
			datas[i] = random.nextDouble();
		}
		System.out.println("配列数 : " + arrayNum);
		long start = System.nanoTime();
		Arrays.sort(datas);
		long sortTime = System.nanoTime() - start;
		System.out.println("Arrays.sort time : " + sortTime / 1E9 + "秒");

		// Arrays.parallelSort
		for(int i = 0; i < arrayNum; i++) {
			datas[i] = random.nextDouble();
		}
		start = System.nanoTime();
		Arrays.parallelSort(datas);
		long parallelSortTime = System.nanoTime() - start;
		System.out.println("Arrays.parallelSort time: " + parallelSortTime / 1E9 + "秒");

		System.out.println("Arrays.parallelSort は Arrays.sort と比べ "
				+ ((parallelSortTime - sortTime) / 1E9) + "秒長い");
		// かなりばらつきあって微妙…


	}
}
