package utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 *@Author vcjmhg
 *@Description:
 * 主要封装在登录过程中所使用的方法（借助HttpClient来实现）
 *@Date:Created in ${TIME} ${DATE}
 *@Modified By:
 **/
public class LoginUtils {

//    声明的全局的httpClient以及response便于变量复用
    CloseableHttpClient httpClient=null;
    CloseableHttpResponse response=null;

    /**
     * 借助于封装了请求头的httpGet实例来获取响应字符串
     * @param httpGet
     * @return
     * @throws IOException
     */
    public String getJsonByHttpGet(HttpGet httpGet) throws IOException {
        httpClient= HttpClients.createDefault();
//      获取httpClient对象，使用默认创建的对象
        int status = 0;
//      发送请求并获取响应状态码响应数据
        response = httpClient.execute(httpGet);
        status = response.getStatusLine().getStatusCode();

        StringBuilder json=new StringBuilder();
        if(status== HttpStatus.SC_OK){
            //获取具体的json内容
            InputStream contentInputStream = response.getEntity().getContent();
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(contentInputStream));
            String line = null;
            while ((line=jsonReader.readLine())!=null){
                json.append(line);
            }
        }else{
            System.out.println("请求异常-->请求状态码为："+status);
        }

//      关闭response
        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return json.toString();
        }
    }

    /**
     * 根据传入的httpGet和class对象获取json数据，并且将json数据转化为java对象
     * @param httpGet
     * @param clazz
     * @param <T>
     * @return T
     */
    public  final  <T> T getJsonObjectByHttpGet(HttpGet httpGet,Class<T> clazz) throws IOException {
        String json= getJsonByHttpGet(httpGet);
        return JSON.parseObject(json,clazz);
    }

    /**
     * 根据传入的httpGet和class对象获取json数据，并将json数据转换为java相对集合（用List存储）
     * @param httpGet
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public final <T> List<T> getJsonListByHttpGet(HttpGet httpGet, Class<T> clazz) throws IOException {
        String json=getJsonByHttpGet(httpGet);
        return JSON.parseArray(json,clazz);
    }

    //TODO 完成根据用户名和密码进行模拟登陆的方法
    /**
     * 根据用户名和密码获取cookie并且存储到CookieStore中进行存储
     * @param name
     * @param pwd
     * @return
     */
    CookieStore loginByNamdAndPwd(String name, String pwd){
        return null;
    }
}
