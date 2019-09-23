package utils;

import pojo.TimeParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @program: GTDHelper
 * @description:
 * 封装一些对日期进行处理的类
 * @author: vcjmhg
 * @create: 2019-09-23 10:40
 **/
public class TimeUtils {
    /**
     * 获取距离本周最近的日期信息
     * @return timeParam
     */
    public TimeParam getRecentWeekStr(){
        Calendar calendar= Calendar.getInstance();
        TimeParam tp=new TimeParam();
        //往前获取最近的周日的日期
        while(calendar.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY){
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }

        tp.setTo(calendar.getTime());
        calendar.add(Calendar.DAY_OF_WEEK, -6);
        tp.setFrom(calendar.getTime());
        return tp;
    }
}

