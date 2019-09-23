package utils;

import pojo.DidaCalendar;

import java.util.List;

/**
 * @program: GTDHelper
 * @description:
 * 封装了对滴答清单日常信息进行处理的常用方法
 * @author: vcjmhg
 * @create: 2019-09-23 10:19
 **/
public class DidaUtils {
    private List<DidaCalendar> didaCalendarList=null;

    public DidaUtils(List<DidaCalendar> didaCalendarList) {
        this.didaCalendarList = didaCalendarList;
    }

    public List<DidaCalendar> getDidaCalendarList() {
        return didaCalendarList;
    }

    public void setDidaCalendarList(List<DidaCalendar> didaCalendarList) {
        this.didaCalendarList = didaCalendarList;
    }

    //TODO 增加format方法对清单进行处理，使其末尾都有代币数字
    private void fornat(){

    }
}

