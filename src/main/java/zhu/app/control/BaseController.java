package zhu.app.control;

import com.alibaba.fastjson.JSON;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zhu.app.core.model.ApiResponse;
import zhu.app.core.model.ApiResponseState;
import zhu.app.core.utils.ConvertUtil;
import zhu.app.core.utils.ModelUtils;

/**
 * Created by lenovo
 * date 2017/5/13.
 */
public class BaseController {

        protected Logger logger = LoggerFactory.getLogger(this.getClass());
        @Autowired
        protected HttpServletRequest request;
        @Autowired
        protected HttpServletResponse response;
        @Autowired
        protected HttpSession session;
        @Autowired
        protected ServletContext application;


        public BaseController() {
        }

        @ExceptionHandler
        public void exceptionHandler(HttpServletRequest request, Throwable t) {
            this.printStackTrace(t);
        }

        protected void printStackTrace(Throwable t) {
            if(t != null) {
                try {
                    StringWriter x = new StringWriter();
                    t.printStackTrace(new PrintWriter(x));
                    this.response.setContentType("text/plain;charset=UTF-8");
                    this.response.getWriter().println(x.toString());
                    this.response.flushBuffer();
                    this.response.setStatus(500);
                } catch (Exception var3) {
                    this.logger.error("给浏览器打印调用堆栈错误", var3);
                }

            }
        }

        protected ApiResponse callback(boolean success) {
            HashMap map = new HashMap();
            if(success) {
                map.put("success", Boolean.valueOf(true));
                return this.callbackSuccess(JSON.toJSONString(map));
            } else {
                map.put("success", Boolean.valueOf(false));
                return this.callbackFail((Map)map);
            }
        }

        protected ApiResponse callbackSuccess(Map<String, Object> map) {
            String json = "{}";
            if(map != null) {
                json = JSON.toJSONString(map);
            }

            return this.callbackSuccess(json);
        }

        protected ApiResponse callbackSuccess(Object model) {
            String json = "{}";
            if(model != null) {
                json = JSON.toJSONString(model);
            }

            return this.callbackSuccess(json);
        }

        protected ApiResponse callbackSuccess(List<?> list) {
            StringBuilder json = new StringBuilder("[");
            if(!CollectionUtils.isEmpty(list)) {
                Iterator var3 = list.iterator();

                while(var3.hasNext()) {
                    Object item = (Object) var3.next();

                    try {
                        json.append(JSON.toJSONString(item)).append(",");
                    } catch (Exception var6) {
                        this.logger.error("数据转换为JSON失败, data: {}", ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE));
                    }
                }

                if(json.length() > 1) {
                    json.setLength(json.length() - 1);
                }
            }

            json.append("]");
            return new ApiResponse(new ApiResponseState(10000L, "访问成功"), json.toString());
        }

        protected ApiResponse callbackSuccess(String json) {
            return new ApiResponse(new ApiResponseState(10000L, "访问成功"), json);
        }

        protected ApiResponse callbackFail(Map<String, Object> data) {
            return new ApiResponse(new ApiResponseState(10001L, "访问错误"), JSON.toJSONString(data));
        }

        protected ApiResponse callbackFail(String json) {
            return new ApiResponse(new ApiResponseState(10001L, "访问错误"), json);
        }

        protected ApiResponse callbackFail(String message, Map<String, Object> data) {
            return this.callbackFail(message, JSON.toJSONString(data));
        }

        protected ApiResponse callbackFail(String message, String json) {
            return new ApiResponse(new ApiResponseState(10001L, message), json);
        }

        public static void copyProperties(Object source, Object target, boolean ignoreNullSource, String... ignoreProperties) throws BeansException {
            ArrayList ignorePropertyList = new ArrayList();
            String[] var5 = ignoreProperties;
            int var6 = ignoreProperties.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String ignoreProperty = var5[var7];
                ignorePropertyList.add(ignoreProperty);
            }

            ignorePropertyList.addAll(Arrays.asList(new String[]{"version", "createUser", "createDate", "updateUser", "updateDate"}));
            ModelUtils.copyProperties(source, target, ignoreNullSource, ignorePropertyList);
        }
       //返回不支持对象采用下面的方法
        protected  String callSuccess(Map<String, Object> map){
          return ConvertUtil.getJSONString(callbackSuccess(map));
       }
        protected  String callSuccess(Object model){
            return ConvertUtil.getJSONString(callbackSuccess(model));
        }
        protected  String callSuccess(List<?> list){
            return ConvertUtil.getJSONString(callbackSuccess(list));
        }
        protected  String callSuccess(String json){
            return ConvertUtil.getJSONString(callbackSuccess(json));
        }
        protected  String callFail(Map<String, Object> data){
            return ConvertUtil.getJSONString(callbackFail(data));
        }
        protected  String callFail(String json){
            return ConvertUtil.getJSONString(callbackFail(json));
        }
        protected  String callFail(String message, Map<String, Object> data){
            return ConvertUtil.getJSONString(callbackFail(message,data));
        }
        protected  String callFail(String message, String json){
            return ConvertUtil.getJSONString(callbackFail(message,json));
        }
    }

