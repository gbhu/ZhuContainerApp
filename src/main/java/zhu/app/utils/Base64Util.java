package zhu.app.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created with com.gi.service.common.
 *
 * @author 陶彦博【yanbo.tao@grandinsight.com】
 * @date 2017/3/28 : 13:44
 */
public class Base64Util {
    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*
    public  static void main(String[] args){
        String s = "hello world 你好！";
        String s1= Base64Util.getBase64(s);
        System.out.println( s);
        System.out.println(s1);
        String s2 = Base64Util.getFromBase64(s1);
        System.out.println(s2);
    }
    */
}
