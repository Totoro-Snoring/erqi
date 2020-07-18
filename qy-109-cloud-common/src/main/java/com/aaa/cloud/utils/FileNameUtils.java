package com.aaa.cloud.utils;

import org.junit.Test;

import java.util.Date;
import java.util.Random;

/**
 * @Author: zcl
 * @ClassName: FileNameUtils
 * @Description:
 *      生成文件名
 *      System.currentTimeMillis():获取当前系统时间毫秒数
 *      一、时间的单位转换
 *
 *      1秒=1000毫秒(ms) 1毫秒=1／1,000秒(s)
 *      1秒=1,000,000 微秒(μs) 1微秒=1／1,000,000秒(s)
 *      1秒=1,000,000,000 纳秒(ns) 1纳秒=1／1,000,000,000秒(s)
 *      1秒=1,000,000,000,000 皮秒(ps) 1皮秒=1／1,000,000,000,000秒(s)
 *      new Date()所做的事情其实就是调用了System.currentTimeMillis()。
 *
 *      String类的format()方法用于创建格式化的字符串以及连接多个字符串对象。
 *      format(String format, Object... args) 新字符串使用本地语言环境，制定字符串格式和参数生成格式化的新字符串。
 *      format(Locale locale, String format, Object... args) 使用指定的语言环境，制定字符串格式和参数生成格式化的字符串。
 * @Date: 2020/7/10 19:02
 **/
public class FileNameUtils {
    private FileNameUtils() {
    }

    public static String getFileName() {
        //1.获取当前系统时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        //2.创建随机数对象
        Random random = new Random();
        //随机范围0-999之间
        int num = random.nextInt(999);
        //4. 生成最终的文件名
        return currentTimeMillis + String.format("%03d", num);
    }
//
//    public void getTime(){
//        System.out.println(System.currentTimeMillis());
//        System.out.println(new Date());
//    }
}
