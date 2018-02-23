package com.hwc.framework.modules.third;

import com.hwc.framework.modules.domain.ContractDomian;
import com.hwc.loan.sdk.borrow.domain.BorrowRepayRepayPlanListDomain;

import java.util.List;

/**
 * Created by lxk on 2017/12/26.
 */
public interface BorrowService {
    List<BorrowRepayRepayPlanListDomain> getRepayPlan(ContractDomian domian);

    BorrowRepayRepayPlanListDomain getCreditRepayPlan(ContractDomian domian);
}
