package com.hwc.framework.config;

import com.hwc.framework.common.BaofuUtils;
import com.hwc.framework.common.RsaCodingUtil;
import com.hwc.framework.common.RsaReadUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by on 2017/11/8.
 */
@Configuration
@Data
public class BaoFuConfig {
	@Value("${baofu.member.id}")
	private String member_id;
	@Value("${baofu.version}")
	private String version;
	@Value("${baofu.terminal.id}")
	private String terminal_id;
	@Value("${baofu.data.type}")
	private String data_type;
	@Value("${baofu.pfx.name}")
	private String pfxPath;
	@Value("${baofu.pfx.pwd}")
	private String priKeyPass;
	@Value("${baofu.cer.name}")
	private String publicKey;
	@Value("${baofu.loanurl}")
	private String loanUrl;
	@Value("${baofu.queryurl}")
	private String loanQueryUrl;
	@Value("${baofu.url}")
	private String commonUrl;
	@Value("${baofu.authApplyUrl}")
	public String authApplyUrl;
	@Value("${baofu.authConfirmUrl}")
	public String authConfirmUrl;

	// @PostConstruct
	// public void init() {
	// PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromClassPath(pfxPath,
	// priKeyPass);
	// PublicKey publicKey2 = RsaReadUtil.getPublicKeyFromClassPath(publicKey);
	// RsaCodingUtil.privateKey = privateKey;
	// RsaCodingUtil.publicKey = publicKey2;
	// }

	// 第二套 私钥地址 私钥密码 公钥 

	@Value("${baofu.member.id1}")
	private String member_id1;
	@Value("${baofu.version1}")
	private String version1;
	@Value("${baofu.terminal.id1}")
	private String terminal_id1;
	@Value("${baofu.data.type1}")
	private String data_type1;
	@Value("${baofu.pfx.name1}")
	private String pfxPath1;
	@Value("${baofu.pfx.pwd1}")
	private String priKeyPass1;
	@Value("${baofu.cer.name1}")
	private String publicKey1;
	@Value("${baofu.loanurl}")
	private String loanUrl1;
	@Value("${baofu.queryurl1}")
	private String loanQueryUrl1;
	@Value("${baofu.url1}")
	private String commonUrl1;
	@Value("${baofu.authApplyUrl1}")
	public String authApplyUrl1;
	@Value("${baofu.authConfirmUrl1}")
	public String authConfirmUrl1;
	
	@Value("${baofu.control}")
	private int state;

	@PostConstruct
	public void init() {
		RsaCodingUtil.privateKey= RsaReadUtil.getPrivateKeyFromClassPath(pfxPath, priKeyPass);
		RsaCodingUtil.publicKey = RsaReadUtil.getPublicKeyFromClassPath(publicKey);
		RsaCodingUtil.privateKey1= RsaReadUtil.getPrivateKeyFromClassPath(pfxPath1, priKeyPass1);
		RsaCodingUtil.publicKey1= RsaReadUtil.getPublicKeyFromClassPath(publicKey1);
		 
	}

	@Bean
	public BaofuUtils formUtil() {
		BaofuUtils util = new BaofuUtils();
		util.setMember_id(member_id);
		util.setVersion(version);
		util.setData_type(data_type);
		util.setTerminal_id(terminal_id);
		util.setTxn_type("0431");
		util.setPay_001_url(loanUrl);
		util.setPay_query_url(loanQueryUrl);
		util.setConfirm_bind_card_url(authConfirmUrl);
		util.setPre_bind_card_url(authApplyUrl);
		util.setRepay_url(commonUrl);
		util.setRepay_query_url(commonUrl);
		util.setPrivateKey(RsaCodingUtil.privateKey);
		util.setPublicKey(RsaCodingUtil.publicKey);
		
		
		
		util.setMember_id1(member_id1);
		util.setVersion1(version1);
		util.setData_type1(data_type1);
		util.setTerminal_id1(terminal_id1);
		util.setTxn_type1("0431");
		util.setPay_001_url1(loanUrl1);
		util.setPay_query_url1(loanQueryUrl1);
		util.setConfirm_bind_card_url1(authConfirmUrl1);
		util.setPre_bind_card_url1(authApplyUrl1);
		util.setRepay_url1(commonUrl1);
		util.setRepay_query_url1(commonUrl1);
		util.setPrivateKey1(RsaCodingUtil.privateKey1);
		util.setPublicKey1(RsaCodingUtil.publicKey1);
		return util;

	}

}
