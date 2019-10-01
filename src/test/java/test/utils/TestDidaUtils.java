package test.utils;

import org.junit.Test;
import pojo.DidaCalendar;
import utils.DidaUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: GTDHelper
 * @description:
 * 测试DidaUtils类的使用情况
 * @author: vcjmhg
 * @create: 2019-10-01 16:39
 **/
public class TestDidaUtils {
    @Test
    public void testMethod_format(){
        String titles[]={"每天运动20分钟 1","看ted 0.5"," 看知乎课程 2 ","打扫卫生10.5"};
        List<DidaCalendar> didaCalendarList=new ArrayList<>();
        DidaCalendar calendar=null;
        for(String title:titles){
            calendar=new DidaCalendar();
            calendar.setTitle(title);
            didaCalendarList.add(calendar);
        }
        DidaUtils didaUtils=new DidaUtils();

        didaUtils.format(didaCalendarList);
    }
}

