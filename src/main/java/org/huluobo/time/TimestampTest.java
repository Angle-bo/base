package org.huluobo.time;

import java.sql.Timestamp;

public class TimestampTest {

    public static void main(String[] args) {
        /**
         *
         year – the year minus 1900
         month – 0 to 11
         date – 1 to 31
         hour – 0 to 23
         minute – 0 to 59
         second – 0 to 59
         nano – 0 to 999,999,999
         */
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getYear());
        System.out.println(timestamp.getMonth());
        System.out.println(timestamp.getDate());
    }
}
