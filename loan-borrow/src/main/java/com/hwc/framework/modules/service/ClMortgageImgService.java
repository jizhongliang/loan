package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.ClMortgageImg;
import com.hwc.mybatis.core.Service;

import java.util.List;

/**
 * 2017/10/23.
 */
public interface ClMortgageImgService extends Service<ClMortgageImg> {
    public Response batchUploadImg(List<String> url,Long userid,  Long mid, String cat);

    public List<String> getImg(String cat, Long mid);
}
