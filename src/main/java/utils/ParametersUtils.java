package utils;

import pojo.Parameters;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: GTDHelper
 * @description:
 * 对命令行参数进行预处理
 * @author: vcjmhg
 * @create: 2019-09-23 10:32
 **/
public class ParametersUtils {
    /**
     *
     * @param args
     * @return params which is producted by args
     */
    public Parameters getParameters(String[] args){
        Parameters params=new Parameters();

        //parse args and solve exception
        for(int i=0;i<args.length;i++){
            switch(args[i])
            {
                case "-tick":
                    params.setTick(true);
                    break;
                case "-tdl":
                    params.setTdl(true);
                    break;
                case "-excel":
                    params.setExcel(true);
                    break;
                //Is has a param and correspond to uri format
                case "-net":
                    if(isValidUrl(args[++i])){
                        params.setNet(args[i]);
                        break;
                    }else{
                        System.err.println("param '-net' "+args[i]+" is not validate!");
                        System.exit(1);
                    }
                    //判断tag后边的参数是否是以#开头的如果不是认为异常
                case "-tag":
                    if(args[++i].startsWith("#")){
                        params.setTag(args[i]);
                        break;
                    }else{
                        System.err.println("param '-tag' "+args[i]+" is not validate!");
                        System.exit(2);
                    }
                    //此处并不详细判断文件路径是否合法，后续在生成文件的过程中再进行处理，此处只判断空参数的情况
                case "-in":
                    if(args[++i].startsWith("-")){
                        System.err.println("param '-in' can be null !");
                        System.exit(3);
                    }else{
                        params.setIn(args[i]);
                        break;
                    }
                case "-out":
                    if(args[++i].startsWith("-")){
                        System.err.println("param '-out' can be null !");
                        System.exit(3);
                    }else{
                        params.setIn(args[i]);
                        break;
                    }
                case "-mail":
                    if (isEmail(args[++i])) {
                        params.setMail(args[i]);
                        break;
                    }else{
                        System.err.println("param '-mail' "+args[i]+" is not validate!");
                        System.exit(4);
                    }
            }
        }

        return params;
    }
    //判断uri地址是否合法
    private static boolean isValidUrl(String urlString){
        URI uri = null;
        try {
            uri = new URI(urlString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }

        if(uri.getHost() == null){
            return false;
        }
        if(uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https")){
            return true;
        }
        return false;
    }
    //判断email地址是否合法
    public boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

}
