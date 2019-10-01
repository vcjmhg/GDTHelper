package main;

import pojo.DidaCalendar;
import utils.DidaUtils;
import java.io.*;
import java.util.List;

/**
 * GDTHelper的入口类
 */
public class App {
    public static void main(String[] args) {
        DidaUtils didaUtils=new DidaUtils();
        List<DidaCalendar> didaCalendarList = null;
        try {
            didaCalendarList = didaUtils.getCalenderBeforeWeek();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //在控制台将日程信息按照完成的时间先后顺序进行输出
        String ctime = null;
        for(DidaCalendar didaCalendar:didaCalendarList){
//            System.out.println(didaCalendar);
            if(ctime==null){
                ctime = didaCalendar.getDueDate().substring(0, 10);
            }
            if(ctime.equals(didaCalendar.getDueDate().substring(0,10) )){
                System.out.println(didaCalendar.getTitle()+"   Date"+didaCalendar.getDueDate());
            }else{
                System.out.println("=================================");
                ctime=didaCalendar.getDueDate().substring(0,10);
                System.out.println(didaCalendar.getTitle()+"   Date"+didaCalendar.getDueDate());
            }
        }
    }
}
