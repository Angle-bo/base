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
		//Map <String,Object> map=new HashMap<>();
		//map.put("a", "s");
		//System.out.println("s".equals(map.get("a")));
		text();
		
	}
	
	public static void text() {
		String path="f:/huoshan/vjtexiao/H1X1";
		File dir = new File(path);
		// 获取路径下的文件列表
		File[] fs = dir.listFiles();

		//循环找到文件名称，结尾序号最大的文件
		for (int i = 1; i <= fs.length; i++) {
			File file=fs[i-1];
			String fileName=file.getName();
			String[] split = fileName.split("\\.");
			String hexString = Integer.toHexString(i);
			hexString = hexString.length()>1?hexString:"0"+hexString;
			String newFileName=hexString.toUpperCase()+"."+split[1];
			file.renameTo(new File(path+"/"+newFileName));
			System.out.println("原文件名称:"+fileName+"#新文件名称:"+newFileName);
		}

	/*	for (File file : fs) {
			String fileName=file.getName();
			System.out.println(fileName);
			//int number=Integer.parseInt(fileName.substring(3, 4));
			//System.out.println(number);
			//String[] split = fileName.split("\\.");
			//String hexString = Integer.toHexString(Integer.parseInt(split[0]));
			//file.renameTo(new File(path+"/"+hexString+"."+split[1]));
			//System.out.println("原文件名称:"+fileName+"#新文件名称:"+hexString+"."+split[1]);
		}*/
	}
}
