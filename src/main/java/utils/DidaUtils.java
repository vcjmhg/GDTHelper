package utils;

import org.apache.http.client.methods.HttpGet;
import pojo.DidaCalendar;
import pojo.TimeParam;
import sun.reflect.generics.scope.Scope;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
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

    /**
     * 传入指定日期获取对应的日程信息,此处并不对参数进行有效性检验，因此传入的时间字符串应该保证有效
     * @return  获取日程信息
     */
    public List<DidaCalendar> getCalenderByDateAndLimit(String fromTime,String toTime,int limit) throws IOException {
        StringBuilder diDaJsonUrl=new StringBuilder("https://api.dida365.com/api/v2/project/all/completedInAll/");
        if(fromTime!=null&&!"".equals(fromTime)){
            diDaJsonUrl.append("?from="+fromTime+"%2023:26:45");
        }else {
            diDaJsonUrl.append("?");
        }
        if(toTime!=null&& !"".equals(toTime)){
            diDaJsonUrl.append("&to="+toTime+"%2023:26:45");
        }
        if(limit>=0){
            diDaJsonUrl.append("&limit="+limit);
        }
//        设置httpClient

        HttpGet httpGet=null;

        try {
//            设置url地址
            httpGet = new HttpGet(new URI(diDaJsonUrl.toString()));
//            设置请求头的内容
            httpGet.setHeader("TE","Trailers");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        设置请求头信息
        httpGet.setHeader("Host","api.dida365.com");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0");
        httpGet.setHeader("Accept","application/json, text/plain, */*");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
        httpGet.setHeader("X-Device","web_app, Firefox, 69.0, , web_app");
        httpGet.setHeader("Origin","https://www.dida365.com");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Referer","https://www.dida365.com/");
        httpGet.setHeader("Cookie","UM_distinctid=16c79b23dd8124-02d5dddaea15748-14367940-100200-16c79b23dd91aa; t=73AE2E6CC13DD9679B9E3BED543C3A93C40F170A38731512E2E2DE2815EF833490EF1AE69FDE55F11F05423713D9C783B77F030C2E7CFCF2CAAFAAE140CC6104CDD95BFB71682D5F3C7E69D6712560BA48C1AAFE16BF27AFEC0558E33E0C597B385AA04082B6E13207380EE6E17F65D77E5A965626102ED47FECDC522DBDED5D52A72790BDA54A97F350026435D774697B6B51D2224D0180FBE9F828B46FEA470665EE343B576E75");

//      根据httpGet以及diDaCalender类获取响应处理后的java对象集合
        this.didaCalendarList= new LoginUtils().getJsonListByHttpGet(httpGet,DidaCalendar.class);

        return didaCalendarList;
    }

    /**
     * 给定起止时间字符串，返回所有的日程信息
     * @param fromTime
     * @param toTime
     * @return
     */
    public List<DidaCalendar> getCalenderByDate(String fromTime,String toTime) throws IOException {
        return getCalenderByDateAndLimit(fromTime,toTime,-1);
    }

    /**
     * 返回离当前请求日期最近的上一周的所有日程信息
     * @return
     */
    public  List<DidaCalendar> getCalenderBeforeWeek() throws IOException {
        //获取最近一周的日程信息
        TimeParam time=new TimeUtils().getRecentWeekStr();
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        String fromTime=sDateFormat.format(time.getFrom());
        String toTime=sDateFormat.format(time.getTo());
        return  getCalenderByDate(fromTime,toTime);
    }

    // 增加format方法对清单进行处理，使其末尾都有代币数字,如果末尾没有奖励默认为0.5，而且给定的score值不能大于99
    public void format(List<DidaCalendar> didaCalendarList){
//        private void format(List<DidaCalendar> didaCalendarList){
        for(DidaCalendar d:didaCalendarList){
            String title=d.getTitle().trim();
            float score=0;
            //提取title中的代币数，并转换为float类型
            //获取代币的字符串
            int i=title.length();
            char numChar;
            //判断代币是否是小数
            if(title.charAt(i-2)!= '.'){
                i=i-1;
                numChar=title.charAt(i);
                while (numChar>='0'&&numChar<='9'){
                    numChar=title.charAt(--i);
                }
            }else{
                i=i-3;
                numChar=title.charAt(i);
                while (numChar>='0'&&numChar<='9'){
                    numChar=title.charAt(--i);
                }
            }
            System.out.println("score="+title.substring(i+1,title.length()));

            score=Float.valueOf(title.substring(i+1,title.length()));
            d.setScore(score);
            if(title.charAt(i)==' '){
                d.setTitle(title.substring(0,i));
            }else {
                d.setTitle(title.substring(0,i+1));
            }
            System.out.println("new title="+d.getTitle());
        }
    }
}

