package com.hwc.framework.modules.bo.request;

import java.util.List;

/**还款信封
 * Created by jzl on 2017/12/27.
 */
public class RepayRequestBo {
    private String source;
    private String sign;
    private List<RepayParamsRequestBo> params;

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

    public List<RepayParamsRequestBo> getParams() {
        return params;
    }

    public void setParams(List<RepayParamsRequestBo> params) {
        this.params = params;
    }
}
