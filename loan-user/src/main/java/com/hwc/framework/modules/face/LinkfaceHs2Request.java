package com.hwc.framework.modules.face;

import credit.DsCreditRequest;
import credit.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.Asserts;
import org.apache.http.util.CharsetUtils;

public class LinkfaceHs2Request extends DsCreditRequest {

	private String livingImg;
	private String ocrImg;
	private String idCard;
	private String name;
	private String apisecret;

	public String getLivingImg() {
		return livingImg;
	}

	public void setLivingImg(String livingImg) {
		this.livingImg = livingImg;
	}

	public String getOcrImg() {
		return ocrImg;
	}

	public void setOcrImg(String ocrImg) {
		this.ocrImg = ocrImg;
	}

	public String getIdCard() {
		return idCard;
	}

	public String getName() {
		return name;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkfaceHs2Request(String host, Header header) {
		super(host, header);
	}

	public HttpEntity handle() throws Exception {
		Asserts.notNull(this.livingImg, "livingImg");

		ContentType type = ContentType.create("application/text", "utf-8");
		StringBody apiKeyBody = new StringBody(this.header.getApiKey(), type);
		StringBody livingImgBody = new StringBody(this.livingImg, type);
		StringBody idCardBody = new StringBody(this.idCard, type);
		StringBody nameBody = new StringBody(this.name, type);
		StringBody apisecretBody = new StringBody(this.apisecret, type);
		MultipartEntityBuilder build = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
				.addPart("api_id", apiKeyBody).addPart("api_secret", apisecretBody).addPart("idCard", idCardBody)
				.addPart("name", nameBody).addPart("selfie_url", livingImgBody);

		if (this.ocrImg != null) {
			StringBody ocrImgBody = new StringBody(this.ocrImg, type);
			build.addPart("historical_selfie_url", ocrImgBody);
		}
		HttpEntity mulitpartEntity = build.setCharset(CharsetUtils.get("UTF-8")).build();
		return mulitpartEntity;
	}

	public String getApisecret() {
		return apisecret;
	}

	public void setApisecret(String apisecret) {
		this.apisecret = apisecret;
	}
}