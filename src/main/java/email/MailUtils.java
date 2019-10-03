package email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: GTDHelper
 * @description:
 * @author: vcjmhg
 * @create: 2019-10-03 10:06
 **/
public class MailUtils {
    //发件人的邮件名称和密码
//    private String userName;
//    private String password;
    private HtmlEmail htmlEmail =new HtmlEmail();
    public MailUtils(String userName, String password, String aHostName){
//        this.userName=userName;
//        this.password=password;
        htmlEmail.setAuthentication(userName,password);
        htmlEmail.setHostName(aHostName);
    }
    //TODO 两个方法功能有部分重叠可以通过设计模式进行优化
    /**
     * 发送HTML格式的富文本邮件
     * @param ahtml
     * @param tos
     * @param fromName
     * @param fromNameAlias
     * @param emailSubject
     * @throws EmailException
     */
    public void sendHtmlEmail(String ahtml, Collection tos,String fromName,String fromNameAlias,String emailSubject) throws EmailException {
        htmlEmail.setCharset("utf-8");//设置发送的字符类型
        htmlEmail.setTo(tos);
        htmlEmail.setFrom(fromName,fromNameAlias);//发送人的邮箱为自己的，用户名可以随便填
        htmlEmail.setSubject(emailSubject);
        htmlEmail.setHtmlMsg(ahtml);
        //由于邮件滥发等原因阿里云服务器禁用了25端口，所以这里得使用ssl加密传输（这样使用的端口号是465）
        htmlEmail.setSSLOnConnect(true);
        htmlEmail.send(); //发送邮件
    }

    /**
     * 发送纯文本内容的邮件
     * @param aText
     * @param tos
     * @param fromName
     * @param fromNameAlias
     * @param emailSubject
     * @throws EmailException
     */
    public void sendTextEmail( String aText, Collection tos,String fromName,String fromNameAlias,String emailSubject) throws EmailException {
        htmlEmail.setCharset("utf-8");//设置发送的字符类型
        htmlEmail.setTo(tos);
        htmlEmail.setFrom(fromName,fromNameAlias);//发送人的邮箱为自己的，用户名可以随便填
        htmlEmail.setSubject(emailSubject);
        htmlEmail.setTextMsg(aText);
        //由于邮件滥发等原因阿里云服务器禁用了25端口，所以这里得使用ssl加密传输（这样使用的端口号是465）
        htmlEmail.setSSLOnConnect(true);
        htmlEmail.send(); //发送邮件
    }

    /**
     * 默认只给一个用户发送信息
     * @param ahtml
     * @param to
     * @param fromName
     * @param fromNameAlias
     * @param emailSubject
     * @throws AddressException
     * @throws EmailException
     */
    public void sendDefaultHtmlEmail(String ahtml, String to,String fromName,String fromNameAlias,String emailSubject) throws AddressException, EmailException {
        Collection<InternetAddress> tos=new ArrayList<>();
        tos.add(new InternetAddress(to));
        sendHtmlEmail(ahtml,tos,fromName,fromNameAlias,emailSubject);
    }
}

