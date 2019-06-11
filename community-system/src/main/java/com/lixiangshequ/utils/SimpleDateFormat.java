package com.lixiangshequ.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 格式化时间
 */
public class SimpleDateFormat {
    private  static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(LocalDateTime dateTime){
        return formatter.format(dateTime);
    }

    public static LocalDateTime parse(String strDate){
        return LocalDateTime.parse(strDate,formatter);
    }

}
