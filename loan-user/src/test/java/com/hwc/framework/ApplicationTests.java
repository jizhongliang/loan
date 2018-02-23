package com.hwc.framework;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.modules.utils.OSSUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private OSSUtils ossUtils;

	@Test
	public void contextLoads() {
			String conStr = "";
			String HtmlUrl ="https://tenant.51datakey.com/zhengxin/report_data?data=0l5c1mDOauokdXDtx5S2BNZYaFVeE10gkQeGCN8jtSbxVjRgF5kDcvKVSYetm47b5VK%2Fzdvb5YjJExOpm8yGp473RoX%2Bk9fU41SZiHk8uu4DBrf1kpcxvA%3D%3D";
			if(!FsUtils.strsEmpty(HtmlUrl)){
				try {
					Document doc=  Jsoup.connect(HtmlUrl).get();
					Elements elements = doc.getElementsByTag("a");
					elements.remove();
					String meta = "<meta charset=\"utf-8/\">";
					String newMeta = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
					String html = doc.toString();
					String newHtml = html.replaceAll(meta,newMeta);
					ossUtils.upHtml(newHtml,"zhenxingHtml","10010");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	@Test
	public void testCharge() {
	}

}
