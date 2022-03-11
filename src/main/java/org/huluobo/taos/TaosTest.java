package org.huluobo.taos;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.taosdata.jdbc.TSDBDriver;

import java.sql.*;
import java.util.Date;
import java.util.Properties;

public class TaosTest {
    public static void main(String[] args) throws Exception {
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        String jdbcUrl = "jdbc:TAOS://hssj-H310M-D2VX-SI-2-0:6030/hsmanager?charset=UTF-8&locale=en_US.UTF-8&timezone=UTC-8";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_USER, "root");
        //connProps.setProperty(TSDBDriver.PROPERTY_KEY_PASSWORD, "taosdata");
        Connection conn = DriverManager.getConnection(jdbcUrl,connProps);
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from hsmanager.device_fodder_download");

        Timestamp ts;
        int fodderType;
        int fodderFileType;
        String fodderId;
        Long fileSize;
        Date downloadTime;
        String remark;
        String serialNo;
        while(resultSet.next()){
            ts = resultSet.getTimestamp("ts");
            fodderType = resultSet.getInt("fodder_type");
            fodderFileType = resultSet.getInt("fodder_file_type");
            fodderId = resultSet.getString("fodder_id");
            fileSize = resultSet.getLong("file_size");
            downloadTime = resultSet.getTimestamp("download_time");
            remark = resultSet.getString("remark");
            serialNo = resultSet.getString("serial_no");
            System.out.printf("%s,资源类型: %s,资源文件类型: %s,资源ID:%s, 资源大小:%s,下载时间:%s,备注:%s,序列号:%s\n",  DateUtil.formatDateTime(ts),
                    fodderType, fodderFileType,fodderId,fileSize, DateUtil.formatDateTime(downloadTime),remark,serialNo);
        }

        String now = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT);
        System.out.println(now);

        resultSet.close();
        stmt.close();
        conn.close();
    }
}
