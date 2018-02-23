package com.hwc.framework.modules.bo.dto.request;

/**还款请求实体
 * Created by jzl on 2018/1/8.
 */
public class RepayRequestDto {
    private String userId;
    private String borrowId;
    private String type;
    private String tradePassword;

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
