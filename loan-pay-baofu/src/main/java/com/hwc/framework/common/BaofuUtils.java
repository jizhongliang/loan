/**
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 */
/**
 * @author Administrator
 *
 */
package com.hwc.framework.common;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.api.ResponseCode;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaofuUtils {

	public static ThreadLocal<Boolean> isCredit = new ThreadLocal<Boolean>(){  
        public Boolean initialValue() {  
            return true;  
        }  
    };  
	/**
	 * 明文参数
	 * 
	 * @param Url
	 * @param Parms
	 * @return
	 */
	private String version;
	private String member_id;
	private String terminal_id;
	private String txn_type;
	private String data_type;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private String pre_bind_card_url;
	private String confirm_bind_card_url;
	private String pay_001_url;
	private String pay_004_url;
	private String pay_query_url;
	private String repay_url;
	private String repay_query_url;

	private String version1;
	private String member_id1;
	private String terminal_id1;
	private String txn_type1;
	private String data_type1;
	private PrivateKey privateKey1;
	private PublicKey publicKey1;
	private String pre_bind_card_url1;
	private String confirm_bind_card_url1;
	private String pay_001_url1;
	private String pay_004_url1;
	private String pay_query_url1;
	private String repay_url1;
	private String repay_query_url1;

	public String getVersion() {
		return isCredit.get()?version1:version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMember_id() {
		return isCredit.get()?member_id1:member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getTerminal_id() {
		return isCredit.get()?terminal_id1:terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public String getTxn_type() {
		return isCredit.get()?txn_type1:txn_type;
	}

	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}

	public String getData_type() {
		return isCredit.get()?data_type1:data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public PublicKey getPublicKey() {
		return isCredit.get()?publicKey1:publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PrivateKey getPrivateKey() {
		return isCredit.get()?privateKey1:privateKey;
	}
}