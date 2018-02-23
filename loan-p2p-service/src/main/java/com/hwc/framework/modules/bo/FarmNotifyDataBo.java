package com.hwc.framework.modules.bo;

import java.util.List;

/**放款回调data参数实体
 * Created by jzl on 2017/12/26.
 */
public class FarmNotifyDataBo {
    private Integer notifyType; //回调通知类型：1打款结果通知，2逾期扣款账号查询
    private List<FarmNotifyDataResultBo> result;  //结果

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    public List<FarmNotifyDataResultBo> getResult() {
        return result;
    }

    public void setResult(List<FarmNotifyDataResultBo> result) {
        this.result = result;
    }
}
