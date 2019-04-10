package org.huluobo.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		  List<String> strings = Arrays.asList("6", "1", "3", "1","2");
		  Collections.sort(strings);//sort方法在这里
		  for (String string : strings) {
			System.out.println(string);
		}
	}

}
