package com.hwc.framework.modules.domain;

import java.math.BigDecimal;

/**提前还款的利息和违约金
 * Created by jzl on 2017/12/29.
 */
public class PreRepayInterestAndBreakFine {
    private BigDecimal interest;        //利息
    private BigDecimal breakFine;       //违约金

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getBreakFine() {
        return breakFine;
    }

    public void setBreakFine(BigDecimal breakFine) {
        this.breakFine = breakFine;
    }
}
