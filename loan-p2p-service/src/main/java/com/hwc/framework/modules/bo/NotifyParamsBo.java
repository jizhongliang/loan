package com.hwc.framework.modules.bo;

/**详细参数
 * Created by jzl on 2017/12/29.
 */
public class NotifyParamsBo {
    private String code;
    private String message;
    private FarmNotifyDataBo data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FarmNotifyDataBo getData() {
        return data;
    }

    public void setData(FarmNotifyDataBo data) {
        this.data = data;
    }
}
