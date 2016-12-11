package ch01.ex04;

import java.io.File;
import java.util.Arrays;

public class DirectoryManager {

	public File[] sort(File[] files) {
		Arrays.sort(files, (first, second) -> {
			if(first.isDirectory() && !second.isDirectory()) {
				return -1;
			} else if(!first.isDirectory() && second.isDirectory()) {
				return 1;
			} else {
				return first.getAbsolutePath().compareToIgnoreCase(second.getAbsolutePath());
			}
		});
		return files;
	}
}
