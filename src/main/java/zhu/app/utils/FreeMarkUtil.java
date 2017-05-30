package zhu.app.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author yanbo.tao@grandinsight.com
 * @date 2017/5/15 : 17:38
 */
public class FreeMarkUtil {
    private static Logger log = Logger.getLogger(FreeMarkUtil.class);
    private static StringTemplateLoader STL = new StringTemplateLoader();
    private static Configuration CONFIG = new Configuration();

    static {
        CONFIG.setTemplateLoader(STL);
    }

    public static StringBuffer getStringBufferByFileInputStream(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while ((fis.read(buf)) != -1) {
            sb.append(new String(buf, "utf-8"));
            buf = new byte[1024];// 重新生成，避免和上次读取的数据重复
        }
        return sb;
    }

    public static String getTemplateSting(String tempString, Object param) {
        if (tempString == null) return tempString;
        String statement = tempString;
        String key = MD5Util.getMD5Str32(tempString);
        StringWriter sw = new StringWriter();
        try {
            if (STL.findTemplateSource(key) == null) {
                STL.putTemplate(key, tempString);
            }
            CONFIG.getTemplate(key).process(param, sw);
            statement = sw.toString();
        } catch (Exception e) {
            log.error("getTemplateSting错误：" + e.getMessage(), e);
        }
        return statement;
    }
}
