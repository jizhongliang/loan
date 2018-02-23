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

public class LinkfaceHs2Request_FileUrl extends DsCreditRequest {

	private String livingImg;
	private String apisecret;
	private Boolean auto_rotate;

	public String getLivingImg() {
		return livingImg;
	}

	public void setLivingImg(String livingImg) {
		this.livingImg = livingImg;
	}

	public Boolean getAuto_rotate() {
		return auto_rotate;
	}

	public void setAuto_rotate(Boolean auto_rotate) {
		this.auto_rotate = auto_rotate;
	}

	public LinkfaceHs2Request_FileUrl(String host, Header header) {
		super(host, header);
	}

	public HttpEntity handle() throws Exception {
		Asserts.notNull(this.livingImg, "livingImg");

		ContentType type = ContentType.create("application/text", "utf-8");
		StringBody apiKeyBody = new StringBody(this.header.getApiKey(), type);
		StringBody livingImgBody = new StringBody(this.livingImg, type);
		StringBody apisecretBody = new StringBody(this.apisecret, type);
		MultipartEntityBuilder build = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
				.addPart("api_id", apiKeyBody).addPart("api_secret", apisecretBody).addPart("file_url", livingImgBody);

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