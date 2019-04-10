package org.huluobo.filesearch;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileSearch2 {
	public static void main(String[] args) {
		/*
		File file = new File("D:/forestoa/docSendFiles/");
		//如果路径不存在，新建
		if(!file.exists()&&!file.isDirectory()) {
		    file.mkdirs();
		}*/
		Map <String,Object> map=new HashMap<>();
		map.put("a", "s");
		System.out.println("s".equals(map.get("a")));
		
		
	}
	
	public static void text() {
		File dir = new File("e:/files");
		// 获取路径下的文件列表
		File[] fs = dir.listFiles();

		//循环找到文件名称，结尾序号最大的文件
		for (File file : fs) {
			String fileName=file.getName();
			System.out.println(fileName);
			int number=Integer.parseInt(fileName.substring(3, 4));
			System.out.println(number);
		}
	}
}
