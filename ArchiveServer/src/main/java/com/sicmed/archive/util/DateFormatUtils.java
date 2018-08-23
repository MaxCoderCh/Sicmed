package com.sicmed.archive.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    public static String dateToStr(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = dateFormat.format(date);

        return dateStr;
    }
}
