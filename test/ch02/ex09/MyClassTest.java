package ch02.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyClassTest {

	static final String DATA_1 = "data1";
	static final String DATA_2 = "data2";
	static final String DATA_22 = "data22";
	static final String DATA_3 = "data3";
	static final String DATA_32 = "data32";
	static final String DATA_33 = "data33";
	static final String DELIMITER = ", ";
	static final String EXPECT = DATA_1
			+ DELIMITER + DATA_2
			+ DELIMITER + DATA_22
			+ DELIMITER + DATA_3
			+ DELIMITER + DATA_32
			+ DELIMITER + DATA_33;

	@Test
	public void testGetSummarizedData1() {
		List<ArrayList<String>> lists = new ArrayList<>();
		ArrayList<String> data1 = new ArrayList<>();
		data1.add(DATA_1);
		lists.add(data1);
		ArrayList<String> data2 = new ArrayList<>();
		data2.add(DATA_2);
		data2.add(DATA_22);
		lists.add(data2);
		ArrayList<String> data3 = new ArrayList<>();
		data3.add(DATA_3);
		data3.add(DATA_32);
		data3.add(DATA_33);
		lists.add(data3);

		ArrayList<String> ret = MyClass.getSummarizedData1(lists);
		String result = String.join(DELIMITER, ret);
		assertThat(result, is(EXPECT));
		System.out.println(result);
	}

	@Test
	public void testGetSummarizedData2() {
		List<ArrayList<String>> lists = new ArrayList<>();
		ArrayList<String> data1 = new ArrayList<>();
		data1.add(DATA_1);
		lists.add(data1);
		ArrayList<String> data2 = new ArrayList<>();
		data2.add(DATA_2);
		data2.add(DATA_22);
		lists.add(data2);
		ArrayList<String> data3 = new ArrayList<>();
		data3.add(DATA_3);
		data3.add(DATA_32);
		data3.add(DATA_33);
		lists.add(data3);

		ArrayList<String> ret = MyClass.getSummarizedData2(lists);
		String result = String.join(DELIMITER, ret);
		assertThat(result, is(EXPECT));
		System.out.println(result);
	}

	@Test
	public void testGetSummarizedData3() {
		List<ArrayList<String>> lists = new ArrayList<>();
		ArrayList<String> data1 = new ArrayList<>();
		data1.add(DATA_1);
		lists.add(data1);
		ArrayList<String> data2 = new ArrayList<>();
		data2.add(DATA_2);
		data2.add(DATA_22);
		lists.add(data2);
		ArrayList<String> data3 = new ArrayList<>();
		data3.add(DATA_3);
		data3.add(DATA_32);
		data3.add(DATA_33);
		lists.add(data3);

		ArrayList<String> ret = MyClass.getSummarizedData3(lists);
		String result = String.join(DELIMITER, ret);
		assertThat(result, is(EXPECT));
		System.out.println(result);
	}

}
