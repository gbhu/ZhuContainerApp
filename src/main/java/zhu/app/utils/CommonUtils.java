package zhu.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CommonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
    /**
     * 编码格式 UTF-8
     */
    public static final String ENCODING = "UTF-8";

    private CommonUtils() {
    }

    public static String hello() {
        return "gi commom utils";
    }

    public static String getDocumentPrefix(String key){
        Properties properties = new Properties();
        ClassLoader c = CommonUtils.class.getClassLoader();
        try {
            properties.load(c.getResourceAsStream("prefix.properties"));
        } catch (IOException e) {
            LOGGER.error("处理异常", e);
        }
        if (properties == null) {
            return "";
        }
        return properties.getProperty(key);
    }
    public static String getBusinessType(String key){
        Properties properties = new Properties();
        ClassLoader c = CommonUtils.class.getClassLoader();
        try {
            properties.load(c.getResourceAsStream("businessType.properties"));
        } catch (IOException e) {
            LOGGER.error("处理异常", e);
        }
        if (properties == null) {
            return "";
        }
        return properties.getProperty(key);
    }

    public static String formatDate(Date date){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目录
        if (dir.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }
}