package ch08.ex07;

import java.util.Arrays;
import java.util.Comparator;

public class SortOrder {

	public static void main(String[] args) {
		// P191 naturalOrder().reversed() は reverseOrder()と同じ
//		Comparator<String> comparator = Comparator.nullsFirst(Comparator.naturalOrder()).reversed();
		Comparator<String> comparator = Comparator.nullsLast(Comparator.reverseOrder());

		String[] strs = {"abc", "def", null, "xyz", "123", null};
		Arrays.sort(strs, comparator);
		Arrays.stream(strs).forEach(str -> System.out.println(str));
	}
}
