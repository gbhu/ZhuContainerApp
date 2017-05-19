package zhu.app.core.model;

/**
 * Created by lenovo
 * date 2017/5/13.
 */
public class ApiResponseState { private long errCode;
    private String errMsg;
    private long timestamp;

    public ApiResponseState(long errCode, String errMsg, long timestamp) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.timestamp = timestamp;
    }

    public ApiResponseState(long errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.timestamp = System.currentTimeMillis();
    }

    public long getErrCode() {
        return this.errCode;
    }

    public void setErrCode(long errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

