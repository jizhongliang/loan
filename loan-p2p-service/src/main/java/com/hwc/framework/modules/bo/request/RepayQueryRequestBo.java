package com.hwc.framework.modules.bo.request;

/**还款查询请求实体
 * Created by jzl on 2017/12/28.
 */
public class RepayQueryRequestBo {
    private String source;
    private String sign;
    private RepayQueryParamsRequestBo params;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public RepayQueryParamsRequestBo getParams() {
        return params;
    }

    public void setParams(RepayQueryParamsRequestBo params) {
        this.params = params;
    }
}
