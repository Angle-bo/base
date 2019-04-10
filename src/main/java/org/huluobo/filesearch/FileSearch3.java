package org.huluobo.filesearch;

public class FileSearch3 {
public static void main(String[] args) {
	
	String fileName="1517466922000_1.docx";
	
	String tmp=fileName.substring(fileName.indexOf("_")+1, fileName.indexOf("."));
	System.out.println(tmp);
}
}
