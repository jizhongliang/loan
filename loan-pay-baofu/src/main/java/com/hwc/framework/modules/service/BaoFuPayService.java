package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.PayDataDomain;
import com.hwc.framework.modules.domain.PayQueryDomain;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by   on 2017/11/8.
 */
public interface BaoFuPayService {
      void loanSplit(PayDataDomain data) throws Exception;

      Response loan(PayDataDomain data) throws Exception;

      Response queryLoan(PayQueryDomain data) throws Exception;

    Response payCallback(HttpServletRequest request);
}
