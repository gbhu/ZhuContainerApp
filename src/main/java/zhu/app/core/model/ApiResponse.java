package zhu.app.core.model;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo
 * date 2017/5/13.
 */
public class ApiResponse {
    private ApiResponseState state;
    private Object body;

    public ApiResponse(ApiResponseState state, Map<String, Object> body) {
        this.state = state;
        if(body == null) {
            this.body = new HashMap();
        } else {
            this.body = body;
        }

    }

    public ApiResponse(ApiResponseState state, String body) {
        this.state = state;
        if(StringUtils.isBlank(body)) {
            this.body = new HashMap();
        } else {
            this.body = JSON.parseObject(body, Object.class);
        }

    }

    public ApiResponseState getState() {
        return this.state;
    }

    public void setState(ApiResponseState state) {
        this.state = state;
    }

    public Object getBody() {
        return this.body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
