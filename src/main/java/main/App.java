package main;

import jdk.nashorn.internal.runtime.regexp.joni.constants.TargetInfo;
import org.apache.http.client.methods.HttpGet;
import pojo.DidaCalendar;
import pojo.TimeParam;
import utils.LoginUtils;
import utils.TimeUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * GDTHelper的入口类
 */
public class App {
    public static void main(String[] args) throws IOException {
        //获取最近一周的日程信息
        TimeParam time=new TimeUtils().getRecentWeekStr();
        System.out.println("from-->"+time.getFrom());
        System.out.println("to-->"+ time.getTo());
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");

//        String fromTime="";
//        String toTime="2019-09-18";
        String fromTime=sDateFormat.format(time.getFrom());
        String toTime=sDateFormat.format(time.getTo());

        int limit=200;
        StringBuilder diDaJsonUrl=new StringBuilder("https://api.dida365.com/api/v2/project/all/completedInAll/");
        diDaJsonUrl.append("?from="+fromTime+"%2023:26:45");
        diDaJsonUrl.append("&to="+toTime+"%2023:26:45");
        diDaJsonUrl.append("&limit="+limit);
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
        List<DidaCalendar> didaCalendarList= new LoginUtils().getJsonListByHttpGet(httpGet,DidaCalendar.class);
        for(DidaCalendar calendar:didaCalendarList){
            System.out.println(calendar.getTitle());
        }
    }
}
