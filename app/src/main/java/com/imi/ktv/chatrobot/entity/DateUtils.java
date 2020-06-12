package com.imi.ktv.chatrobot.entity;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
 
/**
 * author:Created by ZhangPengFei.
 * data: 2017/12/28
 * 时间格式化工具类
 */
 
public class DateUtils {
 
    @SuppressLint("SimpleDateFormat")
    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return df.format(date);
    }
}
