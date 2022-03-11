package org.huluobo.filesearch;


import cn.hutool.core.io.FileUtil;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class FileSearch4 {
    public static void main(String[] args) {
       List<String> files = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            files.add("name_" + i);
        }
        //String path ="E:/IDM";
        //List<String> files = FileUtil.listFileNames(path);

        FileSearchUtil fileSearchUtil = new FileSearchUtil(files);
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 30; i++) {
            String limit = fileSearchUtil.limit(10);
            stopWatch.split();
            System.out.println("第" + i + "次，" + limit + "##" + stopWatch.getSplitTime());
        }
        System.out.println("==========================================");
        for (int i = 0; i < 30; i++) {
            String limit=  FileSearch5.getSplitListStr(files,10);
            stopWatch.split();
            System.out.println("第" + i + "次，"+ limit + "##" + stopWatch.getSplitTime());
        }
    }
}
