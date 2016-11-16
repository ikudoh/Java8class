package ch01.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DirectoryManagerTest {

	@Test
	public void testGetSubDirectorysByLambda() {
		String path = System.getProperty("user.dir") + "\\test\\ch01\\ex02\\targetDirectory";
		File expect1 = new File(path + "\\subDirectory01");
		File expect2 = new File(path + "\\subDirectory02");
		File expect3 = new File(path + "\\subDirectory03");
		DirectoryManager dm = new DirectoryManager();
		File[] actuals = dm.getSubDirectorysByLambda(path);
		List<File> actualList = Arrays.asList(actuals);
		assertThat(actualList, hasItems(expect1, expect2, expect3));
	}

	@Test
	public void testGetSubDirectorysByMethodReference() {
		String path = System.getProperty("user.dir") + "\\test\\ch01\\ex02\\targetDirectory";
		File expect1 = new File(path + "\\subDirectory01");
		File expect2 = new File(path + "\\subDirectory02");
		File expect3 = new File(path + "\\subDirectory03");
		DirectoryManager dm = new DirectoryManager();
		File[] actuals = dm.getSubDirectorysByMethodReference(path);
		List<File> actualList = Arrays.asList(actuals);
		assertThat(actualList, hasItems(expect1, expect2, expect3));
	}

}
