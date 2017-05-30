package zhu.app.utils;

/**
 * Created by Administrator on 2017/3/20.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static String formatDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "";
        }
    }

    public static String formatTime(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "";
        }
    }

    public static String formatDateTime(Date date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "";
        }
    }

    public static String formatDateTime(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "";
        }
    }

    public static Date parseDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public static Date parseSapDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public static Date parseDateTime(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /*
    *把sap格式时间类型转成java时间类型
    **/
    public static Date parseToSapDate(String strDate) throws ParseException {
        String times = strDate.substring(0, 4) + "-" + strDate.substring(4, 6) + "-" + strDate.substring(6, 8);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(times);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /*
    * 把日期转成SAP通用的时间格式
    **/
    public static String formatTimeToSap(Date date) {
        String str = "";
        try {
            str = formatDate(date);
        } catch (ParseException e) {
            LOGGER.error("处理异常", e);
        }
        str = str.replace("-", "");
        return str;
    }

}
