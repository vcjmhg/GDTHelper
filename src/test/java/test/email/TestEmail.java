package test.email;

import email.MailUtils;
import org.apache.commons.mail.EmailException;
import org.junit.Test;

import javax.mail.internet.AddressException;
import java.io.*;

/**
 * @program: GTDHelper
 * @description:
 * @author: vcjmhg
 * @create: 2019-10-03 14:36
 **/
public class TestEmail {
    @Test
    public static void testEmail(String[] args) {
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

