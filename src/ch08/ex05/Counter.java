package ch08.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Counter {

	public static void main(String[] args) throws IOException {
		String aliceContents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + "/src/ch08/ex05/Alice.txt")),
				StandardCharsets.UTF_8);
		// asList()は固定サイズのリストを返すため、
		// そのままではremoveIf()内のremoveとかできない。
		// ArrayListに変換して使う。
		List<String> aliceWords =  new ArrayList<>(Arrays.asList(aliceContents.split("[\\P{L}]+")));

		System.out.println("--- Alice ---");
		// 2章 P28の処理
		long start = System.nanoTime();
		long count = aliceWords.stream().filter(w -> w.length() > 12).count();
		long end = System.nanoTime();
		System.out.println("count: " + count + ", stream time: " + (end - start));

		// filterの逆の処理
		start = System.nanoTime();
		aliceWords.removeIf(w -> w.length() <= 12);
		count = aliceWords.size();
		end = System.nanoTime();
		System.out.println("count: " + count + ", no-stream time: " + (end - start));

		String wapContents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + "/src/ch08/ex05/WarAndPeace.txt")),
				StandardCharsets.UTF_8);
		List<String> wapWords =  new ArrayList<>(Arrays.asList(aliceContents.split("[\\P{L}]+")));

		System.out.println("--- WarAndPeace ---");
		// 2章 P28の処理
		start = System.nanoTime();
		count = wapWords.stream().filter(w -> w.length() > 12).count();
		end = System.nanoTime();
		System.out.println("count: " + count + ", stream time: " + (end - start));

		// filterの逆の処理
		start = System.nanoTime();
		wapWords.removeIf(w -> w.length() <= 12);
		count = wapWords.size();
		end = System.nanoTime();
		System.out.println("count: " + count + ", no-stream time: " + (end - start));

		// 実行結果
		// 短いテキストAliceはstreamを使わない方が早い。
		// 長いテキストWarAndPeaceはほぼ差がない。（↓はstreamの方が早いが、逆転することもある）
//		--- Alice ---
//		count: 17, stream time: 52271069
//		count: 17, no-stream time: 3121433
//		--- WarAndPeace ---
//		count: 17, stream time: 2135267
//		count: 17, no-stream time: 2308467


	}
}
