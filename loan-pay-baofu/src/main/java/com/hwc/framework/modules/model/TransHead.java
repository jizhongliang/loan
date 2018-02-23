package com.hwc.framework.modules.model;


public class TransHead {
	
	private String return_code;
	
	private String return_msg;
	
	private String trans_count;
	
	private String trans_totalMoney;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getTrans_count() {
		return trans_count;
	}

	public void setTrans_count(String trans_count) {
		this.trans_count = trans_count;
	}

	public String getTrans_totalMoney() {
		return trans_totalMoney;
	}

	public void setTrans_totalMoney(String trans_totalMoney) {
		this.trans_totalMoney = trans_totalMoney;
	}
}
