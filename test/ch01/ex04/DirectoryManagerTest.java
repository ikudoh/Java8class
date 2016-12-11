package ch01.ex04;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

public class DirectoryManagerTest {

	@Test
	public void testSort() {
		String path = System.getProperty("user.dir") + "\\test\\ch01\\ex04\\targetDirectory";
		File[] files = new File(path).listFiles();
		String expect1 = new File(path + "\\subDirectory01").getName();
		String expect2 = new File(path + "\\subDirectory03").getName();
		String expect3 = new File(path + "\\zzSubDirectory02").getName();
		String expect4 = new File(path + "\\aaText02.txt").getName();
		String expect5 = new File(path + "\\text01.txt").getName();

		DirectoryManager dm = new DirectoryManager();
		File[] actuals = dm.sort(files);
		String[] actualNames = new String[actuals.length];
		for(int i = 0; i < actualNames.length; i++) {
			actualNames[i] = actuals[i].getName();
		}
		System.out.println(Arrays.toString(actualNames));

		assertTrue(Arrays.equals(actualNames, new String[]{expect1, expect2, expect3, expect4, expect5}));
	}

}
