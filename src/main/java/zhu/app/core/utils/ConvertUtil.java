package zhu.app.core.utils;

import com.alibaba.fastjson.JSON;

import zhu.app.core.model.ApiResponse;

/**
 * Created by lenovo
 * date 2017/5/14.
 */
public class ConvertUtil {
    public  ConvertUtil(){}
    public static  String getJSONString(ApiResponse apiResponse){
        if (apiResponse!=null){
            return JSON.toJSONString(apiResponse);
        }else {
            return "";
        }
    }
}
