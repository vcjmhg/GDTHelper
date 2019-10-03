package main;

import email.MailUtils;
import org.apache.commons.mail.EmailException;
import pojo.DidaCalendar;
import utils.DidaUtils;

import javax.mail.internet.AddressException;
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
        MailUtils mailUtils=new MailUtils("13069648310@163.com","VcJmHg0903","smtp.163.com");
        StringBuffer htmlContent=new StringBuffer();
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/main/java/email/week_table.html"))));
            if(!(reader.ready())){
                System.out.printf("reader为准备就绪");
            }
            String line=null;
            while ((line=reader.readLine())!=null){
                htmlContent.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mailUtils.sendDefaultHtmlEmail(htmlContent.toString(),"vcjmhg@163.com","13069648310@163.com","GDTHelper","每周日程总结");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }
}
