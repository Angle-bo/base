package org.huluobo.filesearch;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileSearch5 {

    public static int mLastedClickTime = 0;

    public static String getSplitListStr(List<String> files, int limit) {
        //List<File> files = FileUtils.listFilesInDir(path);
       /* if (files == null || files.size() == 0) {
            return "";
        }*/
        int size = files.size();
        if (size <= limit) {
            limit = size;
        }
        if (mLastedClickTime > (size-1)) {
            mLastedClickTime = 0;
        }
        int r_size = size - mLastedClickTime;
        String str = null;
        if (r_size == limit) {
            str = String.join(",",files);
        } else {
            str = files.stream().skip(mLastedClickTime).limit(limit).collect(Collectors.joining(","));
        }
        mLastedClickTime++;
        return str;
    }
}
