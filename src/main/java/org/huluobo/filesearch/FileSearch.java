package org.huluobo.filesearch;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class FileSearch {
	public static void main(String[] args) {
		File dir = new File("e:/files");
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.indexOf("我的") != -1) {
					return true;
				} else {
					return false;
				}
			}
		};

		// 按照指定规则匹配到的文件列表
		File[] fs = dir.listFiles(filter);
		if(fs!=null) {
			//按照最后修改时间排序
			Arrays.sort(fs, new FileSearch.CompratorByLastModified());
			for (int i = 0; i < fs.length; i++) {
				System.out.println(fs[i].getName());

			}
		}else {
			System.out.println("无文件");
		}
		
	}

	static class CompratorByLastModified implements Comparator<File> {
		public int compare(File f1, File f2) {
			long diff = f1.lastModified() - f2.lastModified();
			if (diff > 0) {
				return 1;
			} else if (diff == 0) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}
