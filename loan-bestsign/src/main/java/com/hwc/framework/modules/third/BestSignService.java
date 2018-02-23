package com.hwc.framework.modules.third;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by  on 2017/12/7.
 */
public interface BestSignService {
    JSONObject register(JSONObject jsonObject);

    JSONObject registerEnterprise(JSONObject jsonObject);

    JSONObject getUserInfo(JSONObject jsonObject) throws Exception;

    JSONObject setEnterpriseInfo(JSONObject jsonObject);

    JSONObject setUserIdno(JSONObject jsonObject);

    JSONObject applycert(JSONObject jsonObject) throws Exception;

    JSONObject createSignatureImage(JSONObject jsonObject);

    JSONObject upSignatureImage(JSONObject jsonObject) throws Exception;

    JSONObject upContract(JSONObject jsonObject);

    JSONObject getSignURL(JSONObject jsonObject);

    JSONObject addPDFElements(JSONObject jsonObject);

    JSONObject createContract(JSONObject jsonObject);

    JSONObject addSigners(JSONObject jsonObject);

    JSONObject addSigner(JSONObject jsonObject);

    JSONObject setSignerConfig(JSONObject jsonObject);

    JSONObject signCert(JSONObject jsonObject);

    JSONObject getDownload(JSONObject jsonObject);

    JSONObject finish(JSONObject jsonObject);

    JSONObject getPdfDownLoad(JSONObject jsonObject);

    JSONObject downUserImage(JSONObject jsonObject);

	byte[] downloadSignatureImage(String account, String imageName) throws Exception;

	String getUrlByRsa(String account, String imageName, Map<String, Object> data, String path) throws Exception;

	String createImageFromBytes(String fileName, byte[] buffer);

    //JSONObject addPDFElements2(JSONObject );
}
