package org.huluobo.filesearch;

import java.util.List;
import java.util.stream.Collectors;

public class FileSearchUtil {

    private List<String> fileNames;

    private Long index;

    public FileSearchUtil(List<String> fileNames) {
        this.index = 0L;
        this.fileNames = fileNames;
    }

    public String limit(Integer pageSize) {
        if (pageSize > fileNames.size()) {
            if(index==0){
                this.index ++;
                return String.join(",",fileNames);
            }
            pageSize = fileNames.size();
        }
        if(index>fileNames.size()-1){
            this.index = 0L;
        }
        String collect = fileNames.stream().skip(index).limit(pageSize).collect(Collectors.joining(","));
        this.index ++;
        return collect;
    }

}
