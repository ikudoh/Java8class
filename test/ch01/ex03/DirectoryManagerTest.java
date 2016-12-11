package ch01.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DirectoryManagerTest {

	@Test
	public void testGetFilesByLambda() {
		String extension = "txt";
		String path = System.getProperty("user.dir") + "\\test\\ch01\\ex03\\targetDirectory";
		File expect1 = new File(path + "\\text01.txt");
		File expect2 = new File(path + "\\text02.txt");
		DirectoryManager dm = new DirectoryManager();
		File[] actuals = dm.getFilesByLambda(path, extension);
		List<File> actualList = Arrays.asList(actuals);
		assertThat(actualList, hasItems(expect1, expect2));
	}

	@Test
	public void testGetFilesByLambda01() {
		String extension = "txt";
		String path = System.getProperty("user.dir") + "\\test\\ch01\\ex03\\targetDirectory";
		File expect1 = new File("text01.txt");
		File expect2 = new File("text02.txt");
		DirectoryManager dm = new DirectoryManager();
		File[] actuals = dm.getFilesByLambda01(path, extension);
		List<File> actualList = Arrays.asList(actuals);
		assertThat(actualList, hasItems(expect1, expect2));
	}

}
