package org.huluobo.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Read_CSV_To_List {

	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("D:/raw_motion.csv"));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String data[] = line.split(",");// CSV格式文件为逗号分隔符文件，这里根据逗号切分
				System.out.println(data[0] + "," + data[1]);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
