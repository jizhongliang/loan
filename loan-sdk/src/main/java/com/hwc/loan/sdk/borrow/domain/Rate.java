package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class Rate {

    private String rate;//白名单日费率（0.05%）
    private String rates;//白名单日费率（0.05%）*100，1万元每天所需要的利息
    private String ratess;//白名单日费率（0.05%）*10,1000元每天所需要的利息
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getRates() {
		return rates;
	}
	public void setRates(String rates) {
		this.rates = rates;
	}
	public String getRatess() {
		return ratess;
	}
	public void setRatess(String ratess) {
		this.ratess = ratess;
	}
   

   
} 