package org.huluobo.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesRead {

	public static void main(String[] args) {
		readFile8();
	}
	
	//JDK 1.6
	public static void readFile6() {
		// java 6使用FileReader创建BufferedReader
		try {
			BufferedReader reader = new BufferedReader(new FileReader("D:/raw_motion.csv"));
			String str = null;
			while ((str = reader.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// JDK 1.7
	public static void readFile7() {
		// java 7使用Path和Files获取BufferedReader
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get("D:/raw_motion.csv"));
			String str = null;
			while ((str = reader.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// JDK 1.8
	public static void readFile8() {
		// Java8 新增lines方法
		try {
			// Java8用流的方式读文件，更加高效
			Files.lines(Paths.get("D:/raw_motion.csv")).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
