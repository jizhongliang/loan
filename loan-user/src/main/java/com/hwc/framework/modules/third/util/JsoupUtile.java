package com.hwc.framework.modules.third.util;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.modules.utils.OSSUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class JsoupUtile {

    public static String getZhenXingHtml(String url,OSSUtils ossUtils,String userid){
        String path = "";
        if(!FsUtils.strsEmpty(url)){
            try {
                Document doc=  Jsoup.connect(url).get();
                Elements elements = doc.getElementsByTag("a");
                elements.remove();
                String meta = "<meta charset=\"utf-8/\">";
                String newMeta = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
                String html = doc.toString();
                String newHtml = html.replaceAll(meta,newMeta);
                path = ossUtils.upHtml(newHtml,"zhengxin/report/",userid);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return path;
    }

    public static String getPhoneHtml(String url,OSSUtils ossUtils,String userid){
        String path = "";
        if(!FsUtils.strsEmpty(url)){
            try {
                Document doc=  Jsoup.connect(url).get();
                Elements elements = doc.getElementsByTag("a");
                elements.remove();
                Elements footer = doc.getElementsByTag("footer");
                footer.remove();
                String meta = "<meta charset=\"utf-8/\">";
                String newMeta = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
                String html = doc.toString();
                String newHtml = html.replaceAll(meta,newMeta);
                path = ossUtils.upHtml(newHtml,"carrier/report/",userid);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return path;
    }

}
